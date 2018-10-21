/**
 * Category    : SYS
 * Table ID    : SYS_COMMON_CODE
 * Table Name  : Common Lookup Code
 * Description : 
 */
drop table sys_common_code cascade constraints;
purge recyclebin;

create table sys_common_code (
    code_type                       varchar2(30)                                        not null,   -- Code type (PK)
    common_code                     varchar2(30)                                        not null,   -- Common code (PK)
    code_category                   varchar2(30)                                        not null,   -- Code usage category (sys_common_code.code_category)
    description_ko                  varchar2(1000),                                                 -- Code Description (Korean)
    description_en                  varchar2(1000),                                                 -- Code Description (English)
    program_constants               varchar2(100)                                       not null,   -- Constants value for the common code to be used in program source code
    sort_order                      varchar2(3),                                                    -- Sort Order
    is_active                       varchar2(1)                 default 'Y',                        -- Is active?
    is_default                      varchar2(1)                 default 'N',                        -- Is default code item? (default code item should not be deleted)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_common_code primary key(code_type, common_code),
    constraint uk_sys_common_code unique(program_constants)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_common_code                   is 'Common Lookup Code';
comment on column sys_common_code.code_type         is 'Code type (PK)';
comment on column sys_common_code.common_code       is 'Common code (PK)';
comment on column sys_common_code.code_category     is 'Code usage category (sys_common_code.code_category)';
comment on column sys_common_code.description_ko    is 'Code Description (Korean)';
comment on column sys_common_code.description_en    is 'Code Description (English)';
comment on column sys_common_code.program_constants is 'Constants value for the common code to be used in program';
comment on column sys_common_code.sort_order        is 'Sort order';
comment on column sys_common_code.is_active         is 'Is active?';
comment on column sys_common_code.is_default        is 'Is default code item? (default code item should not be deleted)';
comment on column sys_common_code.insert_user_id    is 'Insert User UID';
comment on column sys_common_code.insert_date       is 'Insert Date';
comment on column sys_common_code.update_user_id    is 'Update User UID';
comment on column sys_common_code.update_date       is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_BOARD
 * Table Name  : Bulletin board
 * Description : 
 */
drop table sys_board cascade constraints;
purge recyclebin;

create table sys_board (
    article_id                      varchar2(30)                                        not null,   -- Article UID (PK)
    board_type                      varchar2(30)                                        not null,   -- BBS Type([sys_common_code.board_type - bbs / notice])
    parent_article_id               varchar2(30)                                        not null,   -- Parent article UID (Level 1 => 0)
    writer_id                       varchar2(30),                                                   -- Writer UID ([sys_user.user_id] Anonymous user is allowed to write an article with only name)
    writer_name                     varchar2(50)                                        not null,   -- Writer Name (Anonymous user is allowed to write an article with only name)
    article_password                varchar2(30),                                                   -- Article Password (if the article is confidential)
    writer_email                    varchar2(100),                                                  -- Writer e-mail
    writer_ip_address               varchar2(50),                                                   -- Writer IP Adress
    article_subject                 varchar2(1000),                                                 -- Subject
    article_contents                clob                        default empty_clob(),               -- Contents
    hit_cnt                         number(5)                   default 0               not null,   -- Hit count
    is_confidential                 varchar2(1)                 default 'N',                        -- Is confidential?
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_board primary key(article_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_board                     is 'Bulletin Board';
comment on column sys_board.article_id          is 'Article UID (PK)';
comment on column sys_board.board_type          is 'BBS Type([sys_common_code.board_type - bbs / notice])';
comment on column sys_board.parent_article_id   is 'Parent article UID (Level 1 => 0)';
comment on column sys_board.writer_id           is 'Writer UID ([sys_user.user_id] Anonymous user is allowed to write an article with only name)';
comment on column sys_board.writer_name         is 'Writer Name (Anonymous user is allowed to write an article with only name)';
comment on column sys_board.article_password    is 'Article Password (if the article is confidential)';
comment on column sys_board.writer_email        is 'Writer e-mail';
comment on column sys_board.writer_ip_address   is 'Writer IP Adress';
comment on column sys_board.article_subject     is 'Subject';
comment on column sys_board.article_contents    is 'Contents';
comment on column sys_board.hit_cnt             is 'Hit count';
comment on column sys_board.is_confidential     is 'Is confidential?';
comment on column sys_board.insert_user_id      is 'Insert User UID';
comment on column sys_board.insert_date         is 'Insert Date';
comment on column sys_board.update_user_id      is 'Update User UID';
comment on column sys_board.update_date         is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_BOARD_FILE
 * Table Name  : Attached file for Bulletin board
 * Description : 
 */
drop table sys_board_file cascade constraints;
purge recyclebin;

create table sys_board_file (
    file_id                         varchar2(30)                                        not null,   -- File item UID (PK)
    article_id                      varchar2(30)                                        not null,   -- BBS Article UID ([sys_board.article_id])
    original_name                   varchar2(1000)                                      not null,   -- Real file name
    new_name                        varchar2(1000)                                      not null,   -- System defined file name
    file_type                       varchar2(300),                                                  -- File type
    file_icon                       varchar2(1000),                                                 -- File Icon path and name
    file_size                       number(12,2),                                                   -- File size (KB)
    repository_path                 varchar2(2000)                                      not null,   -- Saved file path
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_sys_board_file foreign key(article_id) references sys_board(article_id),
    constraint pk_sys_board_file primary key(file_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_board_file                    is 'Attached file for Bulletin board';
comment on column sys_board_file.file_id            is 'File item UID (PK)';
comment on column sys_board_file.article_id         is 'BBS Article UID ([sys_board.article_id])';
comment on column sys_board_file.original_name      is 'Real file name';
comment on column sys_board_file.new_name           is 'System defined file name';
comment on column sys_board_file.file_type          is 'File type';
comment on column sys_board_file.file_icon          is 'File Icon path and name';
comment on column sys_board_file.file_size          is 'File size (KB)';
comment on column sys_board_file.repository_path    is 'Saved file path';
comment on column sys_board_file.insert_user_id     is 'Insert User UID';
comment on column sys_board_file.insert_date        is 'Insert Date';
comment on column sys_board_file.update_user_id     is 'Update User UID';
comment on column sys_board_file.update_date        is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_MENU
 * Table Name  : Menu Info
 * Description : Use Excel file to initialise data
 */
drop table sys_menu cascade constraints;
purge recyclebin;

create table sys_menu (
    menu_id                         varchar2(30)                                        not null,   -- Menu UID (PK)
    parent_menu_id                  varchar2(30),                                                   -- Parent menu UID
    menu_name_ko                    varchar2(500)                                       not null,   -- Menu name (Korean)
    menu_name_en                    varchar2(500)                                       not null,   -- Menu name (English)
    menu_url                        varchar2(500),                                                  -- Menu URL
    menu_icon                       varchar2(100),                                                  -- Menu Icon
    sort_order                      varchar2(10),                                                   -- Sort order
    description                     varchar2(1000),                                                 -- Description
    is_active                       varchar2(1),                                                    -- Is active ?
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_menu primary key(menu_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_menu                 is 'Menu Info';
comment on column sys_menu.menu_id         is 'Menu UID (PK)';
comment on column sys_menu.parent_menu_id  is 'Parent menu UID';
comment on column sys_menu.menu_name_ko    is 'Menu name (Korean)';
comment on column sys_menu.menu_name_en    is 'Menu name (English)';
comment on column sys_menu.menu_url        is 'Menu URL';
comment on column sys_menu.menu_icon       is 'Menu Icon';
comment on column sys_menu.sort_order      is 'Sort order';
comment on column sys_menu.description     is 'Description';
comment on column sys_menu.is_active       is 'Is active?';
comment on column sys_menu.insert_user_id  is 'Insert User UID';
comment on column sys_menu.insert_date     is 'Insert Date';
comment on column sys_menu.update_user_id  is 'Update User UID';
comment on column sys_menu.update_date     is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_USER
 * Table Name  : User Info
 * Description : Use Excel file to initialise data
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
 * Category    : SYS
 * Table ID    : SYS_AUTH_GROUP
 * Table Name  : Menu Authority Info
 * Description : 
 */
drop table sys_auth_group cascade constraints;
purge recyclebin;

create table sys_auth_group (
    group_id                        varchar2(30)                                        not null,   -- Authority group UID (PK)
    group_name                      varchar2(50)                                        not null,   -- Authority group name
    description                     varchar2(1000),                                                 -- Description
    is_active                       varchar2(1),                                                    -- Is active ?
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_auth_group primary key(group_id),
    constraint uk_sys_auth_group unique(group_name)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_auth_group                 is 'Menu Authority Info';
comment on column sys_auth_group.group_id        is 'Authority group UID (PK)';
comment on column sys_auth_group.group_name      is 'Authority group name';
comment on column sys_auth_group.description     is 'Description';
comment on column sys_auth_group.is_active       is 'Is active?';
comment on column sys_auth_group.insert_user_id  is 'Insert User UID';
comment on column sys_auth_group.insert_date     is 'Insert Date';
comment on column sys_auth_group.update_user_id  is 'Update User UID';
comment on column sys_auth_group.update_date     is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_MENU_AUTH_LINK
 * Table Name  : Menu - Authority group mapping
 * Description : 
 */
drop table sys_menu_auth_link cascade constraints;
purge recyclebin;

create table sys_menu_auth_link (
    group_id                        varchar2(30)                                        not null,   -- Authority group UID (PK) ([sys_auth_group.auth_id])
    menu_id                         varchar2(30)                                        not null,   -- Menu UID (PK) ([sys_menu.menu_id])
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_menu_auth_link primary key(group_id, menu_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_menu_auth_link                 is 'Menu - Authority group mapping';
comment on column sys_menu_auth_link.group_id        is 'Authority group UID (PK) ([sys_auth_group.auth_id])';
comment on column sys_menu_auth_link.menu_id         is 'Menu UID (PK) ([sys_menu.menu_id])';
comment on column sys_menu_auth_link.insert_user_id  is 'Insert User UID';
comment on column sys_menu_auth_link.insert_date     is 'Insert Date';
comment on column sys_menu_auth_link.update_user_id  is 'Update User UID';
comment on column sys_menu_auth_link.update_date     is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_COUNTRY_CURRENCY
 * Table Name  : Country and Currency Info
 * Description : 
 */
drop table sys_country_currency cascade constraints;
purge recyclebin;

create table sys_country_currency (
    country_currency_id             varchar2(30)                                        not null,   -- Country and Currency UID (PK)
    currency_name                   varchar2(300),                                                  -- Currency Name
    currency_symbol                 varchar2(10),                                                   -- Currency Symbol
    currency_alphabetic_code        varchar2(5),                                                    -- Currency Alphabetic Code
    currency_numeric_code           varchar2(5),                                                    -- Currency Numeric Code
    country_name                    varchar2(300),                                                  -- Country Name
    country_code_2                  varchar2(5),                                                    -- Country Code (2 Digit)
    country_code_3                  varchar2(5),                                                    -- Country Code (3 Digit)
    country_numeric_code            varchar2(5),                                                    -- Country Code Numeric
    country_language_code           varchar2(5),                                                    -- Country Language Code
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_country_currency primary key(country_currency_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_country_currency                          is 'Country and Currency Info';
comment on column sys_country_currency.country_currency_id      is 'Country and Currency UID (PK)';
comment on column sys_country_currency.currency_name            is 'Currency Name';
comment on column sys_country_currency.currency_symbol          is 'Currency Symbol';
comment on column sys_country_currency.currency_alphabetic_code is 'Currency Alphabetic Code';
comment on column sys_country_currency.currency_numeric_code    is 'Currency Numeric Code';
comment on column sys_country_currency.country_name             is 'Country Name';
comment on column sys_country_currency.country_code_2           is 'Country Code (2 Digit)';
comment on column sys_country_currency.country_code_3           is 'Country Code (3 Digit)';
comment on column sys_country_currency.country_numeric_code     is 'Country Code Numeric';
comment on column sys_country_currency.country_language_code    is 'Country Language Code';
comment on column sys_country_currency.insert_user_id           is 'Insert User UID';
comment on column sys_country_currency.insert_date              is 'Insert Date';
comment on column sys_country_currency.update_user_id           is 'Update User UID';
comment on column sys_country_currency.update_date              is 'Update Date';

update sys_country_currency
   set country_name = initcap(country_name)
;


/**
 * Category    : SYS
 * Table ID    : SYS_ORG
 * Table Name  : Organisation Info
 * Description : 
 */
drop table sys_org cascade constraints;
purge recyclebin;

create table sys_org (
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (PK)
    legal_name                      varchar2(300)                                       not null,   -- Org legal name
    trading_name                    varchar2(300),                                                  -- Org trading name
    abn                             varchar2(30),                                                   -- Org ABN
    email                           varchar2(100),                                                  -- Org Email
    postal_address                  varchar2(500),                                                  -- Org Postal Address
    registered_date                 date,                                                           -- Registered Date
    entity_type                     varchar2(30)                                        not null,   -- Entity Type - sys_common_code.entity_type
    business_type                   varchar2(30)                                        not null,   -- Business Type - sys_common_code.business_type
    org_category                    varchar2(30)                                        not null,   -- Organisation Catetory - sys_common_code.org_category
    base_type                       varchar2(30)                                        not null,   -- Base Type - sys_common_code.base_type
    wage_type                       varchar2(30)                                        not null,   -- Wage Type - sys_common_code.wage_type
    revenue_range_from              number(12, 2),                                                  -- Revenue Range From
    revenue_range_to                number(12, 2),                                                  -- Revenue Range To
    is_active                       varchar2(1),                                                    -- Is Active
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_org primary key(org_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_org                    is 'Organisation Info';
comment on column sys_org.org_id             is 'Organisation UID (PK)';
comment on column sys_org.legal_name         is 'Org legal name';
comment on column sys_org.trading_name       is 'Org trading name';
comment on column sys_org.abn                is 'Org ABN';
comment on column sys_org.email              is 'Org Email';
comment on column sys_org.postal_address     is 'Org Postal Address';
comment on column sys_org.registered_date    is 'Registered Date';
comment on column sys_org.entity_type        is 'Entity Type - sys_common_code.entity_type';
comment on column sys_org.business_type      is 'Business Type - sys_common_code.business_type';
comment on column sys_org.org_category       is 'Organisation Catetory - sys_common_code.org_category';
comment on column sys_org.base_type          is 'Base Type - sys_common_code.base_type';
comment on column sys_org.wage_type          is 'Wage Type - sys_common_code.wage_type';
comment on column sys_org.revenue_range_from is 'Revenue Range From';
comment on column sys_org.revenue_range_to   is 'Revenue Range To';
comment on column sys_org.is_active          is 'Is active?';
comment on column sys_org.insert_user_id     is 'Insert User UID';
comment on column sys_org.insert_date        is 'Insert Date';
comment on column sys_org.update_user_id     is 'Update User UID';
comment on column sys_org.update_date        is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_FINANCIAL_PERIOD
 * Table Name  : Quarter type by financial year
 * Description : 
 */
drop table sys_financial_period cascade constraints;
purge recyclebin;

create table sys_financial_period (
    period_year                     varchar2(4)                                         not null,   -- Year (PK)
    quarter_code                    varchar2(30)                                        not null,   -- Quarter Code (PK) (sys_common_code.quarter_code)
    financial_year                  varchar2(30),                                                   -- Financial Year (ex. 2016 - 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    date_from                       date                                                not null,   -- Date From
    date_to                         date                                                not null,   -- Date To
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_financial_period primary key(period_year, quarter_code)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_financial_period                 is 'Quarter type by financial year';
comment on column sys_financial_period.period_year     is 'Year (PK)';
comment on column sys_financial_period.quarter_code    is 'Quarter Code (PK) (sys_common_code.quarter_code)';
comment on column sys_financial_period.financial_year  is 'Financial Year (ex. 2016 - 2017)';
comment on column sys_financial_period.quarter_name    is 'Quarter Name (sys_common_code.quarter_name)';
comment on column sys_financial_period.date_from       is 'Date From';
comment on column sys_financial_period.date_to         is 'Date To';
comment on column sys_financial_period.insert_user_id  is 'Insert User UID';
comment on column sys_financial_period.insert_date     is 'Insert Date';
comment on column sys_financial_period.update_user_id  is 'Update User UID';
comment on column sys_financial_period.update_date     is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_TAX_MASTER
 * Table Name  : Tax Master
 * Description : 
 */
drop table sys_tax_master cascade constraints;
purge recyclebin;

create table sys_tax_master (
    tax_master_id                   varchar2(30)                                        not null,   -- UID (PK)
    tax_year                        varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_code                    varchar2(2),                                                    -- Quarter Code (PK) (sys_financial_period.quarter_code / ex. Q1, Q2...)
    wage_type                       varchar2(30),                                                   -- Wage Type (sys_common_code.wage_type)
    gross                           number(12, 2)                                       not null,   -- Gross Income
    resident                        number(12, 2)                                       not null,   -- Amount for resitents
    non_resident                    number(12, 2)                                       not null,   -- Amount for non resitents
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
 * Category    : SYS
 * Table ID    : SYS_TAX_MASTER_TEMP
 * Table Name  : Temporary Tax Master
 * Description : Temporary Tax Master table for excel file upload
 */
drop table sys_tax_master_temp cascade constraints;
purge recyclebin;

create table sys_tax_master_temp (
    tax_master_id                   varchar2(30)                                        not null,   -- UID (PK)
    tax_year                        varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_code                    varchar2(2),                                                    -- Quarter Code (PK) (sys_financial_period.quarter_code / ex. Q1, Q2...)
    wage_type                       varchar2(30),                                                   -- Wage Type (sys_common_code.wage_type)
    gross                           number(12, 2)                                       not null,   -- Gross Income
    resident                        number(12, 2)                                       not null,   -- Amount for resitents
    non_resident                    number(12, 2)                                       not null,   -- Amount for non resitents
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
 * Category    : SYS
 * Table ID    : SYS_INCOME_TYPE
 * Table Name  : Income Entry Type
 * Description : 
 */
drop table sys_income_type cascade constraints;
purge recyclebin;

create table sys_income_type (
    income_type_id                  varchar2(30)                                        not null,   -- Income type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    income_type                     varchar2(30)                                        not null,   -- Income Type (UK) (sys_common_code.income_main_type or sys_common_code.income_sub_type)
    parent_income_type              varchar2(30),                                                   -- Parent Income Type (always sys_common_code.income_main_type)
    description                     varchar2(100)                                       not null,   -- Income Type Description (comes from sys_common_code.income_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_income_type primary key(income_type_id),
    constraint uk_sys_income_type unique(org_category, income_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_income_type                       is 'Income Entry Type';
comment on column sys_income_type.income_type_id        is 'Income type UID (PK)';
comment on column sys_income_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_income_type.income_type           is 'Income Type (UK) (sys_common_code.income_main_type or sys_common_code.income_sub_type)';
comment on column sys_income_type.parent_income_type    is 'Parent Income Type (always sys_common_code.income_main_type)';
comment on column sys_income_type.description           is 'Income Type Description (comes from sys_common_code.income_type.description_en, user can change)';
comment on column sys_income_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_income_type.gst_percentage        is 'GST Percentage';
comment on column sys_income_type.account_code          is 'Account Code';
comment on column sys_income_type.sort_order            is 'Sort Order';
comment on column sys_income_type.insert_user_id        is 'Insert User UID';
comment on column sys_income_type.insert_date           is 'Insert Date';
comment on column sys_income_type.update_user_id        is 'Update User UID';
comment on column sys_income_type.update_date           is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_EXPENSE_TYPE
 * Table Name  : Expenditure Entry Type
 * Description : 
 */
drop table sys_expense_type cascade constraints;
purge recyclebin;

create table sys_expense_type (
    expense_type_id                 varchar2(30)                                        not null,   -- Expense Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    expense_type                    varchar2(30)                                        not null,   -- Expense Type (UK) (sys_common_code.expense_main_type or sys_common_code.expense_sub_type)
    parent_expense_type             varchar2(30),                                                   -- Parent Expense Type (always sys_common_code.expense_main_type)
    description                     varchar2(100)                                       not null,   -- Expense Type Description (comes from sys_common_code.expense_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_expense_type primary key(expense_type_id),
    constraint uk_sys_expense_type unique(org_category, expense_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_expense_type                       is 'Expenditure Entry Type';
comment on column sys_expense_type.expense_type_id       is 'Expense Type UID (PK)';
comment on column sys_expense_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_expense_type.expense_type          is 'Expense Type (UK) (sys_common_code.expense_main_type)';
comment on column sys_expense_type.parent_expense_type   is 'Parent Expense Type (always sys_common_code.expense_main_type)';
comment on column sys_expense_type.description           is 'Expense Type Description (comes from sys_common_code.expense_main_type.description_en, user can change)';
comment on column sys_expense_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_expense_type.gst_percentage        is 'GST Percentage';
comment on column sys_expense_type.account_code          is 'Account Code';
comment on column sys_expense_type.sort_order            is 'Sort Order';
comment on column sys_expense_type.insert_user_id        is 'Insert User UID';
comment on column sys_expense_type.insert_date           is 'Insert Date';
comment on column sys_expense_type.update_user_id        is 'Update User UID';
comment on column sys_expense_type.update_date           is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_ASSET_TYPE
 * Table Name  : Asset Entry Type
 * Description : 
 */
drop table sys_asset_type cascade constraints;
purge recyclebin;

create table sys_asset_type (
    asset_type_id                   varchar2(30)                                        not null,   -- Asset Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    asset_type                      varchar2(30)                                        not null,   -- Asset Type (UK) (sys_common_code.asset_main_type or sys_common_code.asset_sub_type)
    parent_asset_type               varchar2(30),                                                   -- Parent Asset Type (always sys_common_code.asset_main_type)
    description                     varchar2(100)                                       not null,   -- Asset Type Description (comes from sys_common_code.asset_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_asset_type primary key(asset_type_id),
    constraint uk_sys_asset_type unique(org_category, asset_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_asset_type                       is 'Asset Entry Type';
comment on column sys_asset_type.asset_type_id         is 'Asset Type UID (PK)';
comment on column sys_asset_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_asset_type.asset_type            is 'Asset Type (UK) (sys_common_code.asset_main_type or sys_common_code.asset_sub_type)';
comment on column sys_asset_type.parent_asset_type     is 'Parent Asset Type (always sys_common_code.asset_main_type)';
comment on column sys_asset_type.description           is 'Asset Type Description (comes from sys_common_code.asset_main_type.description_en, user can change)';
comment on column sys_asset_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_asset_type.gst_percentage        is 'GST Percentage';
comment on column sys_asset_type.account_code          is 'Account Code';
comment on column sys_asset_type.sort_order            is 'Sort Order';
comment on column sys_asset_type.insert_user_id        is 'Insert User UID';
comment on column sys_asset_type.insert_date           is 'Insert Date';
comment on column sys_asset_type.update_user_id        is 'Update User UID';
comment on column sys_asset_type.update_date           is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_REPAYMENT_TYPE
 * Table Name  : Repayment Entry Type
 * Description : Loan Repayment type
 */
drop table sys_repayment_type cascade constraints;
purge recyclebin;

create table sys_repayment_type (
    repayment_type_id               varchar2(30)                                        not null,   -- Repayment Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    repayment_type                  varchar2(30)                                        not null,   -- Repayment Type (UK) (sys_common_code.repayment_main_type or sys_common_code.repayment_sub_type)
    parent_repayment_type           varchar2(30),                                                   -- Parent Repayment Type (always sys_common_code.repayment_main_type)
    description                     varchar2(100)                                       not null,   -- Repayment Type Description (comes from sys_common_code.repayment_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_repayment_type primary key(repayment_type_id),
    constraint uk_sys_repayment_type unique(org_category, repayment_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_repayment_type                       is 'Repayment Entry Type';
comment on column sys_repayment_type.repayment_type_id     is 'Repayment Type UID (PK)';
comment on column sys_repayment_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_repayment_type.repayment_type        is 'Repayment Type (UK) (sys_common_code.repayment_main_type or sys_common_code.repayment_sub_type)';
comment on column sys_repayment_type.parent_repayment_type is 'Parent Repayment Type (always sys_common_code.repayment_main_type)';
comment on column sys_repayment_type.description           is 'Repayment Type Description (comes from sys_common_code.repayment_main_type.description_en, user can change)';
comment on column sys_repayment_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_repayment_type.gst_percentage        is 'GST Percentage';
comment on column sys_repayment_type.account_code          is 'Account Code';
comment on column sys_repayment_type.sort_order            is 'Sort Order';
comment on column sys_repayment_type.insert_user_id        is 'Insert User UID';
comment on column sys_repayment_type.insert_date           is 'Insert Date';
comment on column sys_repayment_type.update_user_id        is 'Update User UID';
comment on column sys_repayment_type.update_date           is 'Update Date';



/**
 * Category    : SYS
 * Table ID    : SYS_BORROWING_TYPE
 * Table Name  : Borrowing Entry Type
 * Description : Loan Borrowing type
 */
drop table sys_borrowing_type cascade constraints;
purge recyclebin;

create table sys_borrowing_type (
    borrowing_type_id               varchar2(30)                                        not null,   -- Borrowing Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    borrowing_type                  varchar2(30)                                        not null,   -- Borrowing Type (UK) (sys_common_code.borrowing_main_type or sys_common_code.borrowing_sub_type)
    parent_borrowing_type           varchar2(30),                                                   -- Parent Borrowing Type (always sys_common_code.borrowing_main_type)
    description                     varchar2(100)                                       not null,   -- Borrowing Type Description (comes from sys_common_code.borrowing_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_borrowing_type primary key(borrowing_type_id),
    constraint uk_sys_borrowing_type unique(org_category, borrowing_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_borrowing_type                       is 'Borrowing Entry Type';
comment on column sys_borrowing_type.borrowing_type_id     is 'Borrowing Type UID (PK)';
comment on column sys_borrowing_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_borrowing_type.borrowing_type        is 'Borrowing Type (UK) (sys_common_code.borrowing_main_type or sys_common_code.borrowing_sub_type)';
comment on column sys_borrowing_type.parent_borrowing_type is 'Parent Borrowing Type (always sys_common_code.borrowing_main_type)';
comment on column sys_borrowing_type.description           is 'Borrowing Type Description (comes from sys_common_code.borrowing_main_type.description_en, user can change)';
comment on column sys_borrowing_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_borrowing_type.gst_percentage        is 'GST Percentage';
comment on column sys_borrowing_type.account_code          is 'Account Code';
comment on column sys_borrowing_type.sort_order            is 'Sort Order';
comment on column sys_borrowing_type.insert_user_id        is 'Insert User UID';
comment on column sys_borrowing_type.insert_date           is 'Insert Date';
comment on column sys_borrowing_type.update_user_id        is 'Update User UID';
comment on column sys_borrowing_type.update_date           is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_LENDING_TYPE
 * Table Name  : Lending Entry Type
 * Description : 
 */
drop table sys_lending_type cascade constraints;
purge recyclebin;

create table sys_lending_type (
    lending_type_id                 varchar2(30)                                        not null,   -- Lending Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    lending_type                    varchar2(30)                                        not null,   -- Lending Type (UK) (sys_common_code.lending_main_type or sys_common_code.lending_sub_type)
    parent_lending_type             varchar2(30),                                                   -- Parent Lending Type (always sys_common_code.lending_main_type)
    description                     varchar2(100)                                       not null,   -- Lending Type Description (comes from sys_common_code.lending_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_lending_type primary key(lending_type_id),
    constraint uk_sys_lending_type unique(org_category, lending_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_lending_type                       is 'Lending Entry Type';
comment on column sys_lending_type.lending_type_id       is 'Lending Type UID (PK)';
comment on column sys_lending_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_lending_type.lending_type          is 'Lending Type (UK) (sys_common_code.lending_main_type or sys_common_code.lending_sub_type)';
comment on column sys_lending_type.parent_lending_type   is 'Parent lending Type (always sys_common_code.lending_main_type)';
comment on column sys_lending_type.description           is 'Lending Type Description (comes from sys_common_code.lending_main_type.description_en, user can change)';
comment on column sys_lending_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_lending_type.gst_percentage        is 'GST Percentage';
comment on column sys_lending_type.account_code          is 'Account Code';
comment on column sys_lending_type.sort_order            is 'Sort Order';
comment on column sys_lending_type.insert_user_id        is 'Insert User UID';
comment on column sys_lending_type.insert_date           is 'Insert Date';
comment on column sys_lending_type.update_user_id        is 'Update User UID';
comment on column sys_lending_type.update_date           is 'Update Date';

