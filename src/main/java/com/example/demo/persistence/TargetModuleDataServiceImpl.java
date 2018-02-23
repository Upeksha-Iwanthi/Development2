//package com.example.demo.persistence;
//
//import com.example.demo.repository.TargetModuleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class TargetModuleDataServiceImpl implements TargetModuleDataService {
//
//    @Autowired
//    TargetModuleRepository targetModuleRepository;
//
//    @Override
//    public List<TargetModule> getConfiguredBranchList(){
//        final List<TargetModule> data = new ArrayList<TargetModule>();
//        for (final TargetModule obj : targetModuleRepository.findAll())
//        {
//            data.add(obj);
//        }
//        return data;
//    }
//
//}
