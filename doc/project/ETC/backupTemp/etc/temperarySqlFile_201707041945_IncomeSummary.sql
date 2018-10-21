-- Income - Type A
select code.common_code,
       code.description_en,
       nvl(income.income_entry_count, 0) as income_entry_count,
       code.sort_order
  from sys_common_code code,
       (select record_keeping_type,
               count(*) as income_entry_count
          from usr_income
         where org_id = '439'
           and income_year = '2017'
           and quarter_name = 'SEP'
           and income_entry_type = 'SALES'
         group by record_keeping_type
       ) income
 where code.code_type = 'RECORD_KEEPING_TYPE'
   and code.common_code <> '0000000000'
   and code.common_code = income.record_keeping_type(+)
union
select code.common_code,
       code.description_en,
       nvl(income.income_entry_count, 0) as income_entry_count,
       '999' as sort_order
  from sys_common_code code,
       (select record_keeping_type,
               count(*) as income_entry_count
          from usr_income
         where org_id = '434'
           and income_year = '2017'
           and quarter_name = 'SEP'
           and income_entry_type = 'OTHTA'
         group by record_keeping_type
       ) income
 where code.code_type = 'INCOME_ENTRY_TYPE'
   and code.common_code = 'OTHTA'
   and code.common_code = income.record_keeping_type(+)
 order by sort_order
;

-- Income - Type B
select type.income_type_id,
       type.income_type,
       type.description,
       nvl(income.income_entry_count, 0) as income_entry_count
  from sys_income_type type,
       (select income_type_id,
               count(*) as income_entry_count
          from usr_income
         where org_id = '443'
           and income_year = '2017'
           and quarter_name = 'SEP'
         group by income_type_id
       ) income
 where type.org_category = 'B'
   and type.income_type_id = income.income_type_id(+)
 order by type.sort_order
;

-- Income - Type C
select type.income_type_id,
       type.income_type,
       type.description,
       nvl(income.income_entry_count, 0) as income_entry_count
  from sys_income_type type,
       (select income_type_id,
               count(*) as income_entry_count
          from usr_income
         where org_id = '475'
           and income_year = '2017'
           and quarter_name = 'SEP'
         group by income_type_id
       ) income
 where type.org_category = 'C'
   and type.income_type_id = income.income_type_id(+)
 order by type.sort_order
;