/**
 * User
 */
-- alpaca
create user alpaca identified by alpaca;
grant connect, resource to alpaca;
grant create synonym, create view, create database link, create public synonym, drop public synonym to alpaca;

/**
 * Create DB Link
 * 	Login as alpaca
 */
drop database link perci;
create database link perci connect to PERCI_0829 identified by welcome1 using '10.14.35.41:1521/TEST';
--create database link perci connect to PERCI identified by WELCOME1 using 'entipx-ora2:1521/PROD';

/**
 * Table space(Index, Data)
 */
-- alpaca
create tablespace alpaca_idx
datafile 'C:\oraclexe\app\oracle\oradata\Alpaca\Alpaca_IDX.DBF' size 1m extent management local segment space management auto;

create tablespace alpaca_data
datafile 'C:\oraclexe\app\oracle\oradata\Alpaca\Alpaca_DATA.DBF' size 1m extent management local segment space management auto;

alter database datafile
'C:\oraclexe\app\oracle\oradata\Alpaca\Alpaca_IDX.DBF' autoextend on;

alter database datafile
'C:\oraclexe\app\oracle\oradata\Alpaca\Alpaca_DATA.DBF' autoextend on;

/**
 * To turn off oracle password expiration
 */
select *
  from dba_users
;

alter profile DEFAULT limit password_life_time unlimited;
