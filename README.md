﻿# Mô tả:
- Ứng dụng về quản lý danh sách các phim hay đã xem.
- Dịch ngôn ngữ các bộ phim từ file SRT.
## Vấn đề:
- Dịch ngôn ngữ. Có 2 cách dịch ngôn ngữ: dịch bằng cách gọi các WEB API được cung cấp sẵn và dịch bằng công cụ dịch tự thiết kế.
### Dịch bằng Web API
- Bằng việc sử dụng Web API chúng ta sẽ dễ dàng có được công cụ dịch ngôn ngữ nhanh chóng, đa ngôn ngữ. 
- Phổ biến nhất là công cụ Google Translate.
- Google Translate đã được phát triển rất mạnh, không còn từ gì để tả.
- Có rất nhiều project hướng dẫn sử dụng Google Translate.
- Cần tạo key để sử dụng.
- Có tính phí.
- Nếu không tính  phí thì cũng giới hạn số lần request trong ngày.
- Dịch rất là ngốc. Khi dịch bằng translate.google.com thì thấy tuy còn nhiều chỗ sai, nhưng khá là ok, nhưng khi dùng API dịch thử thì thấy rất là ngu. Cùng 1 text nhưng dịch bằng cách gọi API và dịch bằng translate.google.com thì cho kết quả khác nhau => cần tìm hiểu nguyên nhân.
### Dịch bằng công cụ dịch tự chế
- Nguyên lý cơ bản của việc dịch là: Word to Word. 
- Params là 1 đoạn văn thì chuyển đoạn văn thành nhiều câu rồi dịch câu.
- Params là 1 câu thì chuyển câu thành nhiều từ rồi dịch từ.
- Dịch từ: query keyword trong db để tìm ra translate của từ. Nếu Db chưa có keyword thì tạo mới và update.
- Đây là cách dịch cơ bản.
**- Chưa có suy nghĩ cụ thể cho cách này**
### Dịch bằng cách gọi VNEDICT của Hồ Ngọc Đức
- VNEDICT là công cụ từ điển của tác giả Hồ Ngọc Đức. 
	
		Giới thiệu về Hồ Ngọc Đức 
			*Tôi sẽ trình bày về tác giả này sau*
			
- Dùng phần mềm VNEDICT như 1 Web API server để query từ điển.
- Cách dùng: 
	- Start VNEDICT như server bằng cách chạy file vd-start.bat
	- Dùng Httpsrequest để query http://localhost:8080/td?word=hello&db=ev&fmt=u&k=vni
	- Các key: word, db, fmt, k
- Cách này mới chỉ dịch từng từ, nên sẽ cần áp dụng các nguyên lý Word to Word.
- Cách này được tìm hiểu trong ngày 22/10/2018, cũng là ngày bắt đầu viết README.md.
- **Chưa code thử**.


# Export DB MySQL
mysqldump -u YourUser -p YourDatabaseName > wantedsqlfile.sql

- Mở Power shell ở thư mục Mysql/bin, không sử dụng client command line.
- Chỉ xuất ra db table, không có tạo db name, không có data.
=> dùng Data export của MySql sẽ xuất ra toàn bộ database gồm table và data trong 1 file sql.

