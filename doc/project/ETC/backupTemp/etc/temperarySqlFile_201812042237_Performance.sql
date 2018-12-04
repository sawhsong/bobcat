-- Expense
select *
  from ( select *
           from (select expense_type_id,
                        expense_type,
                        parent_expense_type,
                        description,
                        sort_order
                   from sys_expense_type
                  where org_category = 'C'
                )
        connect by prior expense_type = parent_expense_type
          start with parent_expense_type is null
          order siblings by sort_order
       ) typ,
       (select expense_type_id,
               to_char(expense_date, 'MON') as expense_month,
               nvl(sum(net_amt), 0) as net_amt
          from usr_expense
         where expense_year = '2017'
           and org_id = '477'
         group by expense_type_id, to_char(expense_date, 'MON')
       )
;




-- Income - A Type
select '00' as display_order,
       'Sales Income' as type_name,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'JUL') as jul,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'AUG') as aug,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'SEP') as sep,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'OCT') as oct,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'NOV') as nov,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'DEC') as dec,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'JAN') as jan,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'FEB') as feb,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'MAR') as mar,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'APR') as apr,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'MAY') as may,
       (select nvl(sum(net_amt), 0) as net_amt from usr_income where income_year = '2017' and org_id = '492' and income_type_id is null and to_char(income_date, 'MON') = 'JUN') as jun
  from dual
union
select lpad(typ.income_type_id, 2, '0') as display_order,
       typ.description as type_name,
       decode(uim.income_month, 'JUL', nvl(net_amt, 0), 0) as jul,
       decode(uim.income_month, 'AUG', nvl(net_amt, 0), 0) as aug,
       decode(uim.income_month, 'SEP', nvl(net_amt, 0), 0) as sep,
       decode(uim.income_month, 'OCT', nvl(net_amt, 0), 0) as oct,
       decode(uim.income_month, 'NOV', nvl(net_amt, 0), 0) as nov,
       decode(uim.income_month, 'DEC', nvl(net_amt, 0), 0) as dec,
       decode(uim.income_month, 'JAN', nvl(net_amt, 0), 0) as jan,
       decode(uim.income_month, 'FEB', nvl(net_amt, 0), 0) as feb,
       decode(uim.income_month, 'MAR', nvl(net_amt, 0), 0) as mar,
       decode(uim.income_month, 'APR', nvl(net_amt, 0), 0) as apr,
       decode(uim.income_month, 'MAY', nvl(net_amt, 0), 0) as may,
       decode(uim.income_month, 'JUN', nvl(net_amt, 0), 0) as jun
  from (select income_type_id,
               description
          from sys_income_type
         where org_category = 'A'
       ) typ,
       (select nvl(income_type_id, '0') as income_type_id,
               to_char(income_date, 'MON') as income_month,
               nvl(sum(net_amt), 0) as net_amt
          from usr_income
         where income_year = '2017'
           and org_id = '492'
         group by income_type_id, to_char(income_date, 'MON')
       ) uim
 where typ.income_type_id = uim.income_type_id(+)
 order by display_order
;

-- Income - B, C Type
select lpad(typ.income_type_id, 2, '0') as display_order,
       typ.description as type_name,
       decode(uim.income_month, 'JUL', nvl(net_amt, 0), 0) as jul,
       decode(uim.income_month, 'AUG', nvl(net_amt, 0), 0) as aug,
       decode(uim.income_month, 'SEP', nvl(net_amt, 0), 0) as sep,
       decode(uim.income_month, 'OCT', nvl(net_amt, 0), 0) as oct,
       decode(uim.income_month, 'NOV', nvl(net_amt, 0), 0) as nov,
       decode(uim.income_month, 'DEC', nvl(net_amt, 0), 0) as dec,
       decode(uim.income_month, 'JAN', nvl(net_amt, 0), 0) as jan,
       decode(uim.income_month, 'FEB', nvl(net_amt, 0), 0) as feb,
       decode(uim.income_month, 'MAR', nvl(net_amt, 0), 0) as mar,
       decode(uim.income_month, 'APR', nvl(net_amt, 0), 0) as apr,
       decode(uim.income_month, 'MAY', nvl(net_amt, 0), 0) as may,
       decode(uim.income_month, 'JUN', nvl(net_amt, 0), 0) as jun
  from (select income_type_id,
               description
          from sys_income_type
         where org_category = 'A'
       ) typ,
       (select nvl(income_type_id, '0') as income_type_id,
               to_char(income_date, 'MON') as income_month,
               nvl(sum(net_amt), 0) as net_amt
          from usr_income
         where income_year = '2017'
           and org_id = '492'
         group by income_type_id, to_char(income_date, 'MON')
       ) uim
 where typ.income_type_id = uim.income_type_id(+)
 order by display_order
;