/**
 * DB Design File Naming Conventions
 * 1st number : Subsystem sequence(0:Framework, 1:Project SYS ...)
 * 2nd number : Definition of Table or Data(1:Table, 2:Data ...)
 * 3rd number : Sequence
 */

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


/**
* Table Id    : 
* Table Name  : 
* Description : Query for TableDefinition Document
**/
select a.table_name,
       a.column_name,
       d.comments,
       a.data_type,
       decode(a.data_type, 'NUMBER', decode(a.data_precision||','||a.data_scale, ',', '', a.data_precision||','||a.data_scale), a.data_length) data_length,
       a.nullable,
       decode(c.constraint_type,'P','PK','R','FK','U','UK') constraint_type,
       '' aaa
  from user_tab_columns a,
       (select a.table_name,
               b.column_name,
               a.constraint_type
          from user_constraints a,
               user_cons_columns b
         where a.constraint_type in ('P', 'R', 'U')
           and a.table_name = b.table_name
           and a.constraint_name = b.constraint_name
       ) c,
       user_col_comments d
 where a.table_name = c.table_name (+)
   and a.column_name = c.column_name (+)
   and a.table_name = d.table_name (+)
   and a.column_name = d.column_name (+)
 order by a.table_name,
       a.nullable,
       a.column_name
;