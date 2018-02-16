package com.example.demo.service.impl;

import com.example.demo.service.ModuleClassService;
import org.springframework.stereotype.Service;

@Service
public class ModuleClassServiceImpl implements ModuleClassService {

    @Override
    public String getModuleFromSvnURL(String svnURL){
        int idx1 = svnURL.indexOf("/branches");
        String url = svnURL.substring(0, idx1);
        String module = url.substring(url.lastIndexOf("/")+1);

        return module;
    }

    @Override
    public String getClassPathFromSvnClassPath(String svnClassPath){

        int idx2 = svnClassPath.indexOf("/se/");
        String path = svnClassPath.substring(idx2, svnClassPath.length()).replaceAll("/",".");
        String classPath = path.substring(1,path.length());

        return classPath;
    }

}
