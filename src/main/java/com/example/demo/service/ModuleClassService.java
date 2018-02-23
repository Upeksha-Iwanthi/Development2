package com.example.demo.service;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ModuleClass;

import java.util.List;

public interface ModuleClassService {
    String getClassPathFromSvnClassPath(final String svnClassPath);
    String getModuleFromSvnURL(final String svnURL);
    ModuleClass getModuleClass(String module, String classPath);
    void setFunctionalAreaClassesForModuleClass(ModuleClass moduleClass, List<FunctionalArea> faList, String issueId);
}
