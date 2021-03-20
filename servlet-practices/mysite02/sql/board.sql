-- board

CREATE TABLE `board` (

	`no`       INTEGER UNSIGNED NOT NULL COMMENT '번호', -- 번호

	`title`    VARCHAR(50)      NOT NULL COMMENT '제목', -- 제목

	`email`    VARCHAR(50)      NOT NULL COMMENT '글쓴이', -- 글쓴이

	`views`    INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수

	`reg_date` DATETIME         NOT NULL COMMENT '작성일', -- 작성일

	`content`  TEXT             NOT NULL COMMENT '내용', -- 내용

	`g_no`     INTEGER UNSIGNED NOT NULL COMMENT '그룹번호', -- 그룹번호

	`o_no`     INTEGER UNSIGNED NOT NULL COMMENT '정렬번호', -- 정렬번호

	`depth`    INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '깊이' -- 깊이

)

COMMENT 'board';

 

-- board

ALTER TABLE `board`

	ADD CONSTRAINT `PK_board` -- board 기본키

		PRIMARY KEY (

			`no` -- 번호

		);

 

ALTER TABLE `board`

	MODIFY COLUMN `no` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '번호';
	
	
	-- -------------------------New Version-------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.23 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 webdb.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `no` int unsigned NOT NULL AUTO_INCREMENT COMMENT '번호',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `views` int unsigned NOT NULL DEFAULT '0' COMMENT '조회수',
  `reg_date` datetime NOT NULL COMMENT '작성일',
  `content` text NOT NULL COMMENT '내용',
  `g_no` int unsigned NOT NULL COMMENT '그룹번호',
  `o_no` int unsigned NOT NULL COMMENT '정렬번호',
  `depth` int unsigned NOT NULL DEFAULT '0' COMMENT '깊이',
  `user_no` int unsigned NOT NULL,
  PRIMARY KEY (`no`),
  KEY `FK1_user_no` (`user_no`),
  CONSTRAINT `FK1_user_no` FOREIGN KEY (`user_no`) REFERENCES `user` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='board';

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
