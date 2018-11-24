begin;
delete from mochi.customer where rle_id = 232500;
delete from mochi.role where rle_id = 232500;
delete from mochi.person where psn_id = 232300;
delete from security.user_ where pty_id = 232300;
delete from mochi.user_ where pty_id = 232300;
end;

begin;
insert into mochi.party (pty_typ_id, pty_id) values (1, 232300);
insert into mochi.person (psn_fml_nm_en, psn_gvn_nm_en, psn_nm_cn, psn_id) values ('Jane', 'Doe', 'placeholder', 232300);
insert into mochi.role (rle_start_dttm, pty_id, rle_typ_id, rle_id) values (now(), 232300, 1, 232500);
insert into mochi.customer (cst_id, rle_id) values ('1324532456', 232500);
insert into security.user_ (ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED, PASSWORD, USER_NAME, pty_id)
values (false, false, false, 'true', '$2a$08$V5TAyAWOscZZZ1QtURlldOpKzufHQ4m.CehmJbbLMeFCv.laPvBFa', 'jane002', 232300);
end;
/*INSERT INTO security.USERS_AUTHORITIES(USER_ID, AUTHORITY_ID)
SELECT (SELECT pty_id FROM security.USER_ WHERE user_name = 'jane002') as USER_ID,
       id as AUTHORITY_ID
FROM security.AUTHORITY;*/


select * 
from  mochi.party pty
	inner join mochi.person psn
    	on pty.pty_id = psn.psn_id
        
     inner join mochi.role rle
     	on pty.pty_id = rle.pty_id
        
      inner join mochi.customer cst
      	on rle.rle_id = cst.rle_id
        
      inner join security.user_ usr
      	on pty.pty_id = usr.pty_id
        
     -- inner join security.USERS_AUTHORITIES ua
     -- 	on usr.pty_id = ua.USER_ID
and pty.pty_id = '232300'