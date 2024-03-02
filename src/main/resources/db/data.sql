insert into resume_tb(user_id, title, hope_job, career, license, content, created_at)
values ('1', '이력서 제목1-1', '프론트엔드', '신입', '', '1번 유저의 자기소개서 1..... ..... ..... .....', now());
insert into resume_tb(user_id, title, hope_job, career, license, content, created_at)
values ('1', '이력서 제목1-2', '백엔드', '신입', '', '1번 유저의 자기소개서 2..... ..... ..... .....', now());
insert into resume_tb(user_id, title, hope_job, career, license, content, created_at)
values ('2', '이력서 제목2-1', '프론트엔드', '신입', '', '2번 유저의 자기소개서 1..... ..... ..... .....', now());
insert into resume_tb(user_id, title, hope_job, career, license, content, created_at)
values ('2', '이력서 제목2-2', '백엔드', '신입', '', '2번 유저의 자기소개서 2..... ..... ..... .....', now());

insert into user_tb(name, username, password, address, phone, role, created_at)
values('cos', 'cos@nate.com', '1234', '부산광역시 금정구', '01012345678', 0, now());
insert into user_tb(comp_num, ceo, compname, address, username, password, name, phone, role, created_at)
values('5678', '박선규', '선규넷',  '부산광역시 전포', 'ssar@nate.com', '1234', 'ssar', '01011112222', 1, now());