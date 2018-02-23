//package com.example.demo.controller;
//
//import com.example.demo.persistence.TargetModule;
//import com.example.demo.repository.TargetModuleRepository;
//import com.example.demo.service.TargetModuleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class SourceModuleController {
//
//    @Autowired
//    private TargetModuleService sourceModuleService;
//
//    @Autowired
//    private TargetModuleRepository sourceModuleRepository;
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(path = "api/saveData",method = RequestMethod.POST)
//    public @ResponseBody
//    String saveSourceModuleData(@RequestBody final TargetModule sourceModule){
//        sourceModuleRepository.save(sourceModule);
////        sourceModuleService.saveData(sourceModule);
//        return "faerfg";
//
//    }
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(path = "api/checkData",method = RequestMethod.GET)
//    public @ResponseBody
//    TargetModule checkSourceModuleData() {
//        List<TargetModule> list = sourceModuleRepository.findBySvnURL("url_ab");
//        return list.get(0);
//
//    }
//}
