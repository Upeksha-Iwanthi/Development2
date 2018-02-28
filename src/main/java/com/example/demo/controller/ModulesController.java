package com.example.demo.controller;

import com.example.demo.Data.ModuleSaveResponse;
import com.example.demo.persistence.Modules;
import com.example.demo.repository.ModulesRepository;
import com.example.demo.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ModulesController {

    @Autowired
    private ModulesService modulesService;

    @Autowired
    private ModulesRepository modulesRepository;


    @CrossOrigin(origins = "*")
    @RequestMapping( path = "api/saveModules", method = RequestMethod.POST)
    public @ResponseBody
    ModuleSaveResponse saveBranchData(@RequestBody final String branchURL , final String type)
    {
        Modules branch = new Modules();
        final ModuleSaveResponse response = new ModuleSaveResponse();

        branch.setRevision(0l);
        branch.setSvnURL(branchURL);
        branch.setType(type);

        try {
            final Modules  data = modulesService.saveData(branch);
            response.setMessage("Branch Added successfully");
        } catch (RuntimeException re) {
            response.setMessage(re.getMessage());
        }
        response.setBranchList(modulesRepository.findAll());
        return  response;
    }
}
