--4조 계정 생성 및 DDL

--계정 생성
1.
create user team_4 identified by java1234;

2.
grant connect,resource, dba to team_4;

--전체 테이블 삭제
select 'drop table '||table_name||' cascade constraints;' from user_tables; 

--DDL

create table tblMember (
    seq number primary key,             -- 회원번호(PK)
    email varchar2(30) not null,        -- 이메일
    pw varchar2(30) not null,           -- 비밀번호
    active char(1) default 'y' not null -- 활동상태
);
create sequence seqMember;

create table tblCategory (
    seq number primary key,     -- 카테고리 번호(PK)
    name varchar2(20) not null  -- 카테고리 이름
);

create sequence seqCategory;

create table tblMemberInfo (
    Mseq number not null references tblMember(seq),                     -- 회원번호(PK)
    nickname varchar2(30) not null,                                     -- 닉네임
    Cseq number references tblCategory(seq),                            -- 카테고리 번호
    ddayname varchar2(30) not null,                                     -- 디데이 이름
    ddate date not null,                                                -- 디데이 날짜
    atablenaming varchar2(100),                                         -- main 투두리스트 이름
    btablenaming varchar2(100),                                         -- sub 투두리스트 이름
    chalcnt number default 3 not null,                                  -- 챌린지 횟수
    image varchar2(1000) default 'basic_profile.png' not null,          -- 프로필 이미지
    message varchar2(2000) default '당신의 하루를 응원합니다.' not null,   -- 응원메세지
    point number default 0 not null                                     -- 포인트
);


create table tblChallenge (
    seq number primary key,                         -- 챌린지 번호(PK)
    createdate date default sysdate not null,       -- 생성날짜
    duedatenumber number not null,                  -- 모집기간(일)
    membercnt number not null,                      -- 모집인원
    Cseq number references tblCategory(seq),        -- 카테고리 번호
    name varchar2(1000) not null,                   -- 챌린지 이름
    Hseq number references tblMember(seq) not null, -- 회원 번호(PK)
    mission varchar2(3000) not null                 -- 공동미션
);
create sequence seqChallenge;



create table tblChallengeMember (
    seq number primary key,                     -- 챌린지 멤버번호(PK)
    Cseq number references tblChallenge(seq),   -- 카테고리 번호
    Mseq number references tblMember(seq),      -- 회원 번호(PK)
    Cnickname varchar(30) not null,             -- 챌린지 닉네임
    Ccnt number default 0 not null              -- 챌린지 횟수
);
create sequence seqChallengeMember;

create table tblQuestionBoard (
    seq number primary key,                 -- 게시글 번호(PK)
    Mseq number references tblMember(seq),  -- 회원 번호
    Qdate date default sysdate not null,    -- 작성일
    title varchar2(100) not null,           -- 제목
    content varchar2(3000) not null,        -- 내용
    readcount number default 0 not null     -- 조회수
);
create sequence seqQboard;

create table tblAnswerBoard (
    seq number primary key,                         -- 댓글 번호(PK)
    Mseq number references tblMember(seq),          -- 회원 번호
    Qseq number references tblQuestionBoard(seq),   -- 게시글 번호
    Adate date default sysdate not null,            -- 작성일
    content varchar2(2000) not null                 -- 내용
);
create sequence seqAboard;


create table tblTodoList(
    seq number primary key,                 -- 투두리스트 번호(PK)
    mseq number references tblMember(seq),  -- 회원 번호
    content varchar2(1000) not null,        -- 내용
    regdate date default sysdate not null,  -- 등록일
    type number not null                    -- 투두리스트 종류 (1:main 2:sub)
);
create sequence seqTodoList;


create table tblTimeblock (
    seq number primary key,                 -- 일정 번호(PK)
    content varchar2(200) not null,         -- 내용
    starttime varchar2(200),                -- 시작시간
    endtime varchar2(200),                  -- 종료시간
    Mseq number references tblMember(seq)   -- 회원번호
);
create sequence seqTimeblock;

create table tblCircletable (
    seq number primary key,                 -- 일전번호(PK)
    content varchar2(200) not null,         -- 내용
    starttime varchar2(200) not null,       -- 시작 시간
    endtime varchar2(200) not null,         -- 종료 시간
    Mseq number references tblMember(seq)   -- 회훤번호
);
create sequence seqCircleTablet;

