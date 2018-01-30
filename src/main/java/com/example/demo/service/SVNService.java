package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.SourceModule;
import org.tmatesoft.svn.core.SVNException;

import java.util.Map;
import java.util.Set;

public interface SVNService {

    Set<SVNData> findModifications(final SourceModule branch) throws SVNException;


}
