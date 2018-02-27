package com.example.demo.service;

import com.example.demo.Data.SVNData;
import com.example.demo.persistence.Modules;
import org.tmatesoft.svn.core.SVNException;

import java.util.Map;
import java.util.Set;

public interface SVNService {

    Set<SVNData> findModificationsForSourceModules(final Modules branch, final Map<String,String> propertyHolder) throws SVNException;

}
