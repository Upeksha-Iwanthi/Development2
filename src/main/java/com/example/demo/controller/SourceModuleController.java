package com.example.demo.controller;

import com.example.demo.persistence.SourceModule;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.service.SourceModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SourceModuleController {

    @Autowired
    private SourceModuleService sourceModuleService;

    @Autowired
    private SourceModuleRepository sourceModuleRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "api/saveData",method = RequestMethod.POST)
    public @ResponseBody
    String saveSourceModuleData(@RequestBody final SourceModule sourceModule){
        sourceModuleRepository.save(sourceModule);
//        sourceModuleService.saveData(sourceModule);
        return "faerfg";

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "api/checkData",method = RequestMethod.GET)
    public @ResponseBody
    SourceModule checkSourceModuleData() {
        List<SourceModule> list = sourceModuleRepository.findBySvnURL("url_ab");
        return list.get(0);

    }
}
