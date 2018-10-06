/**
 * Table Name  : ZEBRA_COLUMN_CREATION_INFO
 * Description : Column info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_TABLE_CREATION_INFO table
 */
drop table zebra_column_creation_info cascade constraints;
purge recyclebin;

create table zebra_column_creation_info (
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
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table zebra_column_creation_info is 'Column info to be created';

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
 * Table Name  : ZEBRA_COLUMN_CREATION_INFO
 * Description : Column info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_TABLE_CREATION_INFO table
 */
