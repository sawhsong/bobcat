SET DEFINE OFF;

delete sys_org;
Insert into SYS_ORG (ORG_ID,LEGAL_NAME,TRADING_NAME,ABN,ACN,TEL_NUMBER,MOBILE_NUMBER,EMAIL,ADDRESS,REGISTERED_DATE,ENTITY_TYPE,BUSINESS_TYPE,BASE_TYPE,REVENUE_RANGE_FROM,REVENUE_RANGE_TO,IS_ACTIVE,LOGO_PATH,INSERT_USER_ID,INSERT_DATE,UPDATE_USER_ID,UPDATE_DATE) values ('0','HK Accounting','HK Accounting','13152584837',null,'0393263722',null,'ricky@hkaccounting.com.au','7 Jeffcott Street, West Melbourne, VIC, 3003',to_date('23/MAY/17','DD/MON/RR'),'COMP','HKST','YEAR',0,0,'Y','/shared/resource/image/orgLogo/0_20210216210620_49506337648600_hkaccountingLogo.png','0',to_date('07/NOV/19','DD/MON/RR'),'1',to_date('16/FEB/21','DD/MON/RR'));

delete sys_user;
Insert into sys_user (USER_ID,USER_NAME,LOGIN_ID,LOGIN_PASSWORD,ORG_ID,AUTH_GROUP_ID,LANGUAGE,THEME_TYPE,TEL_NUMBER,MOBILE_NUMBER,EMAIL,MAX_ROW_PER_PAGE,PAGE_NUM_PER_PAGE,USER_STATUS,PHOTO_PATH,DEFAULT_START_URL,IS_ACTIVE,AUTHENTICATION_SECRET_KEY,INSERT_USER_ID,INSERT_DATE,UPDATE_USER_ID,UPDATE_DATE) values ('0','Administrator','admin','admin','0','0','EN','THEME000',null,null,'ricky@hkaccounting.com.au',100,5,'NU','/shared/resource/image/photo/DefaultUser_128_Black.png','/index/dashboard.do','Y','PWAGVR3W2JDNU7OTNOXDOB25PBN5K3TI','0',to_date('01/FEB/21','DD/MON/RR'),'1',to_date('04/FEB/21','DD/MON/RR'));
Insert into sys_user (USER_ID,USER_NAME,LOGIN_ID,LOGIN_PASSWORD,ORG_ID,AUTH_GROUP_ID,LANGUAGE,THEME_TYPE,TEL_NUMBER,MOBILE_NUMBER,EMAIL,MAX_ROW_PER_PAGE,PAGE_NUM_PER_PAGE,USER_STATUS,PHOTO_PATH,DEFAULT_START_URL,IS_ACTIVE,AUTHENTICATION_SECRET_KEY,INSERT_USER_ID,INSERT_DATE,UPDATE_USER_ID,UPDATE_DATE) values ('1','Developer','developer','developer','0','0','EN','THEME000',null,null,'ricky@hkaccounting.com.au',100,5,'NU','/shared/resource/image/photo/1_20220308135847_427650617843500_DefaultUser_128_Black.png','/index/dashboard.do','Y','6TKYSJP6RMXWUENOZ7RKWYNR42OGGBEY','0',to_date('01/FEB/21','DD/MON/RR'),'1',to_date('08/MAR/22','DD/MON/RR'));

delete sys_menu_auth_link;
insert into sys_menu_auth_link (
	select sys_auth_group.group_id,
	       sys_menu.menu_id,
	       0,
	       sysdate,
	       null,
	       null
	  from sys_auth_group,
	       sys_menu
	 where sys_auth_group.group_id = '0'
)
;