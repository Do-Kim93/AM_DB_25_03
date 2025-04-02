DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;
truncate table article;
CREATE TABLE article(
                        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        title CHAR(100),
                        `body` TEXT NOT NULL
);
truncate table member;
CREATE TABLE member(
                        id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        loginId CHAR(100) NOT NULL unique,
                        loginPw TEXT NOT NULL,
                        name CHAR(100) NOT NULL
);
# auto_increment 초기화 하는 코드
SET @count=0;
UPDATE article SET id =@count:=@count+1;

insert INTO article SET regDate = NOW(),updateDate = NOW(),title = '제목', `body` = '내용';

create table article(
                        id int(10) unsigned not null primary key auto_increment,
                        regDate datetime not null,
                        updateDate datetime not null,
                        title char(100) not null,
                        `body` text not null
);

select *
from article;

insert into article
set regDate = now(),
    updateDate = now(),
    title = concat('제목',substring(RAND() * 1000 from 1 for 2)),
    `body` = concat('내용',substring(RAND() * 1000 from 1 for 2));

select count (*) from article where id = 19;

/*
# 1. 손흥민의 주문 개수는? ???
SELECT COUNT(*) FROM t_shopping WHERE userName = '손흥민';
SELECT COUNT(userName) FROM t_shopping;
# 2. 손흥민이 산 상품은? ???
SELECT pname FROM t_shopping WHERE userName = '손흥민';
# 3. 스커트를 산 사람은? ???
SELECT DISTINCT userName FROM t_shopping WHERE pname = '스커트';
# 4. 가장 많이 주문한 사람의 아이디와 이름, 주문개수는? ???
SELECT * FROM t_shopping WHERE MAX(price);
SELECT userName, userId, COUNT(*) AS 'order' FROM t_shopping  GROUP BY userId ORDER BY `order` DESC LIMIT 1;
# 5. 소지섭이 사용한 총 금액은? ???
SELECT SUM(price) FROM t_shopping WHERE userName = '소지섭';
*/
/*
 # 1. 손흥민의 주문 개수는? ???
SELECT COUNT(userNo) AS '주문개수' FROM t_order INNER JOIN t_user ON t_order.userNo = t_user.id WHERE t_user.userName = '손흥민';
# 2. 손흥민이 산 상품은? ??? id1
SELECT DISTINCT t_product.pname FROM t_product INNER JOIN t_order ON t_product.id = t_order.productNo INNER JOIN t_user ON t_order.userNo = t_user.id WHERE t_user.userName = '손흥민';
# 3. 스커트를 산 사람은? ???
SELECT DISTINCT t_user.userName AS '고객이름' FROM t_user INNER JOIN t_order ON t_user.id = t_order.userNo INNER JOIN t_product ON t_product.id = t_order.productNo WHERE t_product.pname = '스커트';
# 4. 가장 많이 주문한 사람의 아이디와 이름, 주문개수는? ???
SELECT t_user.userId AS '고객아이디', t_user.userName AS '고객이름', COUNT(t_order.userNo) AS '주문수량' FROM t_order INNER JOIN t_user ON t_order.userNo = t_user.id GROUP BY t_user.userName ORDER BY 주문수량 DESC LIMIT 1;
# 5. 소지섭이 사용한 총 금액은? ???
SELECT DISTINCT SUM(t_product.price) AS '사용금액' FROM t_user INNER JOIN t_order ON t_user.id = t_order.userNo INNER JOIN t_product ON t_product.id = t_order.productNo WHERE t_user.userName = '소지섭';
 */