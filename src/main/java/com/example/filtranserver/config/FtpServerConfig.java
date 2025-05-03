package com.example.filtranserver.config;

import jakarta.annotation.PostConstruct;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FtpServerConfig {

    private FtpServer server;
    private UserManager userManager;

    @PostConstruct
    public void startServer() throws Exception {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(2121);

        serverFactory.addListener("default", factory.createListener());

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("src/main/resources/users.properties"));

        this.userManager = userManagerFactory.createUserManager();
        serverFactory.setUserManager(userManager);

        this.server = serverFactory.createServer();
        server.start();
        System.out.println("FTP Server started on port 2121");
    }

    public UserManager getUserManager() {
        return this.userManager;
    }
}
