desc emaillist;
-- insert
insert into emaillist values(null,'조','근형','jgh0316@naver.com');

-- list
select no, first_name, last_name, email from emaillist order by no desc;


---------------------------------------------------------------------

-- 이메일리스트
DROP TABLE IF EXISTS `emaillist` RESTRICT;

-- 이메일리스트
CREATE TABLE `emaillist` (
	`no`         INT UNSIGNED NOT NULL COMMENT '번호', -- 번호
	`first_name` VARCHAR(20)  NOT NULL COMMENT '성', -- 성
	`last_name`  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
	`email`      VARCHAR(200) NOT NULL COMMENT '이메일' -- 이메일
)
COMMENT '이메일리스트';

-- 이메일리스트
ALTER TABLE `emaillist`
	ADD CONSTRAINT `PK_emaillist` -- 이메일리스트 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `emaillist`
	MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '번호';