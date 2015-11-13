DELIMITER @@
drop procedure  if exists makequyingyebuitem; 
create procedure makequyingyebuitem()  
begin

declare depfrom int;
set depfrom=20;

while depfrom <= 26 do 
	insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
	values('区营业部条目1', 'miaoshu', 20, 'refer', 'calcu', depfrom, 1, '2014-04-14', 1);
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 9 from department_score_item;
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 10 from department_score_item;
	insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
	values('区营业部条目2', 'miaoshu', 20, 'refer', 'calcu', depfrom, 1, '2014-04-14', 1);
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 11 from department_score_item;
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 12 from department_score_item;
	insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
	values('区营业部条目3', 'miaoshu', 20, 'refer', 'calcu', depfrom, 1, '2014-04-14', 1);
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 13 from department_score_item;
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 14 from department_score_item;
	insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
	values('区营业部条目4', 'miaoshu', 20, 'refer', 'calcu', depfrom, 1, '2014-04-14', 1);
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 15 from department_score_item;
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 16 from department_score_item;
	insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
	values('区营业部条目5', 'miaoshu', 20, 'refer', 'calcu', depfrom, 1, '2014-04-14', 1);
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 17 from department_score_item;
	insert into department_score_relation(score_item_id, department_id) select max(score_item_id), 18 from department_score_item;
	set depfrom=depfrom+1;
end while;

end; 


drop procedure  if exists makekeshiitem; 
create procedure makekeshiitem()  
begin
declare depid int;
declare depname varchar(20);
declare dep1id int;
declare dep1name varchar(20);
declare dep2id int;
declare dep2name varchar(20);
declare done int;
declare olddepid int default 0;

declare cursor_fuck cursor for 
select a.department_id, a.department_name, b.department_id, b.department_name, c.department_id, c.department_name
from department a, department b, department c
where a.higher_department_id = b.department_id
and b.higher_department_id = c.department_id
and a.department_name like '%科' ;
declare continue handler for not found set done=1;
open cursor_fuck;
cursor_loop:loop
	if done=1 then
		leave cursor_loop;
	end if;
	fetch cursor_fuck into depid, depname, dep1id, dep1name, dep2id, dep2name;
	if olddepid != depid then
			insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
			values('区营业部科室条目1', 'miaoshu', 20, 'refer', 'calcu', depid, 1, '2014-04-14', 1);
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep1id from department_score_item;
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep2id from department_score_item;
			insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
			values('区营业部科室条目2', 'miaoshu', 20, 'refer', 'calcu', depid, 1, '2014-04-14', 1);
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep1id from department_score_item;
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep2id from department_score_item;
			insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
			values('区营业部科室条目3', 'miaoshu', 20, 'refer', 'calcu', depid, 1, '2014-04-14', 1);
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep1id from department_score_item;
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep2id from department_score_item;
			insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
			values('区营业部科室条目4', 'miaoshu', 20, 'refer', 'calcu', depid, 1, '2014-04-14', 1);
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep1id from department_score_item;
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep2id from department_score_item;
			insert into department_score_item (score_item_name, score_description, score_value, score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use)
			values('区营业部科室条目5', 'miaoshu', 20, 'refer', 'calcu', depid, 1, '2014-04-14', 1);
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep1id from department_score_item;
			insert into department_score_relation(score_item_id, department_id) select max(score_item_id), dep2id from department_score_item;
		set olddepid=depid;
	end if;
end loop cursor_loop;
close cursor_fuck;
end; 

  
@@ 

DELIMITER ; 

call makequyingyebuitem();

call makekeshiitem();

select 'over!!!';


