package com.example.demo.persistence;

import com.example.demo.Data.IssueIdFilter;
import com.example.demo.repository.ModulesRepository;
import com.example.demo.service.apps.MySVNRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ModulesDataServiceImpl implements ModulesDataService {

    @Autowired
    ModulesRepository modulesRepository;

    @Autowired
    MySVNRepositoryFactory mySVNRepositoryFactory;

    @Override
    public List<Modules> getConfiguredBranchList(String type){
        final List<Modules> data = new ArrayList<>();
        for (final Modules obj : modulesRepository.findByType(type))
        {
            data.add(obj);
        }
        return data;
    }


}
