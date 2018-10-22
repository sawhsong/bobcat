/**
 * Table Name  : SYS_USER
 * Description : User Info - Use Excel file to initialise data (SYS_USER_1.xlsx, SYS_USER_2.xlsx)
 */
drop table sys_user cascade constraints;
purge recyclebin;

create table sys_user (
    user_id                         varchar2(30)                                        not null,   -- User UID (PK)
    user_name                       varchar2(50)                                        not null,   -- User name
    login_id                        varchar2(30)                                        not null,   -- Login ID
    login_password                  varchar2(30)                                        not null,   -- Login password
    org_id                          varchar2(30)                                        not null,   -- Organisation UID
    auth_group_id                   varchar2(30)                default 'Z'             not null,   -- Authority group code for menu access ([sys_auth_group.auth_id], Default : Z)
    language                        varchar2(30)                                        not null,   -- Language ([sys_common_code.language_type])
    theme_type                      varchar2(30)                                        not null,   -- Theme ID ([sys_common_code.user_theme_type])
    user_type                       varchar2(30)                                        not null,   -- User type ([sys_common_code.user_type])
    email                           varchar2(100),                                                  -- Email
    max_row_per_page                number(5)                                           not null,   -- Number of rows to display on a page (config.properties - view.data.maxRowsPerPage)
    page_num_per_page               number(5)                                           not null,   -- Number of pages to display on a page (config.properties - view.data.pageNumsPerPage)
    user_status                     varchar2(30)                                        not null,   -- User status ([sys_common_code.user_status])
    photo_path                      varchar2(2000),                                                 -- User photo path
    is_active                       varchar2(1)                                         not null,   -- Is active ?
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_user primary key(user_id),
    constraint uk_sys_user unique(login_id, login_password)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_user                   is 'User Info';
comment on column sys_user.user_id           is 'User UID (PK)';
comment on column sys_user.user_name         is 'User name';
comment on column sys_user.login_id          is 'Login ID';
comment on column sys_user.login_password    is 'Login password';
comment on column sys_user.org_id            is 'Organisation UID';
comment on column sys_user.auth_group_id     is 'Authority group code for menu access ([sys_auth_group.auth_id], Default : Z)';
comment on column sys_user.language          is 'Language ([sys_common_code.language_type])';
comment on column sys_user.theme_type        is 'Theme ID ([sys_common_code.user_theme_type])';
comment on column sys_user.user_type         is 'User type ([sys_common_code.user_type])';
comment on column sys_user.email             is 'Email';
comment on column sys_user.max_row_per_page  is 'Number of rows to display on a page (config.properties - view.data.maxRowsPerPage)';
comment on column sys_user.page_num_per_page is 'Number of pages to display on a page (config.properties - view.data.pageNumsPerPage)';
comment on column sys_user.user_status       is 'User status ([sys_common_code.user_status])';
comment on column sys_user.photo_path        is 'User photo path';
comment on column sys_user.is_active         is 'Is active?';
comment on column sys_user.insert_user_id    is 'Insert User UID';
comment on column sys_user.insert_date       is 'Insert Date';
comment on column sys_user.update_user_id    is 'Update User UID';
comment on column sys_user.update_date       is 'Update Date';


/**
 * Table Name  : SYS_USER
 * Data        : User Info - Use Excel file to initialise data (SYS_USER_1.xlsx, SYS_USER_2.xlsx)
 */

