package com.cas.components.beanAware.webssh.pojo;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import lombok.Data;

/**
 * @ClassName SFTP
 * @Author luo jin jiang
 * @Date 2020/3/21 17:10
 * @Version 1.0
 */
@Data
public class SFTPConnectInfo {
    private Session session;//会话
    private Channel channel;//连接通道
    private ChannelSftp sftp;// sftp操作类
}
