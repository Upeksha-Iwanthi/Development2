package com.example.demo.service.impl;

import com.example.demo.persistence.SourceModule;
import com.example.demo.repository.SourceModuleRepository;
import com.example.demo.service.SourceModuleService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SourceModuleServiceImpl implements SourceModuleService {

    @Autowired
    private SourceModuleRepository sourceModuleRepository;

    public void saveData(){

        String filename = "J:\\Branches.xlsx";

        // Create an ArrayList to store the data read from excel sheet.
        List<List<XSSFCell>> sheetData = new ArrayList();

        try  {
            OPCPackage pkg = OPCPackage.open(new FileInputStream(new File("J:\\Branches.xlsx")));
            // Create an excel workbook from the file system.
            XSSFWorkbook workbook = new XSSFWorkbook(pkg);
            // Get the first sheet on the workbook.
            XSSFSheet sheet = workbook.getSheetAt(0);

            // When we have a sheet object in hand we can iterator on
            // each sheet's rows and on each row's cells. We store the
            // data read on an ArrayList so that we can printed the
            // content of the excel to the console.
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List<XSSFCell> data = new ArrayList<>();
                while (cells.hasNext()) {
                    XSSFCell cell = (XSSFCell) cells.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }

            for (List<XSSFCell> rowData : sheetData) {
                SourceModule sm_Int = new SourceModule();
                SourceModule sm_Dev = new SourceModule();

                sm_Int.setSvnURL(rowData.get(0).toString());
                sm_Int.setType("Int");
                sourceModuleRepository.save(sm_Int);

                sm_Dev.setSvnURL(rowData.get(0).toString().replaceAll("_Int","_Dev"));
                sm_Dev.setType("Dev");
                sourceModuleRepository.save(sm_Dev);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
