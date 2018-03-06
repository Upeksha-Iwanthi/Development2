package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.*;
import com.example.demo.repository.IssueIdRepository;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.repository.ModulesRepository;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.util.*;

@Service
public class SourceModuleSchedulerServiceImpl implements SourceModuleSchedulerService {

    @Autowired
    private ModulesDataService modulesDataService;

    @Autowired
    private ModulesRepository modulesRepository;

    @Autowired
    private ModuleClassService moduleClassService;

    @Autowired
    private SVNService svnService;

    @Autowired
    private ModuleClassRepository moduleClassRepository;

    @Autowired
    private IssueIdRepository issueIdRepository;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void updateTablesForSourceModules() throws Exception {
        //      Retrieve the source modules
        List<Modules> branchList = modulesDataService.getConfiguredBranchList("Source");

        if (branchList.isEmpty())
        {
            LOGGER.debug("No configured branches found on property file.");
            return;
        }

        for(Modules branch: branchList){
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
                setModuleClassData(svnData,module);

                branch.setRevision(Long.parseLong(propertyHolder.get("LatestRev")));
                modulesRepository.save(branch);
                LOGGER.info(" Finished Processing branch " + branch.getSvnURL());

            } catch (SVNException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private void setModuleClassData(Set<SVNData> svnData, String module) throws Exception {
        String exceptionPoint = "";
        for (final SVNData svnRow: svnData)
        {
            try {
                //avoid svnData with classPath that does not ends with ".java"
                String issueId = svnRow.getIssueId();
                if (!svnRow.getClassPath().endsWith(".java")) {
                    continue;
                }
                //get the classpath from svn class path.
                String classPath = moduleClassService.getClassPathFromSvnClassPath(svnRow.getClassPath());

                //get module class
                final ModuleClass moduleClass = moduleClassService.getModuleClass(module, classPath);

                //set modified jira issue ids.
                List<IssueId> issueList = moduleClass.getIssueList();
                IssueId issue_Id = new IssueId(svnRow.getIssueId());
                issue_Id.setModules(moduleClass);
                ModuleClass moduleClassDb = moduleClassRepository.findByClassPath(moduleClass.getClassPath());
                List<IssueId> issueIdDB = issueIdRepository.findByIssueIdAndModuleClass(svnRow.getIssueId(),moduleClassDb);
                if (issueIdDB.isEmpty()) {
                    issueList.add(issue_Id);
                }
//                issueList.add(issue_Id);

                //save module class
                moduleClassRepository.save(moduleClass);
            }catch (NullPointerException ne){
                LOGGER.error(ne.getMessage());
            }catch (LazyInitializationException le){
                LOGGER.error(le.getMessage());
            }
        }
    }
}
