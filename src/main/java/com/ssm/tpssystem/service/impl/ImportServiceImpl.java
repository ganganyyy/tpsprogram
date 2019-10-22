package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.service.ImportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService{
    @Override
    public List getListByExcel(InputStream inputStream, String filename) throws IOException {
        List list = new ArrayList<>();
        Workbook workbook = this.getWorkbook(inputStream,filename);
        if(workbook == null)
            return null;
        Sheet sheet;
        Row row;
        Cell cell;

        for(int i = 0;i < workbook.getNumberOfSheets();i++){
            sheet = workbook.getSheetAt(i);
            if(sheet == null)
                continue;
            for (int j = sheet.getFirstRowNum();j < sheet.getLastRowNum();j++){
                row = sheet.getRow(i);
                if(row == null)
                    continue;

                List<Object> subList = new ArrayList<>();
                for (int k = row.getFirstCellNum();k < row.getLastCellNum();k++){
                    cell = row.getCell(k);
                    subList.add(cell);
                }
                list.add(subList);
            }
        }
        workbook.close();
        return list;
    }

    @Override
    public Workbook getWorkbook(InputStream inputStream, String filename) {
        return null;
    }
}
