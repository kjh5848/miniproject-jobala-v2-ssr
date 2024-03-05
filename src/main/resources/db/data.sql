-- 회원가입 더미
insert into user_tb(name, username, email, password, address, age, phone, role, created_at)
values ('서강준', 'ssar', 'ssar@nate.com', '1234', '서울시 강남구', '1998-02-02','01022223333', 0, now());
insert into user_tb(name, username, email, password, address, age, phone, role, created_at)
values ('박선규', 'cos', 'cos@nate.com', '1234', '부산광역시 금정구', '2000-04-25','01012345678', 0, now());
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, created_at)
values ( '5678', '홍길동', '네이버', '부산광역시 전포', 'com1', 'com1@nate.com', '1234', '담당자1', '01011112222', 1, now());
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, created_at)
values ( '8888', '임꺽정', '카카오', '부산광역시 남포동', 'com2', 'com2@nate.com', '1234', '담당자2', '01011112222', 1, now());
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, created_at)
values ( '9999', '장보고', '쿠팡', '부산광역시 저세상', 'com3', 'com3@nate.com', '1234', '담당자3', '01011112222', 1, now());


-- 이력서 더미
insert into resume_tb(user_id, name, resume_title, hope_job, career, license, content, edu, created_at)
values (1, '서강준', '이력서 제목1-1', '프론트엔드', '신입', '정보처리기사', '1번 유저의 자기소개서 1..... ..... ..... .....', '대학교 졸업', now());
insert into resume_tb(user_id, name, resume_title, hope_job, career, license, content, edu, created_at)
values (2, '박선규','이력서 제목2-1', '프론트엔드', '신입', 'SQLD', '2번 유저의 자기소개서 1..... ..... ..... .....', '고등학교 졸업', now());
insert into resume_tb(user_id, name, resume_title, hope_job, career, license, content, edu, created_at)
values (2, '박선규','이력서 제목2-2', '백엔드', '신입', 'SQLD', '2번 유저의 자기소개서 2..... ..... ..... .....', '고등학교 졸업', now());


--채용공고 더미
INSERT INTO jobopen_tb(user_id, compname, jobopen_title, content, career, edu, hope_job, comp_location, job_type, salary, end_time, created_at, role)
VALUES
    (3, '네이버', '소프트웨어 개발자 채용', '네이버에서 함께 일해보세요!', '신입', '고졸', '백엔드', '서울', '정규직', '1억','2024-12-31', now(), 1),
    (3, '네이버', '데이터 분석가 채용', '카카오에서 데이터로 세상을 바꿔보세요!', '경력', '대졸', '백엔드', '서울', '정규직', '9천만',  '2024-11-30', now(), 1),
    (4, '카카오', 'UI/UX 디자이너 채용', '라인에서 사용자 경험을 디자인하세요!', '신입', '고졸', '프론트엔드', '서울', '정규직', '8천만', '2024-10-31',now(), 1),
    (5, '쿠팡', '마케팅 전문가 채용', '쿠팡에서 함께 성장하세요!', '경력', '대졸', '백엔드', '서울', '정규직', '1억 2천만','2024-09-30',now(), 1),
    (5, '쿠팡', '운영 지원 인력 채용', '배달의민족의 성장을 함께 이끌어가세요!', '경력', '고졸', '프론트엔드', '서울', '계약직', '5천만', '2024-08-31',now(), 1);

-- 개인이 지원하는 것이면 role = 0을 넣어야 한다.
-- 선규가 1번 이력서로 네이버에 지원했다.
INSERT INTO apply_tb(resume_id, user_id, jobopen_id, state, role, created_at) values(1, 3, 1, '검토중', 0, now());
INSERT INTO apply_tb(resume_id, user_id, jobopen_id, state, role, created_at) values(2, 3, 1, '검토중', 0, now());
INSERT INTO apply_tb(resume_id, user_id, jobopen_id, state, role, created_at) values(3, 5, 4, '검토중', 0, now());