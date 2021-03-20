-- 회원
DROP TABLE IF EXISTS `user` RESTRICT;

-- 회원
CREATE TABLE `user` (
   `no`        INT UNSIGNED          NOT NULL COMMENT '번호', -- 번호
   `name`      VARCHAR(50)           NOT NULL COMMENT '이름', -- 이름
   `email`     VARCHAR(200)          NOT NULL COMMENT '이메일', -- 이메일
   `password`  VARCHAR(20)           NOT NULL COMMENT '비밀번호', -- 비밀번호
   `gender`    ENUM('male','female') NOT NULL COMMENT '성별', -- 성별
   `join_date` DATETIME              NOT NULL COMMENT '가입일' -- 가입일
)
COMMENT '회원';

-- 회원
ALTER TABLE `user`
   ADD CONSTRAINT `PK_user` -- 회원 기본키
      PRIMARY KEY (
         `no` -- 번호
      );

ALTER TABLE `user`
   MODIFY COLUMN `no` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '번호';webdbuser