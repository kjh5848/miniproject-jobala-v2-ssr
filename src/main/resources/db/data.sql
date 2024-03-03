insert into resume_tb(user_id, resume_title, hope_job, career, license, content, created_at)
values (1, '이력서 제목1-1', '프론트엔드', '신입', '', '1번 유저의 자기소개서 1..... ..... ..... .....', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, created_at)
values (1, '이력서 제목1-2', '백엔드', '신입', ' 정보처리기사', '1번 유저의 자기소개서 2..... ..... ..... .....', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, created_at)
values (2, '이력서 제목2-1', '프론트엔드', '신입', '', '2번 유저의 자기소개서 1..... ..... ..... .....', now());
insert into resume_tb(user_id, resume_title, hope_job, career, license, content, created_at)
values (2, '이력서 제목2-2', '백엔드', '신입', 'SQLD', '2번 유저의 자기소개서 2..... ..... ..... .....', now());


insert into jobopen_tb(user_id, compname, jobopen_title, content, career, edu, hope_job, comp_location, end_time,
                       created_at, role)
values (1, '(주)자바라', '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구', NOW(),
        NOW(), 1),
       (1, '(주)크리에이티브', '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구',
        NOW(),
        NOW(), 1),
       (1, '(주)웹프로그래밍', '플랫폼 백엔드 개발자 모집', 'Node.js, Express.js, MySQL 경험…', '경력 3년 이상', '신입', '백엔드 개발', '서울특별시 강남구',
        NOW(),
        NOW(), 1);