create table tblCalendar (
    seq number primary key,                 -- 일정번호
    content varchar2(200) not null,         -- 내용
    startdate varchar2(200) not null,       -- 시작일
    enddate varchar2(200) not null,         -- 종료일
    Mseq number references tblMember(seq)   -- 회원번호 
);
create sequence seqCal;

create table tblOption(
    seq number references tblMember(seq) primary key,   -- 회원번호(PK)
    fonttype varchar2(1000) default 'n' not null,       -- 폰트
    theme varchar2(1000) default 'n' not null           -- 테마 
);


create table tblPlusReward (
    seq number primary key,
    mseq number references tblMember(seq),
    regdate date default sysdate not null,
    pseq number references tblpluspoint(seq)
);
create sequence seqPlusReward;

create table tblPlusPoint (
    seq number primary key,
    plusname varchar2(50) not null,
    pluscost number not null
);
create sequence seqPlusPoint;


create table tblMinusReward (
    seq number primary key,
    mseq number references tblMember(seq),
    regdate date default sysdate not null,
    iseq number references tblItem(seq)
);
create sequence seqMinusPoint;

create table tblItem(
    seq number primary key,
    itemname varchar2(100) not null,
    itemcost number not null,
    image varchar2(2000) not null
);
create sequence seqItem;
-------------------------------------------------------------------------------------------------------------------

create or replace view vwMinusReward
as
select seq, mseq, regdate, 
    (select itemname from tblItem where iseq = tblItem.seq) as itemName,
    (select itemcost from tblItem where iseq = tblItem.seq) as minusPoint
from tblMinusReward;

create or replace view vwPlusReward
as
select seq, mseq,regdate,
    (select plusname from tblPlusPoint where pseq = tblPlusPoint.seq) as plusName,
    (select pluscost from tblPlusPoint where pseq = tblPlusPoint.seq) as plusPoint
from tblPlusReward;


-------------------------------------------------------------------------------------------------------------------



--4조 DML


select * from tblMember;
--멤버 테이블 
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'teamjang@naver.com', '1234', default); --1팀장
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'kkhyun@naver.com', '1234', default); --2경현
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'tiger980827@gmail.com', '1234', default); --3태현
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'kkha@naver.com', '1234', default); --4경하
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'hsj@naver.com', '1234', default); --5서진
insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, 'lim@naver.com', '1234', default); --6수진


select * from tblMemberInfo;
--멤버 정보 테이블
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (1, '팀장', 20, '팀장의 D-day', '2023-06-05', '팀장의 투두리스트', '', default, default, default, default); --1팀장
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (2, '경현', 20, '경현이의 D-day', '2023-05-15', '경현의 투두리스트', '', default, default, default, default); --2경현
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (3, '남태현', 13, '변호사되기', '2023-12-29', '남태현 투두리스트', '', default, default, default, default); --3태현
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (4, '경하', 20, '경하의 D-day', '2023-09-08', '경하의 투두리스트', '', default, default, default, default); --4경하
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (5, '서진', 20, '서진이의 D-day', '2023-11-05', '서진의 투두리스트', '', default, default, default, default); --5서진
insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)
values (6, '수진', 20, '수진이의 D-day', '2023-07-05', '수진의 투두리스트', '', default, default, default, default); --6수진



--투두리스트 테이블

select *from tblTodolist;

insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 1, '영어회화 복습하기', default, default);

insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 1, '다이소 가기', default, default);


insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 2, '가토 돌기', default, default);
insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 2, '사탕 사두기', default, default);

insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 3, '카페가서 오후수업 듣기', default, default);
insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 3, '머리 자르기', default, default);


insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 4, '인공눈물 넣기', default, default);
insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 4, '집가는 버스 시간표 보기', default, default);

insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 5, '자바 공부하기', default, default);
insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 5, '강아지 산책 시켜주기', default, default);

insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 6, '데일리 운동 하기', default, default);
insert into tblTodoList (seq, mseq, content, regdate, type)
values (seqTodoList.nextVal, 6, '친구랑 저녁약속', default, default);


--Q게시판
insert into tblQuestionBoard(seq, Mseq, Qdate, title, content, likes) values
(seqQboard.nextVal, 1, default, '안녕하세요 팀장입니다.', '하하하', default);
insert into tblQuestionBoard(seq, Mseq, Qdate, title, content, likes) values
(seqQboard.nextVal, 2, default, '안녕하세요 경현입니다.', '호호호', default);
insert into tblQuestionBoard(seq, Mseq, Qdate, title, content, likes) values
(seqQboard.nextVal, 4, default, '안녕하세요 경하입니다.', '경하하하', default);


