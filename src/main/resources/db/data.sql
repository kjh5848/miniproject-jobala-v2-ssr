-- 회원가입 더미
insert into user_tb(name, username, email, password, address, phone, role, created_at)
values ('박선규', 'cos', 'cos@nate.com', '1234', '부산광역시 금정구', '01012345678', 0, now());
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, created_at)
values ( '5678', '홍길동', '활빈당컴', '부산광역시 전포', 'love', 'love@nate.com', '1234', '홍길동', '01011112222', 1, now());


-- 이력서 더미
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at)
values (1, '이력서 제목1-1', '프론트엔드', '신입', '', '1번 유저의 자기소개서 1..... ..... ..... .....', '대학교 졸업', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at)
values (1, '이력서 제목1-2', '백엔드', '신입', ' 정보처리기사', '1번 유저의 자기소개서 2..... ..... ..... .....', '고등학교 졸업', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at)
values (2, '이력서 제목2-1', '프론트엔드', '신입', '', '2번 유저의 자기소개서 1..... ..... ..... .....', '고등학교 졸업', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at)
values (2, '이력서 제목2-2', '백엔드', '신입', 'SQLD', '2번 유저의 자기소개서 2..... ..... ..... .....', '대학교 졸업', now());

--
--
-- 채용공고 더미
-- insert into jobopen_tb(user_id, jobopen_title, content, career, edu, hope_job, comp_location, end_time,
--                        created_at, role)
-- values (1, '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구', NOW(),
--         NOW(), 1),
--        (1, '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구',
--         NOW(),
--         NOW(), 1),
--        (1, '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구',
--         NOW(),
--         NOW(), 1);
        


