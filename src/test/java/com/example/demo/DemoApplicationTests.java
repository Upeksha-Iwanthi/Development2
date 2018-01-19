package com.example.demo;

import com.example.demo.persistance.*;
import com.example.demo.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

//	Repositories------------------------------------------------------------------------
	@Autowired
	FunctionalAreaRepository functionalAreaRepository;

	@Autowired
	ProductAreaRepository productAreaRepository;

	@Autowired
	SourceModuleRepository sourceModuleRepository;

	@Autowired
	FunctionalAreaClassRepository functionalAreaClassRepository;

	@Autowired
	ModuleClassRepository moduleClassRepository;


//	@Test
//	public void showModuleClassAndFaClassDataTest(){
//		List<FunctionalAreaClass> faClass = functionalAreaClassRepository.findByFunctionalAreaId(100);
//		List<ModuleClass> mClass = moduleClassRepository.findByModule("module");
//
//		assertEquals(1090, faClass.get(0).getJiraIssueId());
//		assertEquals("classPath", mClass.get(0).getClassPath());
//	}

//	@Test
//	public void saveProdAreaDataTest(){
//		ProductArea theatreManagement = new ProductArea("Theatre Management");
//		ProductArea other = new ProductArea("Other");
//
//		productAreaRepository.save(theatreManagement);
//		Assert.assertNotNull(productAreaRepository.findOne(Long.valueOf(1)));
//
//		productAreaRepository.save(other);
//		Assert.assertNotNull(productAreaRepository.findOne(Long.valueOf(2)));
//	}
//
//	@Test
//	public void saveMCDataTest(){
//		ModuleClass moduleClass1 = new ModuleClass("module1","classPath1");
//		ModuleClass moduleClass2 = new ModuleClass("module2","classPath2");
//
//		moduleClassRepository.save(moduleClass1);
//		Assert.assertNotNull(moduleClassRepository.findOne(Long.valueOf(1)));
//
//		moduleClassRepository.save(moduleClass2);
//		Assert.assertNotNull(moduleClassRepository.findOne(Long.valueOf(2)));
//
//	}

	@Test
	public void saveFACDataTest(){
		List<FunctionalArea> functionalAreas = functionalAreaRepository.findByName("fa1");
		List<ModuleClass> moduleClasses =moduleClassRepository.findByModule("module1");
		FunctionalAreaClass functionalAreaClass1 = new FunctionalAreaClass(100,functionalAreas.get(0));

		functionalAreaClass1.setModuleClass(moduleClasses.get(0));
		functionalAreaClassRepository.save(functionalAreaClass1);
		Assert.assertNotNull(functionalAreaClassRepository.findOne(Long.valueOf(1)));
	}
//
//	@Test
//	public void saveFADataTest(){
//		List<ProductArea> theatreManagement = productAreaRepository.findByName("Theatre Management");
//		List<ProductArea> other = productAreaRepository.findByName("Other");
//
//		FunctionalArea fa1 = new FunctionalArea("fa1",theatreManagement.get(0));
//		FunctionalArea fa2 = new FunctionalArea("fa2",other.get(0));
//
//		functionalAreaRepository.save(fa1);
//		Assert.assertNotNull(functionalAreaRepository.findOne(Long.valueOf(1)));
//
//		functionalAreaRepository.save(fa2);
//		Assert.assertNotNull(functionalAreaRepository.findOne(Long.valueOf(2)));
//
//	}


}