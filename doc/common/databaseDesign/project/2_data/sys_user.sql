REM INSERTING into sys_user
SET DEFINE OFF;
Insert into sys_user (USER_ID,USER_NAME,LOGIN_ID,LOGIN_PASSWORD,ORG_ID,AUTH_GROUP_ID,LANGUAGE,THEME_TYPE,TEL_NUMBER,MOBILE_NUMBER,EMAIL,MAX_ROW_PER_PAGE,PAGE_NUM_PER_PAGE,USER_STATUS,PHOTO_PATH,DEFAULT_START_URL,IS_ACTIVE,AUTHENTICATION_SECRET_KEY,INSERT_USER_ID,INSERT_DATE,UPDATE_USER_ID,UPDATE_DATE) values ('0','Administrator','admin','admin','0','0','EN','THEME000',null,null,'ricky@hkaccounting.com.au',100,5,'NU','/shared/resource/image/photo/DefaultUser_128_Black.png','/index/dashboard.do','Y','PWAGVR3W2JDNU7OTNOXDOB25PBN5K3TI','0',to_date('01/FEB/21','DD/MON/RR'),'1',to_date('04/FEB/21','DD/MON/RR'));
Insert into sys_user (USER_ID,USER_NAME,LOGIN_ID,LOGIN_PASSWORD,ORG_ID,AUTH_GROUP_ID,LANGUAGE,THEME_TYPE,TEL_NUMBER,MOBILE_NUMBER,EMAIL,MAX_ROW_PER_PAGE,PAGE_NUM_PER_PAGE,USER_STATUS,PHOTO_PATH,DEFAULT_START_URL,IS_ACTIVE,AUTHENTICATION_SECRET_KEY,INSERT_USER_ID,INSERT_DATE,UPDATE_USER_ID,UPDATE_DATE) values ('1','Developer','developer','developer','0','0','EN','THEME000',null,null,'ricky@hkaccounting.com.au',100,5,'NU','/shared/resource/image/photo/1_20220308135847_427650617843500_DefaultUser_128_Black.png','/index/dashboard.do','Y','6TKYSJP6RMXWUENOZ7RKWYNR42OGGBEY','0',to_date('01/FEB/21','DD/MON/RR'),'1',to_date('08/MAR/22','DD/MON/RR'));
