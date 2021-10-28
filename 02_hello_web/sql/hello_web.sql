--============================
--관리자 계정
--============================
--web계정 생성
alter session set "_oracle_script"=true; --c##으로 계정명 지정회피

create user web
identified by web
default tablespace users;

alter user web quota unlimited on users;

grant connect, resource to web;

--==============================
--web계정
--==============================
create table tb_member(
    user_id varchar2(20),
    pwd varchar2(20) not null,
    user_name varchar2(50) not null,
    ssn char(14) not null, -- -포함 14자리
    email varchar2(200) not null,
    tel char(11) not null, -- -없이 11자리
    job varchar2(100),
    hobby varchar2(200),
    reg_date date default sysdate,
    constraint pk_tb_member_user_id primary key(user_id)
);

insert into
    tb_member
values(
    'p5na9', '1234', '장희연', '000218-4444444',
    'p5na9@naver.com', '01021443418','백수','산책', default
);

insert into
    tb_member(user_id, pwd, user_name, ssn, email,tel, job, hobby)
values(
    'p5na92', '12345', '이희연', '000219-4444444',
    'p5na29@naver.com', '01021443419','목수','영화'
);
