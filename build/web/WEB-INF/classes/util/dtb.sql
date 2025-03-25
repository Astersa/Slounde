INSERT INTO Subcription (SubType)
VALUES 
('Free'), 
('Premium');


INSERT INTO Users (DoB, Name, Username, Password, AvatarUrl, Mail, Role, SubId)
VALUES 
('2001-05-19', N'Nguyễn Tuấn Dũng', N'tuandung', N'dungnguyen@123', , N'tuandung1905@gmail.com', 0, 1),
();


INSERT INTO Artists ([Name], Followers, ArtistUrl)
VALUES 
(N'Sơn Tùng MTP', 189031, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/5/9/6/9/59696c9dba7a914d587d886049c10df6.jpg'),
(N'Big Bang', 304571, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/2/2/3/3/223327eb3eca92cc50d58aaf985f17f3.jpg'),
(N'Dương Domic', 50738, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/c/9/7/9/c97982b6ae9f7a52196be520f6f25d46.jpg'),
(N'RHYDER', 287910, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/5/c/1/f/5c1fa6ef95fe2f63044eaca0a11d304b.jpg'),
(N'AMEE', 103756, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/d/b/8/2/db82811d8db60180f8251866fd2ede7d.jpg'),
(N'ERIK', 172518, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/6/e/8/b/6e8bc06300770de22c3a56db89100a4c.jpg'),
(N'Alan Walker', 502731, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/b/a/6/b/ba6b56ca02dd06f37693d945acfcdad9.jpg'),
(N'SZA', 205892, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/2/7/9/a/279a391af4107610977011d29d5f7cec.jpg'),
(N'Maroon 5', 593107, 'https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/avatars/3/7/5/3/375337f6d1cb508665d89f2e7d8fc51a.jpg'),
(N'Kendrick Lamar', 423815, 'https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/avatars/a/9/6/9/a969f384fecbfde6ae7ff33b98979f80.jpg'),
(N'Quân AP', 130749, 'https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/avatars/e/e/9/b/ee9b669745d05cb80be5cf63434d6e10.jpg'),
(N'Taylor Swift', 601882, 'https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/avatars/c/8/5/a/c85afd30bf1bc4224e2fd8b59bdc9d56.jpg'),
(N'Shawn Mendes', 400173, 'https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/avatars/6/2/5/f/625fed25e4dbb829c0666b665ab76a2d.jpg');

INSERT INTO Albums ([Name], AlbumUrl, Likes, ArtistId)
VALUES 
-- Sơn Tùng
(N'Nơi Này Có Anh (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/covers/3/a/3a9e48bc4df7bbde3acea30cc267f609_1487066528.jpg', 70114, 1),
(N'Muộn Rồi Mà Sao Còn (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/e/f/6/c/ef6c9d4b97048c9911ea6069e968dadb.jpg', 67531, 1),
(N'Hãy Trao Cho Anh (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/5/f/b/c/5fbcebb0cb33b8cf404f2fd4e30305f9.jpg', 100832, 1),
(N'Có Chắc Yêu Là Đây (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/9/d/7/9/9d79ebd03bbb6482bab748d67bbe0afb.jpg', 50381, 1),
(N'Chúng Ta Của Hiện Tại (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/e/f/6/c/ef6c9d4b97048c9911ea6069e968dadb.jpg', 49103, 1),
-- Big Bang
(N'Still Life (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/7/b/b/e/7bbe2786fe86fc61f0c2968a11bd2c36.jpg', 54321, 2),
(N'Haru Haru (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/7/b/b/e/7bbe2786fe86fc61f0c2968a11bd2c36.jpg', 182345, 2),
(N'A', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/4/2/3/b/423bc432440a05971cdcd0db68f622d4.jpg', 298567, 2),
(N'D', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/c/4/1/7/c417f84441f30b8cabacdb7d3b35a6fa.jpg', 171456, 2),
-- Dương Domic
(N'Dữ liệu quý', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/8/c/1/6/8c166e2b9a0e45ca9a6c7bef40a81f74.jpg', 81234, 3),
-- RHYDER
(N'Nỗi đau đính kèm (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/1/8/d/3/18d3a5c6df2dbe7d09d5b343b43b0b72.jpg', 90215, 4),
(N'Chịu Cách Mình Nói Thua (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/e/3/d/5/e3d541870421859d108f5f982642bd36.jpg', 81234, 4),
(N'Dân Chơi Sao Phải Khóc (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/7/1/b/9/71b955cff065c904f0955d93a2925a83.jpg', 68105, 4),
-- AMEE
(N'MỘNGMEE', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/4/4/7/4/44749ae7547640b7f3edb4e45502a68f.jpg', 123987, 5),
-- ERIK
(N'Có Tất Cả Nhưng Thiếu Anh (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/6/b/e/4/6be430e44902db6e3e28e8a39034f4df.jpg', 135678, 6),
(N'Chạm Đáy Nỗi Đau (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/9/b/4/0/9b40a25f9a606520333fa79f1fdf5ea6.jpg', 147321, 6),
(N'Sau Tất Cả', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/covers/5/5/55a2e33a5d4d6a70f5144181c28eacb0_1452855672.jpg', 118734, 6),
-- Alan Walker
(N'Better Off (Alone, Pt. III) (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/7/c/4/4/7c444e0ec2c027b189fd4c46cd231db5.jpg', 223456, 7),
(N'Hero (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/7/c/2/9/7c29c7c47e77934bef7f5a95d98181cf.jpg', 205678, 7),
(N'Endless Summer (Single)', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/3/2/4/1/3241eea41a87444275055cad40dfd1a8.jpg', 199876, 7),
-- SZA
(N'SOS', 'https://dl.dropboxusercontent.com/scl/fi/3duckyi0c87bzmyvo8ieo/thumb.jpg?rlkey=oj8c6famsbzlzl9puppa5ghpv&st=iagn89yf&dl=0', 256789, 8),
-- Maroon 5
(N'V', 'https://photo-resize-zmp3.zmdcdn.me/w500_r1x1_jpeg/cover/3/5/f/f/35ffba84cb6253ced2127c7f5e1b3194.jpg', 279345, 9),
(N'Singles', 'https://dl.dropboxusercontent.com/scl/fi/f2sepfp0k15jjeevdnere/600x600bf-60.jpg?rlkey=ict84xgoxw1gfxditqyzexyj9&st=da1hvbxb&dl=0', 269234, 9),
-- Kendrick Lamar
(N'GNX', 'https://photo-resize-zmp3.zadn.vn/w360_r1x1_jpeg/cover/f/6/0/8/f60838f8a14ca1c11aae78fa92c226c0.jpg', 259123, 10);


INSERT INTO Genre (Name) 
VALUES 
    ('Vpop'),
    ('Us-Uk'),
    ('Kpop'),
    ('Rap');

INSERT INTO SongGenre (SongId, GenreId) 
VALUES 
    (1, 1),  
    (2, 1),  
    (3, 1),  
    (4, 1),  
    (5, 1),  
    (6, 3),  
    (7, 3),  
    (8, 3),  
    (9, 3),  
    (10, 1),  
    (11, 1),  
    (12, 1),  
    (13, 1),  
    (14, 1),  
    (15, 1),  
    (16, 1),  
    (17, 1),  
    (18, 1),  
    (19, 1),  
    (20, 1),  
    (21, 1),  
    (22, 1),  
    (23, 1),  
    (24, 1),  
    (25, 1),  
    (26, 2),  
    (27, 2),  
    (28, 2),  
    (29, 2),  
    (30, 2),  
    (31, 2),  
    (32, 2),  
    (33, 2),  
    (34, 2),  
    (35, 2),  
    (36, 2),  
    (37, 2),  
    (38, 2),  
    (39, 2),  
    (40, 2),  
    (41, 2),  
    (42, 2);

