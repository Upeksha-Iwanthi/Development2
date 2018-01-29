package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.SourceModule;
import com.example.demo.service.apps.SVNReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SVNServiceImpl implements SVNService {

    @Autowired
    SVNReader svnReader;

    @Override
    public Set<SVNData> findModifications(SourceModule branch, final Map<String,String> propertyHolder){

        return svnReader.getHistory(branch.getSvnURL(),branch.getRevision(),propertyHolder);

    }


}
