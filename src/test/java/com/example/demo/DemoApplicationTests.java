package com.example.demo;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.Data.IssueSearchResultRow;
import com.example.demo.controller.FunctionalAreaController;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

//	Repositories------------------------------------------------------------------------
	@Autowired
	FunctionalAreaRepository functionalAreaRepository;

	@Autowired
    ModulesService sourceModuleService;

	@Autowired
	FunctionalAreaClassRepository functionalAreaClassRepository;

	@Autowired
	ModuleClassRepository moduleClassRepository;

	@Autowired
	SourceModuleSchedulerService sourceModuleSchedulerService;

	@Autowired
	TargetModuleSchedulerService targetModuleSchedulerService;

	@Autowired
    FunctionalAreaFinderService functionalAreaFinderService;

	@Autowired
	FunctionalAreaController functionalAreaController;

	@Autowired
	FindFunctionalAreaByClassService findFunctionalAreaByClassService;

//	@Test
//	public void testFunctionalAreaFinder() throws Exception{
//        IssueSearchResult list = functionalAreaController.findFunctionalAreasForIssueId("IMOD-84705");
//        for(IssueSearchResultRow result:list.getIssueSearchResultList()) {
//            System.out.println(result.getClassPath()+"\t"+result.getModule()+"\t"+result.getFunctionalAreas()+"\t"+result.getPercentage()+"\t"+result.getIssueList()+"\n");
//        }
//	}

//	@Test
//	public void testFindFAByClass(){
//		List<IssueSearchResultRow> list = findFunctionalAreaByClassService.findFunctionalAreasByClass("se.cambio.cosmic.eped.impl.ServiceConfiguration");
//		for(IssueSearchResultRow result:list) {
//			System.out.println(result.getClassPath()+"\n"+result.getModule()+"\n"+result.getFunctionalAreas()+"\n"+result.getProductArea()+"\n"+result.getPercentage());
//			for(String ids:result.getJiraIssueIds()) {
//				System.out.println(ids);
//			}
//		}
//	}

//	@Test
//	public void productAreaTest() throws Exception {
//		sourceModuleSchedulerService.updateTablesForSourceModules();
//	}

//	@Test
//	public void saveBranches() throws Exception{
//		sourceModuleService.saveData();
//	}

//	@Test
//	public void saveBranches() throws Exception{
//		sourceModuleService.saveData();
//	}

//

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

//	@Test
//	public void saveFACDataTest(){
//		List<FunctionalArea> functionalAreas = functionalAreaRepository.findByName("fa1");
//		List<ModuleClass> moduleClasses =moduleClassRepository.findByModule("module1");
//		FunctionalAreaClass functionalAreaClass1 = new FunctionalAreaClass(100);
//
//		functionalAreaClass1.setFunctionalArea(functionalAreas.get(0));
//		functionalAreaClass1.setModuleClass(moduleClasses.get(0));
//		functionalAreaClassRepository.save(functionalAreaClass1);
//		Assert.assertNotNull(functionalAreaClassRepository.findOne(Long.valueOf(1)));
//	}

//	@Test
//	public void saveSMDataTest(){
//		sourceModuleService.saveData();
//		Assert.assertNotNull(sourceModuleRepository.findOne(Long.valueOf(1)));
//	}
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