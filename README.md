# Luận Án: Giải Pháp Bảo Mật Cho Ứng Dụng FTP Client-Server

## Mô tả
Đây là đồ án tốt nghiệp với mục tiêu xây dựng một giải pháp **FTP Client-Server an toàn**, trong đó:
- **Server**: sử dụng **Apache FtpServer** để triển khai dịch vụ FTP với các cơ chế bảo mật như xác thực người dùng, mã hóa dữ liệu (TLS/SSL), giới hạn quyền truy cập.
- **Client**: ứng dụng web sử dụng **Spring Boot**, cho phép người dùng tương tác với server để tải lên/tải xuống/xem danh sách file một cách bảo mật.
---

## Kiến trúc tổng thể
+------------+ HTTPS +----------------+
| Web Client | <-----------------> | Spring Boot App|
+------------+ +--------+-------+
|
| FTPS (TLS)
|
+--------v--------+
| Apache FtpServer |
+------------------+
## Tính năng chính

### Server (Apache FtpServer)

- Xác thực người dùng bằng file `users.properties` hoặc JDBC.
- Mã hóa FTPS (TLS/SSL) với chứng chỉ tự ký hoặc CA.
- Giới hạn thư mục truy cập (home directory isolation).
- Ghi log truy cập FTP.

### Web Client (Spring Boot)

- Giao diện upload / download file đơn giản.
- Hiển thị danh sách file đã upload từ FTP server.
- Xác thực người dùng (form login hoặc token-based).
- Mã hóa kết nối client-server qua HTTPS.

---

## Hướng dẫn cài đặt

### Yêu cầu hệ thống
