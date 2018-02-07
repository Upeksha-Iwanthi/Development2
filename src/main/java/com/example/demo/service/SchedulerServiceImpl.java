package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.*;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.repository.ProductAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.util.*;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SourceModuleDataService sourceModuleDataService;

    @Autowired
    private ModuleClassService moduleClassService;

    @Autowired
    private SVNService svnService;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private ProductAreaRepository productAreaRepository;

    @Autowired
    private FunctionalAreaRepository functionalAreaRepository;

    @Autowired
    private FunctionalAreaClassRepository functionalAreaClassRepository;

    @Autowired
    private ModuleClassRepository moduleClassRepository;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public  void updateProductArea() throws Exception
    {
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

        List<SourceModule> branchList = sourceModuleDataService.getConfiguredBranchList();

        if (branchList.isEmpty())
        {
            LOGGER.debug("No configured branches found on property file.");
            return;
        }

        for(SourceModule branch: branchList){

            try {
                if (branch.getRevision() == 0) {
                    branch.setRevision(01);
                }

                final List<FunctionalArea> issueFunctionalAreaList = new ArrayList<>();
                final List<FunctionalAreaClass> issueFunctionalAreaClassList = new ArrayList<>();
                final Map<String,ProductArea> issueProductAreaMap = new HashMap<>();

                String module = moduleClassService.getModuleFromSvnURL(branch.getSvnURL());

                final Set<SVNData> svnData = svnService.findModifications(branch);

                for (final SVNData svnRow: svnData)
                {
                    if (!svnRow.getClassPath().endsWith(".java"))
                    {
                        continue;
                    }


//              find product areas
                    ProductArea productArea = issueProductAreaMap.get(svnRow.getIssueId());
                    if(productArea == null)
                    {
                        productArea = jiraService.findProductAreasForIssueId(svnRow.getIssueId());
                        issueProductAreaMap.put(svnRow.getIssueId(),productArea);
                    }

                    productAreaRepository.save(productArea);

//              find functional areas
                    for (final FunctionalArea fa : productArea.getFa())
                    {
                        if (!issueFunctionalAreaList.contains(fa)) {
                            try {
                                fa.setProductArea(productArea);
                                issueFunctionalAreaList.add(fa);
                                functionalAreaRepository.save(fa);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

//                      find JIRA ids
                        FunctionalAreaClass functionalAreaClass = new FunctionalAreaClass();

                        functionalAreaClass.setJiraIssueId(Long.parseLong(svnRow.getIssueId()));
                        functionalAreaClass.setFunctionalArea(fa);

                        if (!issueFunctionalAreaClassList.contains(functionalAreaClass)){
                            issueFunctionalAreaClassList.add(functionalAreaClass);
                            functionalAreaClassRepository.save(functionalAreaClass);
                        }
                    }

//              find the classes modified
                    String classPath = moduleClassService.getClassPathFromSvnClassPath(svnRow.getClassPath());
                    ModuleClass moduleClass = new ModuleClass();
                    moduleClass.setModule(module);
                    moduleClass.setClassPath(classPath);
                    moduleClass.setFunctionalAreaClasses(issueFunctionalAreaClassList);

                    moduleClassRepository.save(moduleClass);


                }

            } catch (SVNException e) {
                e.printStackTrace();
            }


        }



    }

}
