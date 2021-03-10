 desc guestbook;

-- insert
 insert into guestbook values(null,'안대혁','1234','테스트입니다.',now());

-- select
 select no, name, date_format(reg_date, '%Y-%m-%d %H:%i:%s'), contents from guestbook order by reg_date;
 
 -- delete
delete from guestbook where no = 1 and password='1234';




------------------------------------------
-- 방명록
DROP TABLE IF EXISTS `guestbook` RESTRICT;

-- 방명록
CREATE TABLE `guestbook` (
	`no`       INT UNSIGNED NOT NULL COMMENT '번호', -- 번호
	`name`     VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
	`password` VARCHAR(20)  NOT NULL COMMENT '비밀번호', -- 비밀번호
	`contents` TEXT         NOT NULL COMMENT '내용', -- 내용
	`reg_date` DATETIME     NOT NULL COMMENT '등록일' -- 등록일
)
COMMENT '방명록';

-- 방명록
ALTER TABLE `guestbook`
	ADD CONSTRAINT `PK_guestbook` -- 방명록 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `guestbook`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '번호';