desc emaillist;
-- insert
insert into emaillist values(null,'조','근형','jgh0316@naver.com');

-- list
select no, first_name, last_name, email from emaillist order by no desc;