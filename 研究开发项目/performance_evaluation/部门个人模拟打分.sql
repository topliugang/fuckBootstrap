DELIMITER @@


drop procedure if exists staffscore1;
create procedure staffscore1()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor1 cursor for 
select staff_id, department_id from staff where post_id=2 or post_id=1;
declare continue handler for not found set done=1;
open cursor1;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;


	fetch cursor1 into stafffromid, depfromid;
	

	if oldstafffromid != stafffromid then
		
		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id in (select department_id from department where higher_department_id=depfromid)
		and a.post_id=4 
		and a.department_id = b.department_id
		and a.post_id = b.post_id;

		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor1;


end;

drop procedure if exists staffscore2;
create procedure staffscore2()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor2 cursor for 
select staff_id, department_id from staff where post_id=3;
declare continue handler for not found set done=1;
open cursor2;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor2 into stafffromid, depfromid;

	if oldstafffromid != stafffromid then

		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id = depfromid
		and (a.post_id=4 or a.post_id=5)
		and a.department_id = b.department_id
		and a.post_id = b.post_id;
		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor2;



end;

drop procedure if exists staffscore3;
create procedure staffscore3()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor3 cursor for 
select staff_id, department_id from staff where post_id=4;
declare continue handler for not found set done=1;
open cursor3;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor3 into stafffromid, depfromid;

	if oldstafffromid != stafffromid then

		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id = depfromid
		and a.post_id=5
		and a.department_id = b.department_id
		and a.post_id = b.post_id;

		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor3;



end;

drop procedure if exists staffscore4;
create procedure staffscore4()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor4 cursor for 
select staff_id, department_id from staff where post_id=7;
declare continue handler for not found set done=1;
open cursor4;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor4 into stafffromid, depfromid;

	if oldstafffromid != stafffromid then
		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id in (select department_id from department where higher_department_id=depfromid) 
		and a.post_id=9
		and a.department_id = b.department_id
		and a.post_id = b.post_id;

		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor4;



end;

drop procedure if exists staffscore5;
create procedure staffscore5()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor5 cursor for 
select staff_id, department_id from staff where post_id=8;
declare continue handler for not found set done=1;
open cursor5;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor5 into stafffromid, depfromid;

	if oldstafffromid != stafffromid then
		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id = depfromid
		and (a.post_id=9 or a.post_id=10)
		and a.department_id = b.department_id
		and a.post_id = b.post_id;

		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor5;



end;

drop procedure if exists staffscore6;
create procedure staffscore6()
begin
declare stafffromid int;
declare depfromid int;
declare done int;
declare oldstafffromid int default 0;

declare cursor6 cursor for 
select staff_id, department_id from staff where post_id=9;
declare continue handler for not found set done=1;
open cursor6;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor6 into stafffromid, depfromid;

	if oldstafffromid != stafffromid then
		insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, score_item_id, score, score_date, which_year, which_month, modify_times, owner)
		select stafffromid, a.staff_id, b.score_item_id, 99, '2015-06-30', 2015, 6, 3, stafffromid
		from staff a, staff_score_relation b
		where a.department_id = depfromid
		and a.post_id=10
		and a.department_id = b.department_id
		and a.post_id = b.post_id;

		set oldstafffromid=stafffromid;
	end if;

end loop cursor_loop;
close cursor6;



end;

drop procedure  if exists departmentscore2; 
create procedure departmentscore2()  
begin

declare staffid int;
declare depid int;
declare done int default 0;
declare depcomment text default 'hehe';
declare depdate date default '2015-06-30';
declare depyear int default 2015;
declare depmonth int default 10;
declare depmoditimes int default 3;


declare cursor6 cursor for 
	select staff_id, department_id from staff where role_id in (select role_id from role_funcpage_relation where funcpage_id = 16);
declare continue handler for not found set done=1;
open cursor6;
repeat
	fetch cursor6 into staffid, depid;
 	if done <> 1 then
		-- do something
		insert into department_score_record(department_from_id, department_to_id, score_item_id, score, score_comment, score_date, which_year, which_month, modify_times, owner)
			select a.department_id, b.department_id, a.score_item_id, b.score_value, depcomment, depdate, depyear, depmonth, depmoditimes, staffid
			from department_score_relation a, department_score_item b 
			where a.score_item_id = b.score_item_id
				and a.department_id = depid;


 	end if;
until done = 1 end repeat;

close cursor6;

end; 



  
@@ 

DELIMITER ; 


-- delete from department_score_record;
-- delete from staff_score_record;
-- delete from department_score;
-- delete from staff_score;
call departmentscore2();
call staffscore1();
call staffscore2();
call staffscore3();
-- call staffscore4();
-- call staffscore5();
-- call staffscore6();


update department_score_record set score_date='2015-06-30', which_year=2015, which_month=6;
update staff_score_record set score_date='2015-06-30', which_year=2015, which_month=6;


select 'over!!!';