select * from member 
where member_id = 'sejong';

update member 
set password = '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==';

commit;


--페이징쿼리
select
    row_number () over(order by enroll_date desc) rnum,
    m.*
from
    member m
order by 
    enroll_date desc;
    
    







