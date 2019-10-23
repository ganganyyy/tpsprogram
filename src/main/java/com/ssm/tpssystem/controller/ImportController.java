package com.ssm.tpssystem.controller;

import com.ssm.tpssystem.domain.Interaction;
import com.ssm.tpssystem.domain.Trade;
import com.ssm.tpssystem.domain.Transaction;
import com.ssm.tpssystem.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/import")
public class ImportController {
    @Autowired
    private ImportService importService;
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String CreateTrade(HttpServletRequest httpServletRequest) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
        MultipartFile file = multipartHttpServletRequest.getFile("filename");
        if(file.isEmpty())
            return "the file is empty";
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = importService.getListByExcel(inputStream,file.getOriginalFilename());
        inputStream.close();

        for(int i = 0;i < list.size();i++){
            List<Object> subList = list.get(i);

        }
        return "STP success";



    }

}