select * from tblAnswerBoard;
--A게시판
insert into tblAnswerBoard(seq, Mseq, Qseq, Adate, content, likes) values
(seqAboard.nextVal, 3, 1,default, '안녕하세요 팀장님~', default);
insert into tblAnswerBoard(seq, Mseq, Qseq, Adate, content, likes) values
(seqAboard.nextVal, 6, 2,default, '반가워요 경현님~', default);
insert into tblAnswerBoard(seq, Mseq, Qseq, Adate, content, likes) values
(seqAboard.nextVal, 5, 3,default, '재미없어요 경하님', default);

select * from tblChallenge;
-- tblChallenge 첼린지 테이블
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (1, default, 100, 30, 37, '정처기 챌린지', 1, '정처기 기출 문제 10문제씩 풀기'); 
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (2, default, 180, 20, 1, '중학생 모여라', 3,'출석하기'); 
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (3, default, 60, 20, 36, '토익 만점 챌린지', 5, '토익 L/C 20분 공부하기'); 

select * from tblChallengeMember;
-- tblChallengeMember 첼린지 멤버 테이블
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 1, 1, '정처기딸랭', default);
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 1, 2, '정처기조', default);
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 2, 3, '잼민이1', default);
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 2, 4, '잼민이2', default);
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 3, 5, '토익만점화이팅', default);
insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqChallengeMember.nextVal, 3, 6, '토익토익', default);


-- 카테고리 테이블
select * from tblCategory;
insert into tblCategory values (categorySeq.nextVal, '초등학교');
insert into tblCategory values (categorySeq.nextVal, '중학교 1학년');
insert into tblCategory values (categorySeq.nextVal, '중학교 2학년');
insert into tblCategory values (categorySeq.nextVal, '중학교 3학년');
insert into tblCategory values (categorySeq.nextVal, '고등학교 1학년');
insert into tblCategory values (categorySeq.nextVal, '고등학교 2학년');
insert into tblCategory values (categorySeq.nextVal, '대학생');
insert into tblCategory values (categorySeq.nextVal, '일반/학사 편입');
insert into tblCategory values (categorySeq.nextVal, 'PEET');
insert into tblCategory values (categorySeq.nextVal, 'MDEET');
insert into tblCategory values (categorySeq.nextVal, 'LEET');
insert into tblCategory values (categorySeq.nextVal, '로스쿨생');
insert into tblCategory values (categorySeq.nextVal, '간호대');
insert into tblCategory values (categorySeq.nextVal, '대학원생');
insert into tblCategory values (categorySeq.nextVal, '의대/의전원');
insert into tblCategory values (categorySeq.nextVal, '약학대학');
insert into tblCategory values (categorySeq.nextVal, '공무원');
insert into tblCategory values (categorySeq.nextVal, '경찰');
insert into tblCategory values (categorySeq.nextVal, '소방');
insert into tblCategory values (categorySeq.nextVal, '임용 유아');
insert into tblCategory values (categorySeq.nextVal, '임용 초등');
insert into tblCategory values (categorySeq.nextVal, '임용 중등');
insert into tblCategory values (categorySeq.nextVal, '임용 특수');
insert into tblCategory values (categorySeq.nextVal, '공인중개사');
insert into tblCategory values (categorySeq.nextVal, '회계사');
insert into tblCategory values (categorySeq.nextVal, '세무사');
insert into tblCategory values (categorySeq.nextVal, '관세사');
insert into tblCategory values (categorySeq.nextVal, '법무사');
insert into tblCategory values (categorySeq.nextVal, '노무사');
insert into tblCategory values (categorySeq.nextVal, '감정평가사');
insert into tblCategory values (categorySeq.nextVal, '변리사');
insert into tblCategory values (categorySeq.nextVal, '고시');
insert into tblCategory values (categorySeq.nextVal, '취업');
insert into tblCategory values (categorySeq.nextVal, '이직');
insert into tblCategory values (categorySeq.nextVal, '어학');
insert into tblCategory values (categorySeq.nextVal, '자격증');
insert into tblCategory values (categorySeq.nextVal, '기타');
insert into tblCategory values (categorySeq.nextVal, '고등학교 3학년');
insert into tblCategory values (categorySeq.nextVal, 'N수생');


