package com.example.demo.service.impl;

import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.ModuleClass;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.repository.FunctionalAreaRepository;
import com.example.demo.repository.ModuleClassRepository;
import com.example.demo.service.ModuleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleClassServiceImpl implements ModuleClassService {

    @Autowired
    ModuleClassRepository moduleClassRepository;

    @Autowired
    FunctionalAreaRepository functionalAreaRepository;

    @Autowired
    FunctionalAreaClassRepository functionalAreaClassRepository;

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
        if(idx2>=0) {
            String path = svnClassPath.substring(idx2, svnClassPath.length()).replaceAll("/", ".");
            String classPath = path.substring(1, path.length());
            return classPath;
        }else{
            return svnClassPath;
        }
    }

    @Override
    public ModuleClass getModuleClass(String module, String classPath){
        //                  check the DB
        final List<ModuleClass> moduleClassList = moduleClassRepository.findByModuleAndClassPath(module,classPath);

        final ModuleClass moduleClass = moduleClassList.isEmpty()? new ModuleClass(module,classPath) : moduleClassList.get(0);

        return moduleClass;
    }

    @Override
    public void setFunctionalAreaClassesForModuleClass(ModuleClass moduleClass, List<FunctionalArea> faList, String issueId){
        List<FunctionalAreaClass> functionalAreaClasses = moduleClass.getFunctionalAreaClasses();
        for (final FunctionalArea fa : faList) {

            List<FunctionalArea> functionalAreasDB = functionalAreaRepository.findByName(fa.getName());
            FunctionalAreaClass functionalAreaClass = new FunctionalAreaClass();
            functionalAreaClass.setJiraIssueId(issueId);
            functionalAreaClass.setFunctionalArea(functionalAreasDB.get(0));
            functionalAreaClass.setModuleClass(moduleClass);

            List<FunctionalAreaClass> functionalAreaClassesDB = functionalAreaClassRepository.findByJiraIssueIdAndFunctionaArea(issueId,functionalAreasDB.get(0));
            if(!functionalAreaClassesDB.isEmpty()){

            }else{
                functionalAreaClasses.add(functionalAreaClass);
            }
        }

        moduleClass.setFunctionalAreaClasses(functionalAreaClasses);
    }

}
