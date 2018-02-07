package com.example.demo.service;

import com.example.demo.persistence.ModuleClass;

public interface ModuleClassService {
    String getClassPathFromSvnClassPath(final String svnClassPath);
    String getModuleFromSvnURL(final String svnURL);
}
