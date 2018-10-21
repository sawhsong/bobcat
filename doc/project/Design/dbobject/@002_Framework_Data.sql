/**
 * Table Name  : Domain Dictionary
 * Description : Database column data types
 */
delete zebra_domain_dictionary;

insert into zebra_domain_dictionary values('1',    'id',                   'id',                   'VARCHAR2',  null,  null,  30,    'uid, id',                           '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('2',    'password',             'password',             'VARCHAR2',  null,  null,  30,    'password',                          '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('3',    'common_code',          'common_code',          'VARCHAR2',  null,  null,  30,    'common_code.common_code',           '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('4',    'normal_name',          'name',                 'VARCHAR2',  null,  null,  50,    'person name, user name, org name',  '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('5',    'file_name',            'file_name',            'VARCHAR2',  null,  null,  1000,  'file name, image/icon name',        '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('6',    'menu_name',            'menu_name',            'VARCHAR2',  null,  null,  500,   'menu name',                         '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('7',    'email',                'email',                'VARCHAR2',  null,  null,  100,   'email',                             '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('8',    'ip_address',           'ip_address',           'VARCHAR2',  null,  null,  50,    'ip_address (considering ipv6)',     '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('9',    'size',                 'size',                 'NUMBER',    24,    2,     24,    'size',                              '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('10',   'quantity',             'qty',                  'NUMBER',    24,    2,     24,    'quantity',                          '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('11',   'file_path',            'file_path',            'VARCHAR2',  null,  null,  2000,  'file/directory path',               '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('12',   'description_short',    'description',          'VARCHAR2',  null,  null,  1000,  'short description',                 '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('13',   'description_normal',   'description',          'VARCHAR2',  null,  null,  2000,  'normal description',                '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('14',   'file_type',            'file_type',            'VARCHAR2',  null,  null,  300,   'file type',                         '0',  sysdate,  '',  '');
insert into zebra_domain_dictionary values('15',   'constants',            'constants',            'VARCHAR2',  null,  null,  100,   'constants (common code constants)', '0',  sysdate,  '',  '');


/**
 * Table Name  : 공통코드
 * Description : 공통코드정보
 */
delete zebra_common_code;

insert into zebra_common_code values('USE_YN','0000000000',     '사용여부',  'Use or Not',    'USE_YN_0000000000',  '000',   'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USE_YN','Y',              '사용',      'Use',           'USE_YN_Y',           '001',   'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USE_YN','N',              '미사용',    'Not Use',       'USE_YN_N',           '002',   'Y','Y','0',sysdate,'','');

insert into zebra_common_code values('BOARD_TYPE','0000000000', '게시판구분',  'Board Type',     'BOARD_TYPE_0000000000',  '000','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_TYPE','FREE',       '자유게시판',  'Free Board',     'BOARD_TYPE_FREE',        '001','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_TYPE','NOTICE',     '공지사항',    'Notice Board',   'BOARD_TYPE_NOTICE',      '002','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_TYPE','REPOSITORY', '자료실',      'Data Repository','BOARD_TYPE_REPOSITORY',  '003','Y','Y','0',sysdate,'','');

insert into zebra_common_code values('BOARD_SEARCH_TYPE', '0000000000','게시판검색형태', 'Board Search Criteria', 'BOARD_SEARCH_TYPE_0000000000',  '000','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_SEARCH_TYPE', 'SUBJECT',   '제목',            'Subject',               'BOARD_SEARCH_TYPE_SUBJECT',     '001','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_SEARCH_TYPE', 'CONTENTS',  '내용',            'Contents',              'BOARD_SEARCH_TYPE_CONTENTS',    '002','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('BOARD_SEARCH_TYPE', 'WRITER',    '작성자',          'Writer',                'BOARD_SEARCH_TYPE_WRITER',      '003','Y','Y','0',sysdate,'','');

insert into zebra_common_code values('USER_THEME_TYPE','0000000000',   '사용자스킨구분', 'User Theme Type','USER_THEME_TYPE_0000000000',  '000','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme000',     'Bootstrap',       'Bootstrap',      'USER_THEME_TYPE_000',         '001','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme001',     'Smoothness',      'Smoothness',     'USER_THEME_TYPE_001',         '002','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme002',     'Redmond',         'Redmond',        'USER_THEME_TYPE_002',         '003','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme003',     'Lightness',       'Lightness',      'USER_THEME_TYPE_003',         '004','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme004',     'Start',           'Start',          'USER_THEME_TYPE_004',         '005','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme005',     'Sunny',           'Sunny',          'USER_THEME_TYPE_005',         '006','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme006',     'Flick',           'Flick',          'USER_THEME_TYPE_006',         '007','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme007',     'Pepper Grinder',  'Pepper Grinder', 'USER_THEME_TYPE_007',         '008','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme008',     'Cupertino',       'Cupertino',      'USER_THEME_TYPE_008',         '009','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme009',     'South Street',    'South Street',   'USER_THEME_TYPE_009',         '010','Y','Y','0',sysdate,'','');
insert into zebra_common_code values('USER_THEME_TYPE','theme010',     'Humanity',        'Humanity',       'USER_THEME_TYPE_010',         '011','Y','Y','0',sysdate,'','');

insert into zebra_common_code values('LANGUAGE_TYPE','0000000000', '언어타입',  'Language', 'LANGUAGE_TYPE_0000000000',  '000',  'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('LANGUAGE_TYPE','en',         '영어',      'English',  'LANGUAGE_TYPE_EN',          '001',   'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('LANGUAGE_TYPE','ko',         '한국어',    'Korean',   'LANGUAGE_TYPE_KO',          '002',   'Y','Y','0',sysdate,'','');

insert into zebra_common_code values('DOMAIN_DATA_TYPE', '0000000000', 'Domain Data Type',  'Domain Data Type', 'DOMAIN_DATA_TYPE_0000000000',  '000',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_TYPE', 'VARCHAR2',   'Varchar2',          'Varchar2',         'DOMAIN_DATA_TYPE_VARCHAR2',    '001',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_TYPE', 'NUMBER',     'Number',            'Number',           'DOMAIN_DATA_TYPE_NUMBER',      '002',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_TYPE', 'DATE',       'Date',              'Date',             'DOMAIN_DATA_TYPE_DATE',        '003',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_TYPE', 'CLOB',       'Clob',              'Clob',             'DOMAIN_DATA_TYPE_CLOB',        '004',  'Y', 'Y', '0', sysdate, '', '');

insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '0000000000', 'Domain Data Length',  'Domain Data Length', 'DOMAIN_DATA_LENGTH_0000000000',  '000',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '1',          '1',                   '1',                  'DOMAIN_DATA_LENGTH_1',           '001',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '3',          '3',                   '3',                  'DOMAIN_DATA_LENGTH_3',           '002',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '5',          '5',                   '5',                  'DOMAIN_DATA_LENGTH_5',           '003',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '10',         '10',                  '10',                 'DOMAIN_DATA_LENGTH_10',          '004',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '30',         '30',                  '30',                 'DOMAIN_DATA_LENGTH_30',          '005',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '50',         '50',                  '50',                 'DOMAIN_DATA_LENGTH_50',          '006',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '100',        '100',                 '100',                'DOMAIN_DATA_LENGTH_100',         '007',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '300',        '300',                 '300',                'DOMAIN_DATA_LENGTH_300',         '008',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '500',        '500',                 '500',                'DOMAIN_DATA_LENGTH_500',         '009',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '1000',       '1000',                '1000',               'DOMAIN_DATA_LENGTH_1000',        '010',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '2000',       '2000',                '2000',               'DOMAIN_DATA_LENGTH_2000',        '011',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_LENGTH', '4000',       '4000',                '4000',               'DOMAIN_DATA_LENGTH_4000',        '012',  'Y', 'Y', '0', sysdate, '', '');

insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '0000000000', 'Domain Data Precision',  'Domain Data Precision', 'DOMAIN_DATA_PRECISION_0000000000',  '000',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '3',          '3',                      '3',                     'DOMAIN_DATA_PRECISION_3',           '001',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '5',          '5',                      '5',                     'DOMAIN_DATA_PRECISION_5',           '002',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '9',          '9',                      '9',                     'DOMAIN_DATA_PRECISION_9',           '003',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '12',         '12',                     '12',                    'DOMAIN_DATA_PRECISION_12',          '004',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '15',         '15',                     '15',                    'DOMAIN_DATA_PRECISION_15',          '005',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_PRECISION', '24',         '24',                     '24',                    'DOMAIN_DATA_PRECISION_24',          '006',  'Y', 'Y', '0', sysdate, '', '');

insert into zebra_common_code values('DOMAIN_DATA_SCALE', '0000000000', 'Domain Data Scale',  'Domain Data Scale', 'DOMAIN_DATA_SCALE_0000000000',  '000',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_SCALE', '1',          '1',                  '1',                 'DOMAIN_DATA_SCALE_1',           '001',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_SCALE', '2',          '2',                  '2',                 'DOMAIN_DATA_SCALE_2',           '002',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_SCALE', '3',          '3',                  '3',                 'DOMAIN_DATA_SCALE_3',           '003',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_SCALE', '4',          '4',                  '4',                 'DOMAIN_DATA_SCALE_4',           '004',  'Y', 'Y', '0', sysdate, '', '');
insert into zebra_common_code values('DOMAIN_DATA_SCALE', '5',          '5',                  '5',                 'DOMAIN_DATA_SCALE_5',           '005',  'Y', 'Y', '0', sysdate, '', '');

insert into zebra_common_code values('SIMPLE_YN','0000000000',     '단순YN',  'Simple YN',    'SIMPLE_YN_0000000000',  '000',   'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('SIMPLE_YN','Y',              '예',      'Yes',          'SIMPLE_YN_Y',           '001',   'Y','Y','0',sysdate,'','');
insert into zebra_common_code values('SIMPLE_YN','N',              '아니오',  'No',           'SIMPLE_YN_N',           '002',   'Y','Y','0',sysdate,'','');

/**
 * Table Name   : 게시판
 * Description  :
 */
delete zebra_board;

--insert into zebra_board values('0','NOTICE','0','sawh','4444','sawhsong@gmail.com','127.0.0.1','For testing','For testing',0,'-1','0',sysdate,'','');
--insert into zebra_board values('1','NOTICE','0','sawh','4444','sawhsong@gmail.com','127.0.0.1','For testing','For testing',0,'-1','0',sysdate,'','');
/*
declare
begin
    for i in 12..1000 loop
        insert into zebra_board values(i, 'NOTICE', '0', 'sawh', '4444', 'sawhsong@gmail.com', '127.0.0.1', 'For testing'||i, 'For testing'||i, 0, '-1', '0', sysdate, '', '');
        commit;
    end loop;
end;
*/