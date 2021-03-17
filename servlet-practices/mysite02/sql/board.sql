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