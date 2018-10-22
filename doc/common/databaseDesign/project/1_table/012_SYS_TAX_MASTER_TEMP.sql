/**
 * Table Name  : SYS_TAX_MASTER_TEMP
 * Description : Temporary Tax Master table for excel file upload
 */
drop table sys_tax_master_temp cascade constraints;
purge recyclebin;

create table sys_tax_master_temp (
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

    constraint pk_sys_tax_master_temp primary key(tax_master_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_tax_master_temp                 is 'Tax Master';
comment on column sys_tax_master_temp.tax_master_id   is 'UID (PK)';
comment on column sys_tax_master_temp.tax_year        is 'Year (ex. 2017)';
comment on column sys_tax_master_temp.quarter_code    is 'Quarter Code (PK) (sys_financial_period.quarter_code / ex. Q1, Q2...)';
comment on column sys_tax_master_temp.wage_type       is 'Wage Type (sys_common_code.wage_type)';
comment on column sys_tax_master_temp.gross           is 'Gross Income';
comment on column sys_tax_master_temp.resident        is 'Amount for resitents';
comment on column sys_tax_master_temp.non_resident    is 'Amount for non resitents';
comment on column sys_tax_master_temp.insert_user_id  is 'Insert User UID';
comment on column sys_tax_master_temp.insert_date     is 'Insert Date';
comment on column sys_tax_master_temp.update_user_id  is 'Update User UID';
comment on column sys_tax_master_temp.update_date     is 'Update Date';


/**
 * Table Name  : SYS_TAX_MASTER_TEMP
 * Data        : 
 */
