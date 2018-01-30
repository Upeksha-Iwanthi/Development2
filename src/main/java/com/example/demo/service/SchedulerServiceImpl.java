package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.SourceModuleDataService;
import com.example.demo.persistence.SourceModule;
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
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SourceModuleDataService sourceModuleDataService;

    @Autowired
    private SVNService svnService;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public  void updateFunctionalArea(){
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

                final Map<String,String> propertyHolder = new HashMap<>();

                final Set<SVNData> svnData = svnService.findModifications(branch);

                for (final SVNData svnRow: svnData){

                }

            } catch (SVNException e) {
                e.printStackTrace();
            }


        }



    }

}
