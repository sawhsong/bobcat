/**
 * User
 */
-- hkaccount
create user hkaccount identified by hkaccount20170523;
grant connect, resource to hkaccount;
grant create synonym, create view, create database link, create public synonym, drop public synonym to hkaccount;

/**
 * Create DB Link
 */
drop database link hkaccount_aws;
create database link hkaccount_aws connect to hkaccount identified by hkaccount20170523 using '52.65.204.139:1521/xe';

/**
 * Table space(Index, Data)
 */
-- hkaccount
create tablespace hkaccount_idx
datafile 'C:\oraclexe\app\oracle\oradata\HKAccount\HKAccount_IDX.DBF' size 1m
extent management local
segment space management auto;

create tablespace hkaccount_data
datafile 'C:\oraclexe\app\oracle\oradata\HKAccount\HKAccount_DATA.DBF' size 1m
extent management local
segment space management auto;

alter database datafile
'C:\oraclexe\app\oracle\oradata\HKAccount\HKAccount_IDX.DBF'
autoextend on;

alter database datafile
'C:\oraclexe\app\oracle\oradata\HKAccount\HKAccount_DATA.DBF'
autoextend on;

/**
 * To turn off oracle password expiration
 */
select *
  from dba_users
;

alter profile DEFAULT limit password_life_time unlimited;

/**
 * plan_table
 */
drop table plan_table cascade constraints;
purge recyclebin;

create table plan_table (
    statement_id       varchar2(30),
    plan_id            number,
    timestamp          date,
    remarks            varchar2(4000),
    operation          varchar2(30),
    options            varchar2(255),
    object_node        varchar2(128),
    object_owner       varchar2(30),
    object_name        varchar2(30),
    object_alias       varchar2(65),
    object_instance    numeric,
    object_type        varchar2(30),
    optimizer          varchar2(255),
    search_columns     number,
    id                 numeric,
    parent_id          numeric,
    depth              numeric,
    position           numeric,
    cost               numeric,
    cardinality        numeric,
    bytes              numeric,
    other_tag          varchar2(255),
    partition_start    varchar2(255),
    partition_stop     varchar2(255),
    partition_id       numeric,
    other              long,
    distribution       varchar2(30),
    cpu_cost           numeric,
    io_cost            numeric,
    temp_space         numeric,
    access_predicates  varchar2(4000),
    filter_predicates  varchar2(4000),
    projection         varchar2(4000),
    time               numeric,
    qblock_name        varchar2(30),
    other_xml          clob
);


/**
 * Table Name  : 공통코드
 * Description : Framework에서 사용하는 공통코드(프로젝트에서는 별도 테이블 만들것)
 */
drop table zebra_common_code cascade constraints;
purge recyclebin;

