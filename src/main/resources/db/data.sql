-- 개인 회원가입 더미
insert into user_tb(name, username, email, password, address, age, phone, role, created_at)
values ('서강준', 'ssar', 'ssar@nate.com', '1234', '서울시 강남구', '1998-02-02', '01022223333', 0, now()),
       ('김주혁', 'cos', 'cos@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, now());


-- 기업 회원가입 더미
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, created_at)
values ('5678', '홍길동', '네이버', '부산광역시 전포', 'com1', 'com1@nate.com', '1234', '담당자1', '01011112222', 1, now()),
       ('8888', '임꺽정', '카카오', '부산광역시 남포동', 'com2', 'com2@nate.com', '1234', '담당자2', '01011112222', 1, now()),
       ('9999', '장보고', '쿠팡', '부산광역시 저세상', 'com3', 'com3@nate.com', '1234', '담당자3', '01011112222', 1, now());


-- 이력서 더미
insert into resume_tb(user_id, name, resume_title, hope_job, career, license, content, edu, created_at)
values (1, '서강준', '이력서 제목1-1', '프론트엔드', '신입', '정보처리기사', '1번 유저의 자기소개서 1..... ..... ..... .....', '대학교 졸업', now()),
       (2, '김주혁', '이력서 제목2-1', '프론트엔드', '신입', 'SQLD', '2번 유저의 자기소개서 1..... ..... ..... .....', '고등학교 졸업', now()),
       (2, '김주혁', '이력서 제목2-2', '백엔드', '신입', 'SQLD', '2번 유저의 자기소개서 2..... ..... ..... .....', '고등학교 졸업', now());


--채용공고 더미
INSERT INTO jobopen_tb(user_id, compname, jobopen_title, content, career, edu, hope_job, comp_location, job_type,
                       salary, end_time, created_at, role)
VALUES (3, '네이버', '소프트웨어 개발자 채용', '네이버에서 함께 일해보세요!', '신입', '고졸', '백엔드', '서울', '정규직', '협의', '2024-12-31', now(), 1),
       (3, '네이버', '데이터 분석가 채용', '카카오에서 데이터로 세상을 바꿔보세요!', '경력', '대졸', '백엔드', '서울', '정규직', '5,000만원 이상', '2024-11-30',
        now(), 1),
       (4, '카카오', 'UI/UX 디자이너 채용', '라인에서 사용자 경험을 디자인하세요!', '신입', '고졸', '프론트엔드', '서울', '정규직', '3,000만원 이상', '2024-10-31',
        now(), 1),
       (5, '쿠팡', '마케팅 전문가 채용', '쿠팡에서 함께 성장하세요!', '경력', '대졸', '백엔드', '서울', '정규직', '협의', '2024-09-30', now(), 1),
       (5, '쿠팡', '운영 지원 인력 채용', '배달의민족의 성장을 함께 이끌어가세요!', '경력', '고졸', '프론트엔드', '서울', '계약직', '협의', '2024-08-31', now(),
        1);


-- 개인이 지원하는 것이면 role = 0을 넣어야 한다.
-- 선규가 2번 이력서로 네이버에 지원했다.
-- 선규가 2번 이력서로 쿠팡에 지원했다.
-- 강준이가 1번 이력서로 네이버(2)에 지원했다.
-- -- 개인에서 보는 지원한 현황 더미
INSERT INTO apply_tb(resume_id, user_id, jobopen_id, role, state, created_at)
values (3, 2, 1, 0, '검토중', now()), -- 네이버, 소프트웨어 개발자 채용에 선규(이력서 제  목 2-1)가 지원
       (3, 2, 4, 0, '검토중', now()), -- 쿠팡, 마케팅 전문가 채용에 선규(이력서 제목 2-2)가 지원
       (1, 1, 2, 0, '검토중', now());
-- 네이버, 데이터 분석가 채용에 강준(이력서 제목 1-1) 가 지원

