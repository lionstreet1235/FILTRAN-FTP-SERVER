package com.example.filtranserver.service;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FtpService {

    private FTPClient ftpClient = new FTPClient();

    public boolean connectToFTPServer(String server, String username, String password) {
        try {
            ftpClient.connect(server);
            return ftpClient.login(username, password);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean uploadFile(MultipartFile file, String remoteFilePath) {
        try (InputStream input = file.getInputStream()) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFilePath, input);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
