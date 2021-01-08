package com.cas.components.beanAware.webssh.service;

import com.cas.components.beanAware.webssh.pojo.FileDTO;
import com.cas.components.beanAware.webssh.pojo.SFTPData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

/**
 * Sftp业务逻辑类
 * @ClassName SftpService
 * @Author luo jin jiang
 * @Date 2020/3/16 20:21
 * @Version 1.0
 */
public interface SftpService {


    /**
     * 连接sftp服务器
     * @param session
     * @param sftpData
     */
    void connect(HttpSession session, SFTPData sftpData) throws Exception;

    /**
     * 断开连接
     * @param session
     */
    void disConnect(HttpSession session) throws Exception;
    /**
     * 根据路径或者文件夹和文件列表
     * @param path 路径
     * @return
     * @throws Exception
     */
    List<FileDTO> listFiles(HttpSession session, String path) throws Exception;

    /**
     * 上传文件接口
     * @param uploadFile 源文件地址
     * @param targetDir 上传目录
     * @throws Exception
     */
    void put(HttpSession session, MultipartFile uploadFile, String targetDir)throws Exception;

    /**
     * 下载文件接口
     * @param fullFileName 下载文件
     * @throws Exception
     */
    InputStream get(HttpSession session, String fullFileName)throws Exception;


    /**
     * 下载文件接口
     * @param fullFileName 删除文件路径
     * @throws Exception
     */
    void delete(HttpSession session, String fullFileName)throws Exception;


}
