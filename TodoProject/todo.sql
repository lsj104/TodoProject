delete from tblChallengeMember;
delete from tblChallenge;
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (seqchallenge.nextval, default, 100, 30, 37, '정처기 챌린지', 1, '정처기 기출 문제 10문제씩 풀기'); 
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (seqchallenge.nextval, default, 180, 20, 1, '중학생 모여라', 3,'출석하기'); 
insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission)
values (seqchallenge.nextval, default, 60, 20, 36, '토익 만점 챌린지', 5,'토익 L/C 20분 공부하기'); 

select * from tblChallenge;

select count(nickname) as cnt from tblMemberInfo where nickname = '경현' and mseq != 2;

select * from tblMemberInfo;

create sequence seqCal;

select seq, content, startdate, enddate, mseq from tblCalendar where mseq = 2 order by seq; 
select seq, content, startdate, to_char(to_date(enddate, 'yyyy-mm-dd')+1, 'yyyy-mm-dd') as enddate, mseq from tblCalendar where mseq = 2 order by seq; 

delete from tblCalendar;

select seq, content, startdate, to_char(to_date(enddate, 'yyyy-mm-dd')+1, 'yyyy-mm-dd') as enddate, mseq from tblCalendar where mseq = 2;

select * from tblCalendar;

commit;'

select * from tblPlusReward;

select * from tblPlusPoint;
    

select * from tblMinusReward where mseq = 2;

select m.seq as mrseq, mseq, regdate, iseq, itemname, itemcost, image from tblMinusReward m inner join tblItem i on  m.iseq = i.seq where mseq = 2 order by mrseq desc;

select * from tblChallenge;

select * from tblPlusReward;

select * from tblPlusReward;

insert into tblPlusReward values (seqPlusReward.nextVal, 3, default, 7);

commit;

select * from tblMemberinfo;

select * from tblMember;

select * from tblOption;

update tblOption set theme = 'n' where seq = 2;

commit;

select * from tblChallengeMember;

select * from tblMemberinfo;

select * from tblAnswerBoard;

select * from tblQuestionBoard;

delete from tblQuestionBoard where seq = 1;

select m.id, (select name from tblMember where id = m.id) as name, count(b.seq) as cnt from tblComment b right outer join tblMember m on m.id = b.id where m.lv = 1 group by m.id;

select * from tblMember m inner join tblMemberinfo i on m.seq = i.mseq inner join tblCategory c on c.seq = i.cseq where m.seq = 2;

select m.seq as seq, email, pw, active, nickname, message, name, image from tblMember m inner join tblMemberinfo i on m.seq = i.mseq inner join tblCategory c on c.seq = m.seq where email=? and pw=? and active='y'

select * 

select * from tblMemberInfo where mseq=2;

select * from tblCategory;

select nickname from tblMemberInfo i inner join tblMember m on i.mseq = m.seq;

select * from tblAnswerBoard;


select 
    tblAnswerBoard.*,
    (select nickname 
    from tblMemberInfo i 
        inner join tblMember m 
            on i.mseq = m.seq 
    where i.mseq = tblAnswerBoard.mseq) as name 
from tblAnswerBoard 
    where qseq = 1 order by seq desc;

insert into tblAnswerBoard(seq, Mseq, Qseq, Adate, content) values (seqAboard.nextVal, ?, ?, default, ?);



commit;

rollback;

update tblMemberInfo set image = 'catty01.png' where mseq = 2;

update tblChallengeMember set cseq = 2 where seq = 2;

create sequence seqCM;

insert into tblChallengeMember values (seqCM.nextVal, 1, 2, '경현', default);
insert into tblChallengeMember values (seqCM.nextVal, 1, 2, '안녕하세요', default);

select * from tblChallenge where seq = (select cseq from tblChallengeMember where mseq = 2);


select * from tblChallenge;

select * from tblChallengeMember;


select 
    cseq
from tblChallengeMember where mseq = 2;

select * from tblChallenge where seq in (1, 2);



commit;
