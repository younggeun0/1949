DROP SEQUENCE seq_app_num;

CREATE SEQUENCE seq_app_num
	START WITH 1
	MAXVALUE 999999
	INCREMENT BY 1
	NOCYCLE
	CACHE 20;

COMMIT;