--tblItem
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'폰트 변경권 - 교보손글씨체','200', 'Kyobo_handfont.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'폰트 변경권 - 마이쮸체','200', 'maijjyufont.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'폰트 변경권 - 조선 궁서체','200', 'Joseonfont.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'밤하늘의 별 테마','1000', 'starsinthenightskyT.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'검은토끼해 테마','1000', 'black_rabbitT.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'오리 꽥꽥 테마','1000', 'duckT.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'짱구 테마','1000', 'jjang_guT.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'보라돌이 테마','1000', 'purpleT.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'응원메세지 변경권','200', 'message.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'진행상태 설정권 - 막대바','500', 'ing.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'챌린지 생성권','500', 'createC.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'챌린지 가입권','200', 'joinC.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'닉네임 변경권','500', 'nick.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'투두리스트 추가권','500', 'todoplus.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'타임테이블 추가권','500', 'timetable.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'24시간 계획표 추가권','500', 'hour.png');
insert into tblItem (seq, itemname, itemcost, image) values (seqItem.nextVal,'캘린더 추가권','500', 'calendar.png');

--tblMinusReward
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 1, '2022-12-23', 17);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 2, '2022-12-25', 16);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 3, '2022-12-29', 15);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 1, '2022-12-16', 11);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 3, '2022-12-20', 11);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 5, '2022-12-21', 11);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 2, '2022-12-18', 12);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 4, '2022-12-20', 12);
insert into tblMinusReward(seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, 2, '2022-12-23', 12);        

--tblPlusPoint
insert into tblPlusPoint values (seqPlusPoint.nextval, '회원가입 이벤트', 5000);
insert into tblPlusPoint values (seqPlusPoint.nextval, '일일 로그인', 100);
insert into tblPlusPoint values (seqPlusPoint.nextval, '하루 1개 todo 완료', 40);
insert into tblPlusPoint values (seqPlusPoint.nextval, '7일 연속 todo list 완료', 500);
insert into tblPlusPoint values (seqPlusPoint.nextval, '한달 연속 todo list 완료 ', 5000);
insert into tblPlusPoint values (seqPlusPoint.nextval, '챌린지 공동 미션 달성', 500);


-- tblCalendar 캘린더
insert into tblCalendar values (seqCal.nextval, '교무처 방문', '2022-12-29', '2022-12-29', 1);
insert into tblCalendar values (seqCal.nextval, '홋카이도 여행', '2023-01-01', '2022-01-03', 1);

-- tblCircletable 원형 시간표
insert into tblCircletable values (seqCircleTable.nextval, '취침', 0, 7, 2);
insert into tblCircletable values (seqCircleTable.nextval, '기상 및 등교', 7, 8, 2);
insert into tblCircletable values (seqCircleTable.nextval, '학교 수업', 8, 14, 2);
insert into tblCircletable values (seqCircleTable.nextval, '숙제 및 복습', 14, 18, 2);
insert into tblCircletable values (seqCircleTable.nextval, '저녁시간', 18, 19, 2);
insert into tblCircletable values (seqCircleTable.nextval, '인터넷 강의', 19, 20, 2);
insert into tblCircletable values (seqCircleTable.nextval, '공부시간', 20, 24, 2);

-- tblTimeblock 타임 블럭
insert into tblTimeblock values (seqTimeblock.nextVal, '운동하기', 15, 16, 3);
insert into tblTimeblock values (seqTimeblock.nextVal, '병리학 공부하기', 19, 22, 3); 

--tblOption 
insert into tblOption(seq, fonttype, theme) values (1, default, default);
insert into tblOption(seq, fonttype, theme) values (2, default, default);
insert into tblOption(seq, fonttype, theme) values (3, default, default);
insert into tblOption(seq, fonttype, theme) values (4, default, default);
insert into tblOption(seq, fonttype, theme) values (5, default, default);
insert into tblOption(seq, fonttype, theme) values (6, default, default);


--tblplusReward
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 1, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 1, default, 2);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 2, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 2, default, 2);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 3, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 3, default, 2);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 4, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 4, default, 2);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 5, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 5, default, 2);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 6, default, 1);
insert into tblPlusReward(seq, mseq ,regdate, pseq) values (seqPlusReward.nextVal, 6, default, 2);




--create table tblTodoListDone (
--    mseq number primary key references tblMember(seq),  
--    donecnt number default 0 not null                   
--);
--
--create table tblRepeat(
--    seq number primary key,
--    content varchar2(30) not null
--);