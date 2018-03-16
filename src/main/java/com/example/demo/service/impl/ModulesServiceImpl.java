package com.example.demo.service.impl;

import com.example.demo.persistence.Modules;
import com.example.demo.persistence.ModulesDataService;
import com.example.demo.repository.ModulesRepository;
import com.example.demo.service.ModulesService;
import com.example.demo.service.apps.MySVNRepositoryFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ModulesServiceImpl implements ModulesService {

    @Autowired
    private ModulesRepository moduleRepository;

    @Autowired
    private ModulesDataService modulesDataService;

    @Autowired
    private MySVNRepositoryFactory mySVNRepositoryFactory;

    public Modules saveData(Modules module){
        boolean isDuplicate = isBranchDuplicate(module);
        if (isDuplicate)
        {
            throw new RuntimeException("Duplicate Detected");
        }
        else if (isInvalidRepository(module))
        {
            throw new RuntimeException("Invalid Repository");
        }
        return moduleRepository.save(module);
    }

    private  boolean isBranchDuplicate( final Modules module)
    {
        final List<Modules> db = moduleRepository.findBySvnURL(module.getSvnURL());
        if (db.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean isInvalidRepository(final Modules module)
    {
        boolean returnValue = false;
        try {
            final SVNRepository repository = mySVNRepositoryFactory.create(module.getSvnURL());
            long rev = repository.getLatestRevision();
        } catch (SVNException e) {
            e.printStackTrace();
            returnValue = true;
        }
        return returnValue;
    }

//    public void saveData(){
//
//        String filename = "J:\\Branches.xlsx";
//
//        // Create an ArrayList to store the data read from excel sheet.
//        List<List<XSSFCell>> sheetData = new ArrayList();
//
//        try  {
//            OPCPackage pkg = OPCPackage.open(new FileInputStream(new File("J:\\Branches.xlsx")));
//            // Create an excel workbook from the file system.
//            XSSFWorkbook workbook = new XSSFWorkbook(pkg);
//            // Get the first sheet on the workbook.
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            // When we have a sheet object in hand we can iterator on
//            // each sheet's rows and on each row's cells. We store the
//            // data read on an ArrayList so that we can printed the
//            // content of the excel to the console.
//            Iterator rows = sheet.rowIterator();
//            while (rows.hasNext()) {
//                XSSFRow row = (XSSFRow) rows.next();
//                Iterator cells = row.cellIterator();
//
//                List<XSSFCell> data = new ArrayList<>();
//                while (cells.hasNext()) {
//                    XSSFCell cell = (XSSFCell) cells.next();
//                    data.add(cell);
//                }
//                sheetData.add(data);
//            }
//
//            for (List<XSSFCell> rowData : sheetData) {
//                Modules sm_Int = new Modules();
//                Modules sm_Dev = new Modules();
//
//                sm_Int.setSvnURL(rowData.get(0).toString());
//                sm_Int.setType("Target");
//                sourceModuleRepository.save(sm_Int);
//
//                sm_Dev.setSvnURL(rowData.get(0).toString().replaceAll("_Int","_Dev"));
//                sm_Dev.setType("Source");
////                List<String> issueList = modulesDataService.getIssueList(rowData.get(0).toString().replaceAll("_Int","_Dev"));
////                sm_Dev.setIssueList(issueList);
//                sourceModuleRepository.save(sm_Dev);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//    }
}
