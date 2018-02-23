package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.SourceModule;
import com.example.demo.service.apps.SVNReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.util.*;

@Service
public class SVNServiceImpl implements SVNService {

    @Autowired
    SVNReader svnReader;


    @Override
    public Set<SVNData> findModificationsForSourceModules(SourceModule branch, final Map<String,String> propertyHolder) throws SVNException{

        return svnReader.getHistory(branch.getSvnURL(),branch.getRevision(),propertyHolder);

    }


}
