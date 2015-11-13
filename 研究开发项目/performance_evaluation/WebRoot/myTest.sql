select * from staff;

select dsr.department_from_id,foutd.department_name,dsr.department_to_id,toutd.department_name,dsr.score_item_id 
from department_score_record dsr,department foutd,department toutd 
where dsr.department_from_id=foutd.department_id and dsr.department_to_id=toutd.department_id 
	and unix_timestamp(dsr.score_date) between ? and ? 
	and foutd.department_id in (
		select distinct dsre.department_id 
		from department outd,department_score_relation dsre 
		where dsre.department_id=outd.department_id 
		and dsre.department_id not in (
			select ind.department_id 
			from department ind,staff s,post p 
			where ind.department_id=s.department_id and s.post_id=p.post_id 
			and p.post_name in ('区营业部科室科长','区营业部副经理')));
			
drop table staff_score; 

select * from post;

select * from staff_score_record;

show tables;

insert into task_progress values(1,90,null,2015,4);
select * from task_progress;
desc task_progress;

