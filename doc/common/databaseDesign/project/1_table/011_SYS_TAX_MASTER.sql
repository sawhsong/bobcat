/**
 * Table Name  : SYS_TAX_MASTER
 * Description : Tax Master
 */
drop table sys_tax_master cascade constraints;
purge recyclebin;

create table sys_tax_master (
    tax_master_id                   varchar2(30)                                        not null,   -- UID (PK)
    tax_year                        varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_code                    varchar2(2),                                                    -- Quarter Code (PK) (sys_financial_period.quarter_code / ex. Q1, Q2...)
    wage_type                       varchar2(30),                                                   -- Wage Type (sys_common_code.wage_type)
    gross                           number(12,2)                                        not null,   -- Gross Income
    resident                        number(12,2)                                        not null,   -- Amount for resitents
    non_resident                    number(12,2)                                        not null,   -- Amount for non resitents
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_tax_master primary key(tax_master_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_tax_master                 is 'Tax Master';
comment on column sys_tax_master.tax_master_id   is 'UID (PK)';
comment on column sys_tax_master.tax_year        is 'Year (ex. 2017)';
comment on column sys_tax_master.quarter_code    is 'Quarter Code (PK) (sys_financial_period.quarter_code / ex. Q1, Q2...)';
comment on column sys_tax_master.wage_type       is 'Wage Type (sys_common_code.wage_type)';
comment on column sys_tax_master.gross           is 'Gross Income';
comment on column sys_tax_master.resident        is 'Amount for resitents';
comment on column sys_tax_master.non_resident    is 'Amount for non resitents';
comment on column sys_tax_master.insert_user_id  is 'Insert User UID';
comment on column sys_tax_master.insert_date     is 'Insert Date';
comment on column sys_tax_master.update_user_id  is 'Update User UID';
comment on column sys_tax_master.update_date     is 'Update Date';


/**
 * Table Name  : SYS_TAX_MASTER
 * Data        : 
 */
