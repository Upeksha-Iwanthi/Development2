package com.example.demo.service;

import com.example.demo.persistence.PersistenceService;
import com.example.demo.persistence.SourceModule;
import com.example.demo.repository.SourceModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    PersistenceService persistenceService;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public  void updateFunctionalArea(){
        /**
         *   1. Retrive the Source Modules
         *   2. For each entry
         *        find the modifications from SVN'
         *        for each modification
         *                  find the classes modified
         *                  find the JIRA IDs - (REesolved)
         *                  Find the product areas and functional area
         *                  Save data in the database
         *
         */
        List<SourceModule> branchList = persistenceService.getConfiguredBranchList();

        if (branchList.isEmpty())
        {
            LOGGER.debug("No configured branches found on property file.");
            return;
        }



    }

}
