package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.*;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.repository.SourceModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SourceModuleSchedulerServiceImpl implements SourceModuleSchedulerService {

    @Autowired
    private SourceModuleDataService sourceModuleDataService;

    @Autowired
    private SourceModuleRepository sourceModuleRepository;

    @Autowired
    private ModuleClassService moduleClassService;

    @Autowired
    private SVNService svnService;

    @Autowired
    private ModuleClassRepository moduleClassRepository;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void updateTablesForSourceModules() throws Exception {
        //      Retrieve the source modules
        List<SourceModule> branchList = sourceModuleDataService.getConfiguredBranchList("Dev");

        if (branchList.isEmpty())
        {
            LOGGER.debug("No configured branches found on property file.");
            return;
        }

        for(SourceModule branch: branchList){
//          if revision of branch(source module) is zero, set it to 1.
            try {
                if (branch.getRevision() == 0) {
                    branch.setRevision(01);
                }
                final Map<String,String> propertyHolder = new HashMap<>();

                //get the module from svn url.
                String module = moduleClassService.getModuleFromSvnURL(branch.getSvnURL());

                //find the modifications for the branch
                final Set<SVNData> svnData = svnService.findModificationsForSourceModules(branch,propertyHolder);
                findDataForModifications(svnData,module);

                branch.setRevision(Long.parseLong(propertyHolder.get("LatestRev")));
                sourceModuleRepository.save(branch);
                LOGGER.info(" Finished Processing branch " + branch.getSvnURL());

            } catch (SVNException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private void findDataForModifications(Set<SVNData> svnData, String module) throws Exception {
        String exceptionPoint = "";
        for (final SVNData svnRow: svnData)
        {
            try {
                //get the classpath from svn class path.
                String classPath = moduleClassService.getClassPathFromSvnClassPath(svnRow.getClassPath());

                //get module class
                final ModuleClass moduleClass = moduleClassService.getModuleClass(module, classPath);

                //save module class with it's functional area classes
                moduleClassRepository.save(moduleClass);
            }catch (NullPointerException ne){
                LOGGER.error(ne.getMessage());
            }
        }
    }
}
