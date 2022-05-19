/**
 * Table Name  : SYS_FINANCIAL_PERIOD
 * Description : Quarter type by financial year
 */
drop table sys_financial_period cascade constraints;
purge recyclebin;

create table sys_financial_period (
    period_year                     varchar2(30)                                 not null,      -- Year (PK)
    quarter_code                    varchar2(30)                                 not null,      -- Quarter Code (PK) (sys_common_code.quarter_code)
    financial_year                  varchar2(30),                                               -- Financial Year (ex. 2016 - 2017)
    quarter_name                    varchar2(30)                                 not null,      -- Quarter Name (sys_common_code.quarter_name)
    date_from                       date                                         not null,      -- Date From
    date_to                         date                                         not null,      -- Date To
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint pk_sys_financial_period primary key(period_year, quarter_code)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_financial_period                                           is 'Quarter type by financial year';
comment on column sys_financial_period.period_year                               is 'Year (PK)';
comment on column sys_financial_period.quarter_code                              is 'Quarter Code (PK) (sys_common_code.quarter_code)';
comment on column sys_financial_period.financial_year                            is 'Financial Year (ex. 2016 - 2017)';
comment on column sys_financial_period.quarter_name                              is 'Quarter Name (sys_common_code.quarter_name)';
comment on column sys_financial_period.date_from                                 is 'Date From';
comment on column sys_financial_period.date_to                                   is 'Date To';
comment on column sys_financial_period.insert_user_id                            is 'Insert User UID';
comment on column sys_financial_period.insert_date                               is 'Insert Date';
comment on column sys_financial_period.update_user_id                            is 'Update User UID';
comment on column sys_financial_period.update_date                               is 'Update Date';


/**
 * Table Name  : SYS_FINANCIAL_PERIOD
 * Data        : 
 */
