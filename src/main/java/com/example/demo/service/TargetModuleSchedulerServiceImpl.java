package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.*;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.repository.ProductAreaRepository;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.repository.TargetModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.util.*;

@Service
public class TargetModuleSchedulerServiceImpl implements TargetModuleSchedulerService {

    @Autowired
    private TargetModuleDataService targetModuleDataService;

    @Autowired
    private TargetModuleRepository targetModuleRepository;

    @Autowired
    private ModuleClassService moduleClassService;

    @Autowired
    private ProductAreaService productAreaService;

    @Autowired
    private SVNService svnService;

    @Autowired
    private ProductAreaRepository productAreaRepository;

    @Autowired
    private ModuleClassRepository moduleClassRepository;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     *   1. Retrieve the Source Modules#
     *   2. For each entry
     *        find the modifications from SVN'
     *        for each modification
     *                  find the classes modified
     *                  find the JIRA IDs - (REesolved)
     *                  Find the product areas and functional area
     *                  Save data in the database
     *
     */

    @Override
    public  void updateTablesForTargetModules() throws Exception
    {
//      Retrieve the source modules
        List<TargetModule> branchList = targetModuleDataService.getConfiguredBranchList();

        if (branchList.isEmpty())
        {
            LOGGER.debug("No configured branches found on property file.");
            return;
        }

        for(TargetModule branch: branchList){
//          if revision of branch(source module) is zero, set it to 1.
            try {
                if (branch.getRevision() == 0) {
                    branch.setRevision(01);
                }
                final Map<String,String> propertyHolder = new HashMap<>();

                final Map<String,ProductArea> issueProductAreaMap = new HashMap<>();

              //get the module from svn url.
                String module = moduleClassService.getModuleFromSvnURL(branch.getSvnURL());

              //find the modifications for the branch
                final Set<SVNData> svnData = svnService.findModificationsForTargetModules(branch,propertyHolder);
                findDataForModifications(svnData,module,issueProductAreaMap);

                branch.setRevision(Long.parseLong(propertyHolder.get("LatestRev")));
                targetModuleRepository.save(branch);
                LOGGER.info(" Finished Processing branch " + branch.getSvnURL());

            } catch (SVNException e) {
                e.printStackTrace();
            }
        }
    }


    private void findDataForModifications(Set<SVNData> svnData, String module, Map<String,ProductArea> issueProductAreaMap) throws Exception {
        String exceptionPoint = "";
        for (final SVNData svnRow: svnData)
        {
            try {

                //avoid svnData with classPath that does not ends with ".java"
                String issueId = svnRow.getIssueId();
                if (!svnRow.getClassPath().endsWith(".java")) {
                    continue;
                }

                //find product areas and functional area
                ProductArea productArea = productAreaService.findProductAreaForIssueId(svnRow.getIssueId(), issueProductAreaMap);
                exceptionPoint = productArea.getName();

                //check the DB
                List<ProductArea> area = productAreaRepository.findByName(productArea.getName());
                List<FunctionalArea> faList = productArea.getFa();

                //get product area to save after checking duplicates and setting it's functional areas
                ProductArea productAreaDB = productAreaService.getProductAreaToSave(productArea, area, faList);

                //save product area with it's functional areas
                if (productAreaDB.getName() != null) {
                    productAreaRepository.save(productAreaDB);
                }

//          -------------------------------------------------------------------------------------------------------------

                //get the classpath from svn class path.
                String classPath = moduleClassService.getClassPathFromSvnClassPath(svnRow.getClassPath());

                //get module class
                final ModuleClass moduleClass = moduleClassService.getModuleClass(module, classPath);

                //set functional are classes to module class
                moduleClassService.setFunctionalAreaClassesForModuleClass(moduleClass, faList, issueId);

                //save module class with it's functional area classes
                moduleClassRepository.save(moduleClass);
            }catch (NullPointerException ne){
                LOGGER.error(ne.getMessage());
            }
        }
    }

}