create table zebra_common_code (
    code_type                       varchar2(30)                                        not null,   -- 상위구분코드
    common_code                     varchar2(30)                                        not null,   -- 코드
    description_ko                  varchar2(1000),                                                 -- 코드설명(Korean)
    description_en                  varchar2(1000),                                                 -- 코드설명(English)
    program_constants               varchar2(100)                                       not null,   -- Constants value for the common code to be used in program
    sort_order                      varchar2(3),                                                    -- 정렬순서
    use_yn                          varchar2(1)                 default 'Y',                        -- 사용여부
    default_yn                      varchar2(1)                 default 'N',                        -- 기본데이터여부(기본데이터는 변경불가)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_zebra_common_code primary key(code_type, common_code),
    constraint uk_zebra_common_code unique(program_constants)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table zebra_common_code is '공통코드';

comment on column zebra_common_code.code_type         is '상위구분코드';
comment on column zebra_common_code.common_code       is '코드';
comment on column zebra_common_code.description_ko    is '코드설명(Korean)';
comment on column zebra_common_code.description_en    is '코드설명(English)';
comment on column zebra_common_code.program_constants is 'Constants value for the common code to be used in program';
comment on column zebra_common_code.sort_order        is '정렬순서';
comment on column zebra_common_code.use_yn            is '사용여부';
comment on column zebra_common_code.default_yn        is '기본데이터여부(기본데이터는 변경불가)';
comment on column zebra_common_code.insert_user_id    is '입력자 uid';
comment on column zebra_common_code.insert_date       is '입력일자';
comment on column zebra_common_code.update_user_id    is '수정자 uid';
comment on column zebra_common_code.update_date       is '수정일자';


/**
 * Table Name  : Domain Dictionary
 * Description : Database column data types
 */
drop table zebra_domain_dictionary cascade constraints;
purge recyclebin;

create table zebra_domain_dictionary (
    domain_id                       varchar2(30)                                        not null,   -- Domain item UID (PK)
    domain_name                     varchar2(100)                                       not null,   -- Domain item name
    name_abbreviation               varchar2(50)                                        not null,   -- Domain item name abbreviated
    data_type                       varchar2(30)                                        not null,   -- Data type ([zebra_common_code.DOMAIN_DATA_TYPE])
    data_precision                  number,                                                         -- Data precision ([zebra_common_code.DOMAIN_DATA_PRECISION])
    data_scale                      number,                                                         -- Data scale ([zebra_common_code.DOMAIN_DATA_SCALE])
    data_length                     number,                                                         -- Data length ([zebra_common_code.DOMAIN_DATA_LENGTH])
    description                     varchar2(4000),                                                 -- Description
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_zebra_domain_dictionary primary key(domain_id),
    constraint uk_zebra_domain_dictionary unique(domain_name, name_abbreviation)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  zebra_domain_dictionary                     is 'Domain Dictionary';
comment on column zebra_domain_dictionary.domain_id           is 'Domain item UID (PK)';
comment on column zebra_domain_dictionary.domain_name         is 'Domain item name';
comment on column zebra_domain_dictionary.name_abbreviation   is 'Domain item name abbreviated';
comment on column zebra_domain_dictionary.data_type           is 'Data type ([zebra_common_code.DOMAIN_DATA_TYPE])';
comment on column zebra_domain_dictionary.data_precision      is 'Data precision ([zebra_common_code.DOMAIN_DATA_PRECISION])';
comment on column zebra_domain_dictionary.data_scale          is 'Data scale ([zebra_common_code.DOMAIN_DATA_SCALE])';
comment on column zebra_domain_dictionary.data_length         is 'Data length ([zebra_common_code.DOMAIN_DATA_LENGTH])';
comment on column zebra_domain_dictionary.description         is 'Description';
comment on column zebra_domain_dictionary.insert_user_id      is 'Insert User UID';
comment on column zebra_domain_dictionary.insert_date         is 'Insert Date';
comment on column zebra_domain_dictionary.update_user_id      is 'Update User UID';
comment on column zebra_domain_dictionary.update_date         is 'Update Date';


/**
 * Table Name  : 게시판
 * Description : 
 */
drop table zebra_board cascade constraints;
purge recyclebin;

create table zebra_board (
    article_id                      varchar2(30)                                        not null,   -- Article UID (PK)
    board_type                      varchar2(30)                                        not null,   -- BBS Type([sys_common_code.board_type - bbs / notice])
    writer_id                       varchar2(30),                                                   -- Writer UID ([sys_user.user_id] Anonymous user is allowed to write an article with only name)
    writer_name                     varchar2(50)                                        not null,   -- Writer Name (Anonymous user is allowed to write an article with only name)
    article_password                varchar2(12),                                                   -- Article Password (if wants)
    writer_email                    varchar2(100),                                                  -- Writer e-mail
    writer_ip_address               varchar2(50),                                                   -- Writer IP Adress
    article_subject                 varchar2(1000),                                                 -- Subject
    article_contents                clob                        default empty_clob(),               -- Contents
    visit_cnt                       number(5)                   default 0               not null,   -- Number of read
    ref_article_id                  varchar2(30)                                        not null,   -- Referred article UID (Level 1 => 0)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_zebra_board primary key(article_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  zebra_board                     is '게시판';
comment on column zebra_board.article_id          is 'Article UID (PK)';
comment on column zebra_board.board_type          is '게시판 종류([sys_common_code.board_type - bbs / notice])';
comment on column zebra_board.writer_id           is '작성자 uid(등록된 사용자가 아니라도 이름만 입력하면 등록 가능)';
comment on column zebra_board.writer_name         is '작성자성명';
comment on column zebra_board.article_password    is '게시물비밀번호';
comment on column zebra_board.writer_email        is '작성자e-mail';
comment on column zebra_board.writer_ip_address   is '작성자ip adress';
comment on column zebra_board.article_subject     is '게시물제목';
comment on column zebra_board.article_contents    is '게시물내용';
comment on column zebra_board.visit_cnt           is '방문횟수';
comment on column zebra_board.ref_article_id      is '참조게시물 unique id(1레벨자료는 0)';
comment on column zebra_board.insert_user_id      is '입력자 uid';
comment on column zebra_board.insert_date         is '입력일자';
comment on column zebra_board.update_user_id      is '수정자 uid';
comment on column zebra_board.update_date         is '수정일자';


/**
 * Table Name  : 게시판 첨부파일 정보
 * Description : 
 */
drop table zebra_board_file cascade constraints;
purge recyclebin;

create table zebra_board_file (
    file_id                         varchar2(30)                                        not null,   -- 파일 unique id
    article_id                      varchar2(30)                                        not null,   -- 게시물 unique id
    original_name                   varchar2(1000)                                      not null,   -- 실제 파일명
    new_name                        varchar2(1000)                                      not null,   -- 시스템 설정 파일명
    file_type                       varchar2(300),                                                  -- 파일 type
    file_icon                       varchar2(1000),                                                 -- File Icon path and name
    file_size                       number(12,2),                                                   -- 파일 크기(kb)
    repository_path                 varchar2(2000)                                      not null,   -- 파일경로
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_zebra_board_file foreign key(article_id) references zebra_board(article_id),
    constraint pk_zebra_board_file primary key(file_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  zebra_board_file                    is '게시판 첨부파일';
comment on column zebra_board_file.file_id            is '파일 unique id';
comment on column zebra_board_file.article_id         is '게시물 unique id';
comment on column zebra_board_file.original_name      is '실제 파일명';
comment on column zebra_board_file.new_name           is '시스템 설정 파일명';
comment on column zebra_board_file.file_type          is '파일 type';
comment on column zebra_board_file.file_icon          is 'File Icon path and name';
comment on column zebra_board_file.file_size          is '파일 크기(kb)';
comment on column zebra_board_file.repository_path    is '파일경로';
comment on column zebra_board_file.insert_user_id     is '입력자 uid';
comment on column zebra_board_file.insert_date        is '입력일자';
comment on column zebra_board_file.update_user_id     is '수정자 uid';
comment on column zebra_board_file.update_date        is '수정일자';

--alter table zebra_board_file add(constraint fk_zebra_board_file foreign key(article_uid) references zebra_board(article_uid));
--create index idx_zebra_board_file on zebra_board_file(file_uid) tablespace hkaccount_idx storage(initial 3m next 3m maxextents 2000 pctincrease 0);


/**
 * Table Name  : Test_Table
 * Description : Create in PERCI_DEV
 */
drop table test_table cascade constraints;
purge recyclebin;

create table test_table (
    article_id                      varchar2(30)                                        not null,   -- Article UID (PK)
    writer_name                     varchar2(50)                                        not null,   -- Writer Name (Anonymous user is allowed to write an article with only name)
    writer_email                    varchar2(100),                                                  -- Writer e-mail
    writer_ip_address               varchar2(50),                                                   -- Writer IP Adress
    article_subject                 varchar2(1000),                                                 -- Subject
    article_contents                clob                        default empty_clob(),               -- Contents

    constraint pk_test_table primary key(article_id)
)