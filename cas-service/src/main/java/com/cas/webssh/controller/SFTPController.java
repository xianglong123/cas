package com.cas.service.webssh.controller;

import com.cas.service.webssh.pojo.FileDTO;
import com.cas.service.webssh.pojo.Result;
import com.cas.service.webssh.pojo.SFTPData;
import com.cas.service.webssh.service.SftpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName SFTPController
 * @Author luo jin jiang
 * @Date 2020/3/21 20:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/sftp")
@Slf4j
public class SFTPController {

    @Autowired
    SftpService sftpService;

    @PostMapping("/connect")
    public Result connect(HttpSession session, @RequestBody SFTPData sftpData) throws Exception {
        sftpService.connect(session, sftpData);
        return Result.ok();
    }

    @PostMapping("/exit")
    public Result disConnect(HttpSession session) throws Exception {
        sftpService.disConnect(session);
        return Result.ok();
    }


    @PostMapping("/ls")
    public Result<List<FileDTO>> listFiles(HttpSession session, String path) throws Exception {
        return Result.ok(sftpService.listFiles(session, path));
    }

    @PostMapping("/put")
    public Result put(HttpSession session, MultipartFile uploadFile, String targetDir) throws Exception {
        sftpService.put(session, uploadFile, targetDir);
        return Result.ok();
    }

    @GetMapping("/get")
    public void get(String fullFileName, HttpSession session, HttpServletResponse response) throws Exception {
        String fileName = fullFileName.substring(fullFileName.lastIndexOf("/") + 1);
        //设置下载响应头
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream inputStream = sftpService.get(session,fullFileName);
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copyLarge(inputStream,outputStream);
    }

    @PostMapping("/delete")
    public Result delete(String fullFileName, HttpSession session) throws Exception {
        sftpService.delete(session,fullFileName);
        return Result.ok();
    }


}
