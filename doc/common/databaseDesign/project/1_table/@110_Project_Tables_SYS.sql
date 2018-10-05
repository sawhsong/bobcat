/**
 * Category    : SYS
 * Table ID    : SYS_COMMON_CODE
 * Table Name  : Common Lookup Code
 * Description : 
 */
drop table sys_common_code cascade constraints;
purge recyclebin;

create table sys_common_code (
    code_type                       varchar2(50)                                        not null,   -- Code type (PK) - PERCI codes have 50(need to be amended to 30 later), ideal length is 30
    common_code                     varchar2(60)                                        not null,   -- Common code (PK) - PERCI codes have 60(need to be amended to 30 later), ideal length is 30
    code_meaning                    varchar2(1000)                                      not null,   -- Code Meaning
    description_ko                  varchar2(2000),                                                 -- Code Description (Korean) - PERCI codes have 2000(need to be amended to 1000 later), ideal length is 1000
    description_en                  varchar2(2000),                                                 -- Code Description (English) - PERCI codes have 2000(need to be amended to 1000 later), ideal length is 1000
    program_constants               varchar2(100)                                       not null,   -- Constants value for the common code to be used in program source code
    sort_order                      varchar2(3),                                                    -- Sort Order
    is_active                       varchar2(1)                 default 'Y',                        -- Is active?
    is_default                      varchar2(1)                 default 'N',                        -- Is default code item? (default code item should not be deleted)
    insert_user_id                  varchar2(50),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(50),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_common_code primary key(code_type, common_code),
    constraint uk_sys_common_code unique(program_constants)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_common_code                   is 'Common Lookup Code';
comment on column sys_common_code.code_type         is 'Code type (PK) - PERCI codes have 50(need to be amended to 30 later), ideal length is 30';
comment on column sys_common_code.common_code       is 'Common code (PK) - PERCI codes have 60(need to be amended to 30 later), ideal length is 30';
comment on column sys_common_code.code_meaning      is 'Code Meaning';
comment on column sys_common_code.description_ko    is 'Code Description (Korean) - PERCI codes have 2000(need to be amended to 1000 later), ideal length is 1000';
comment on column sys_common_code.description_en    is 'Code Description (English) - PERCI codes have 2000(need to be amended to 1000 later), ideal length is 1000';
comment on column sys_common_code.program_constants is 'Constants value for the common code to be used in program source code';
comment on column sys_common_code.sort_order        is 'Sort order';
comment on column sys_common_code.is_active         is 'Is active?';
comment on column sys_common_code.is_default        is 'Is default code item? (default code item should not be deleted)';
comment on column sys_common_code.insert_user_id    is 'Insert User UID';
comment on column sys_common_code.insert_date       is 'Insert Date';
comment on column sys_common_code.update_user_id    is 'Update User UID';
comment on column sys_common_code.update_date       is 'Update Date';


/**
 * Category    : SYS
 * Table ID    : SYS_MENU
 * Table Name  : Menu Info
 * Description : Use Excel file to initialise data (@121_Project_Data_SYS_MENU.xlsx)
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
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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
    person_id                       varchar2(30)                                        not null,   -- Person UID ([PERCI.HP_PERSON_D.PERSON_ID])
    auth_group_id                   varchar2(30)                default 'Z'             not null,   -- Authority group code for menu access ([sys_auth_group.auth_id], Default : Z)
    language                        varchar2(30)                                        not null,   -- Language ([sys_common_code.language_type])
    theme_type                      varchar2(30)                                        not null,   -- Theme ID ([sys_common_code.user_theme_type])
    user_type                       varchar2(30)                                        not null,   -- User type ([sys_common_code.user_type])
    email                           varchar2(100),                                                  -- Email
    max_row_per_page                number(5)                                           not null,   -- Number of rows to display on a page (config.properties - view.data.maxRowsPerPage)
    page_num_per_page               number(5)                                           not null,   -- Number of pages to display on a page (config.properties - view.data.pageNumsPerPage)
    user_status                     varchar2(30)                                        not null,   -- User status ([sys_common_code.user_status])
    photo_path                      varchar2(1000),                                                 -- User photo path
    is_active                       varchar2(1)                                         not null,   -- Is active ?
    description                     varchar2(1000),                                                 -- From PERCI(Description for the User)
    prop_to_portal                  varchar2(3),                                                    -- From PERCI(For What?)
    pin                             varchar2(30),                                                   -- From PERCI(For What?)
    disabled_date                   date,                                                           -- From PERCI(For What?)
    security_question_1             varchar2(60),                                                   -- From PERCI(For What? - sys_common_code.PORTAL_SECURITY_QUESTIONS)
    security_question_answer_1      varchar2(500),                                                  -- From PERCI()
    security_question_2             varchar2(60),                                                   -- From PERCI(For What? - sys_common_code.PORTAL_SECURITY_QUESTIONS)
    security_question_answer_2      varchar2(500),                                                  -- From PERCI()
    portal_skin                     varchar2(60),                                                   -- From PERCI(sys_common_code.PORTAL_SKIN)
    portal_security_role            varchar2(60),                                                   -- From PERCI(sys_common_code.PORTAL_SECURITY_GROUP)
    reset_password                  varchar2(30),                                                   -- From PERCI(For What?)
    reset_term_condition            varchar2(30),                                                   -- From PERCI(For What?)
    is_portal_user                  varchar2(30),                                                   -- From PERCI(For What?)
    portal_org_profile_id           varchar2(30),                                                   -- From PERCI(For What?)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_user primary key(user_id),
    constraint fk_sys_user_auth_group foreign key(auth_group_id) references sys_auth_group(auth_group_id),
    constraint uk_sys_user unique(login_id, login_password)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_user                            is 'User Info';
comment on column sys_user.user_id                    is 'User UID (PK)';
comment on column sys_user.user_name                  is 'User name';
comment on column sys_user.login_id                   is 'Login ID';
comment on column sys_user.login_password             is 'Login password';
comment on column sys_user.person_id                  is 'Person UID ([PERCI.HP_PERSON_D.PERSON_ID])';
comment on column sys_user.auth_group_id              is 'Authority group code for menu access ([sys_auth_group.auth_id], Default : Z)';
comment on column sys_user.language                   is 'Language ([sys_common_code.language_type])';
comment on column sys_user.theme_type                 is 'Theme ID ([sys_common_code.user_theme_type])';
comment on column sys_user.user_type                  is 'User type ([sys_common_code.user_type])';
comment on column sys_user.email                      is 'Email';
comment on column sys_user.max_row_per_page           is 'Number of rows to display on a page (config.properties - view.data.maxRowsPerPage)';
comment on column sys_user.page_num_per_page          is 'Number of pages to display on a page (config.properties - view.data.pageNumsPerPage)';
comment on column sys_user.user_status                is 'User status ([sys_common_code.user_status])';
comment on column sys_user.photo_path                 is 'User photo path';
comment on column sys_user.is_active                  is 'Is active?';
comment on column sys_user.description                is 'From PERCI(Description for the User)';
comment on column sys_user.prop_to_portal             is 'From PERCI(For What?)';
comment on column sys_user.pin                        is 'From PERCI(For What?)';
comment on column sys_user.disabled_date              is 'From PERCI(For What?)';
comment on column sys_user.security_question_1        is 'From PERCI(For What? - sys_common_code.PORTAL_SECURITY_QUESTIONS)';
comment on column sys_user.security_question_answer_1 is 'From PERCI()';
comment on column sys_user.security_question_2        is 'From PERCI(For What? - sys_common_code.PORTAL_SECURITY_QUESTIONS)';
comment on column sys_user.security_question_answer_2 is 'From PERCI()';
comment on column sys_user.portal_skin                is 'From PERCI(sys_common_code.PORTAL_SKIN)';
comment on column sys_user.portal_security_role       is 'From PERCI(sys_common_code.PORTAL_SECURITY_GROUP)';
comment on column sys_user.reset_password             is 'From PERCI(For What?)';
comment on column sys_user.reset_term_condition       is 'From PERCI(For What?)';
comment on column sys_user.is_portal_user             is 'From PERCI(For What?)';
comment on column sys_user.portal_org_profile_id      is 'From PERCI(For What?)';
comment on column sys_user.insert_user_id             is 'Insert User UID';
comment on column sys_user.insert_date                is 'Insert Date';
comment on column sys_user.update_user_id             is 'Update User UID';
comment on column sys_user.update_date                is 'Update Date';


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
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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

    constraint pk_sys_menu_auth_link primary key(group_id, menu_id),
    constraint fk_sys_menu_auth_link_auth_group foreign key(group_id) references sys_auth_group(group_id),
    constraint fk_sys_menu_auth_link_sys_menu foreign key(menu_id) references sys_menu(menu_id),
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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
 * Description : Use Excel file to initialise data (@122_Project_Data_SYS_COUNTRY_CURRENCY.xlsx)
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
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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

    constraint pk_sys_board_file primary key(file_id),
    constraint fk_sys_board_file_sys_board foreign key(article_id) references sys_board(article_id)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

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