---기업이 제안한 공고 현황 더미
INSERT INTO apply_tb(resume_id, user_id, jobopen_id, role, state, created_at)
values (1, 3, 1, 1, '검토중', now()),
       (2, 3, 1, 1, '검토중', now()),
       (3, 5, 4, 1, '검토중', now());

-- 합격, 불합격 더미가 필요함


-- 이력서 스킬 더미
INSERT INTO SKILL_TB(RESUME_ID, ROLE, USER_ID, NAME)
VALUES (1, 0, 1, '["Java","JavaScript","HTML","jQuery","MySQL"]'),
       (2, 0, 2, '["Java","JavaScript","Spring"]'),
       (3, 0, 2, '["Java","Spring"]');


-- 공고 스킬 더미
INSERT INTO SKILL_TB(jobopen_id, role, user_id, name)
values (1, 1, 3, '["Java","JavaScript"]'),
       (2, 1, 3, '["Java","JavaScript","Spring","HTML","jQuery","MySQL"]'),
       (3, 1, 4, '["Java"]'),
       (4, 1, 5, '["JavaScript","jQuery"]'),
       (5, 1, 5, '["Java","Spring","HTML","jQuery","MySQL"]');


-- 개인이 공고를 선택한 더미
Insert Into scrap_tb(user_id, jobopen_id, role, create_at)
values (1, 1, 0, now()),
       (1, 2, 0, now()),
       (2, 3, 0, now()),
       (2, 4, 0, now());


-- 기업이 이력서를 선택한 더미
Insert Into scrap_tb(user_id, resume_id, role, create_at)
values (3, 1, 1, now()),
       (3, 2, 1, now()),
       (4, 3, 1, now()),
       (5, 1, 1, now());


-- 자유게시판 더미
INSERT INTO board_tb(title, content, role, user_id, created_at)
VALUES ('1년차 개발자들', '1년차 개발자들 요즘 패기가 없더라 봄인데도 춥다고 패딩입고 다녀 쿼리 작성할 때도 *쓰더라 하나하나씩 다 안적고 싸가지 없게;; 나때는 하...', 0, 1, now()),
       ('죄송합니다', '유저 여러분의 마음을 헤아리지 못해 정말 죄송합니다! \n사죄의 의미로 200회 무료 뽑기와 전설급 장비와 펫을 무료로 드리겠습니다', 0, 2, now()),
       ('우리 회사 최고', '진짜 너무 좋아서 죽고 싶다', 0, 1, now()),
       ('네이버 질문 있어요', '네이버 들어가려면 어떻게 해야하나요?', 0, 2, now()),
       ('배민 자소서 질문', '배민 등급 천생연분인데 자기소개서 어필하면 가산점 있을까요?', 0, 1, now()),
       ('회사 추천 해주세요', '백엔드 5년차 이직 준비중입니다. \n연봉 어느정도 받으시나요?', 0, 2, now()),
       ('햄버거', '요즘 맥도날드 보다는 롯데리아가 더 맛있더라', 0, 1, now()),
       ('초보자분들 강의 추천 드립니다.', '최주호 강사님 강의 들어보세요. \n알기 쉽게 머리에 쏙쏙 들어오게 알려주시네요!', 0, 2, now()),
       ('책 추천 해주세요', '개발자라면 꼭 읽어야 할 책 추천해주세요. \n이유도 같이 적어주시면 감사하겠습니다! ㅎㅎ', 0, 1, now()),
       ('너가 연봉올려줄게 했잖아?', '그럼 이직 이딴거 안했어', 0, 2, now());

-- 이력서 사진 더미
INSERT INTO pic_tb(resume_id, img_filename, title)
VALUES (2, 'df106e66-d2c3-4153-9366-243fda8584d5_userImage.jpg', 'userImage.jpg'),
       (3, 'df106e66-d2c3-4153-9366-243fda8584d5_userImage.jpg', 'userImage.jpg');
