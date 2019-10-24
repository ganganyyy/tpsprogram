package com.ssm.tpssystem.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public interface ImportService {
    public List getListByExcel(InputStream inputStream,String filename) throws Exception;
    public Workbook getWorkbook(InputStream inputStream,String filename) throws Exception;
}
