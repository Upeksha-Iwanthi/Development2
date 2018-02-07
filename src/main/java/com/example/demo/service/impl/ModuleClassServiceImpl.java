package com.example.demo.service.impl;

import com.example.demo.service.ModuleClassService;
import org.springframework.stereotype.Service;

@Service
public class ModuleClassServiceImpl implements ModuleClassService {

    @Override
    public String getModuleFromSvnURL(String svnURL){
        int idx1 = svnURL.indexOf("/branches");
        String module = svnURL.substring(1, idx1);
        return module;
    }

    @Override
    public String getClassPathFromSvnClassPath(String svnClassPath){

        int idx2 = svnClassPath.indexOf("/se/");
        String classPath = svnClassPath.substring(idx2, svnClassPath.length()).replaceAll("/",".");

        return classPath;
    }

}
