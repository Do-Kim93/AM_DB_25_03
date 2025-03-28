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

select now();

select '제목1';

select concat('제목',' 1');

select substring(RAND() * 1000 from 1 for 2);

insert into article
set regDate = now(),
    updateDate = now(),
    title = concat('제목',substring(RAND() * 1000 from 1 for 2)),
    `body` = concat('내용',substring(RAND() * 1000 from 1 for 2));

select count (*) from article;
select id from article;
SELECT LAST_INSERT_ID();