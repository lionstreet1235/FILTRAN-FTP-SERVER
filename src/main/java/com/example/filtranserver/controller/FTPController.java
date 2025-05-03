package com.example.filtranserver.controller;

import com.example.filtranserver.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ftp")
public class FTPController {

    @Autowired
    private FtpService ftpService;

    // Kết nối đến FTP server
    @PostMapping("/connect")
    public ResponseEntity<String> connectToFTP(@RequestParam String server, @RequestParam String username, @RequestParam String password) {
        boolean success = ftpService.connectToFTPServer(server, username, password);
        return success ? ResponseEntity.ok("Connected to FTP Server") : ResponseEntity.status(403).body("Connection failed");
    }

    // Upload file lên FTP server
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("remoteFilePath") String remoteFilePath) {
        boolean success = ftpService.uploadFile(file, remoteFilePath);
        return success ? ResponseEntity.ok("File uploaded successfully") : ResponseEntity.status(500).body("Upload failed");
    }


    // Ngắt kết nối với FTP server
    @PostMapping("/disconnect")
    public ResponseEntity<String> disconnect() {
        ftpService.disconnect();
        return ResponseEntity.ok("Disconnected from FTP Server");
    }
}
