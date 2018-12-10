select DISPLAY_ORDER,
       TYPE_NAME,
       nvl(sum(jul), 0) as JUL_AMT,
       nvl(sum(aug), 0) as AUG_AMT,
       nvl(sum(sep), 0) as SEP_AMT,
       nvl(sum(oct), 0) as OCT_AMT,
       nvl(sum(nov), 0) as NOV_AMT,
       nvl(sum(dec), 0) as DEC_AMT,
       nvl(sum(jan), 0) as JAN_AMT,
       nvl(sum(feb), 0) as FEB_AMT,
       nvl(sum(mar), 0) as MAR_AMT,
       nvl(sum(apr), 0) as APR_AMT,
       nvl(sum(may), 0) as MAY_AMT,
       nvl(sum(jun), 0) as JUN_AMT
  from (select lpad(typ.income_type_id, 2, '0') as display_order,
               typ.description as type_name,
               decode(uim.income_month, '07', nvl(net_amt, 0), 0) as jul,
               decode(uim.income_month, '08', nvl(net_amt, 0), 0) as aug,
               decode(uim.income_month, '09', nvl(net_amt, 0), 0) as sep,
               decode(uim.income_month, '10', nvl(net_amt, 0), 0) as oct,
               decode(uim.income_month, '11', nvl(net_amt, 0), 0) as nov,
               decode(uim.income_month, '12', nvl(net_amt, 0), 0) as dec,
               decode(uim.income_month, '01', nvl(net_amt, 0), 0) as jan,
               decode(uim.income_month, '02', nvl(net_amt, 0), 0) as feb,
               decode(uim.income_month, '03', nvl(net_amt, 0), 0) as mar,
               decode(uim.income_month, '04', nvl(net_amt, 0), 0) as apr,
               decode(uim.income_month, '05', nvl(net_amt, 0), 0) as may,
               decode(uim.income_month, '06', nvl(net_amt, 0), 0) as jun
          from (select income_type_id,
                       description
                  from sys_income_type
                 where org_category = 'C'
               ) typ,
               (select nvl(income_type_id, '0') as income_type_id,
                       to_char(income_date, 'MM') as income_month,
                       nvl(sum(gross_amt), 0) as net_amt
                  from usr_income
                 where income_year = '2017'
                   and org_id = '477'
                 group by income_type_id, to_char(income_date, 'MM')
               ) uim
         where typ.income_type_id = uim.income_type_id(+)
       )
 group by display_order,
       type_name
 order by display_order
;




-- Expense
with src_data as (
select sort_order as display_order,
       description as type_name,
       decode(expense_month, 'JUL', nvl(net_amt, 0), 0) as jul,
       decode(expense_month, 'AUG', nvl(net_amt, 0), 0) as aug,
       decode(expense_month, 'SEP', nvl(net_amt, 0), 0) as sep,
       decode(expense_month, 'OCT', nvl(net_amt, 0), 0) as oct,
       decode(expense_month, 'NOV', nvl(net_amt, 0), 0) as nov,
       decode(expense_month, 'DEC', nvl(net_amt, 0), 0) as dec,
       decode(expense_month, 'JAN', nvl(net_amt, 0), 0) as jan,
       decode(expense_month, 'FEB', nvl(net_amt, 0), 0) as feb,
       decode(expense_month, 'MAR', nvl(net_amt, 0), 0) as mar,
       decode(expense_month, 'APR', nvl(net_amt, 0), 0) as apr,
       decode(expense_month, 'MAY', nvl(net_amt, 0), 0) as may,
       decode(expense_month, 'JUN', nvl(net_amt, 0), 0) as jun
  from (select expense_type,
               description,
               sort_order,
               expense_month,
               nvl(sum(net_amt), 0) as net_amt
          from (select typ.expense_type,
                       typ.description,
                       typ.sort_order,
                       exp.expense_month,
                       nvl(exp.net_amt, 0) as net_amt
                  from ( select a.expense_type_id,
                                nvl(a.parent_expense_type, a.expense_type) as expense_type,
                                (select description from sys_expense_type where org_category = 'C' and expense_type = a.parent_expense_type) as description,
                                a.sort_order
                           from (select expense_type_id,
                                        expense_type,
                                        parent_expense_type,
                                        description,
                                        sort_order
                                   from sys_expense_type
                                  where org_category = 'C'
                                ) a
                        connect by prior a.expense_type = a.parent_expense_type
                          start with a.parent_expense_type is null
                          order siblings by a.sort_order
                       ) typ,
                       (select expense_type_id,
                               to_char(expense_date, 'MON') as expense_month,
                               nvl(sum(net_amt), 0) as net_amt
                          from usr_expense
                         where expense_year = '2017'
                           and org_id = '477'
                         group by expense_type_id, to_char(expense_date, 'MON')
                       ) exp
                 where typ.expense_type_id = exp.expense_type_id(+)
                 order by typ.sort_order
               ) src
         where expense_month is not null
         group by expense_type,
               description,
               sort_order,
               expense_month
         order by sort_order,
               expense_month
        )
)
select substr(display_order, 3, 2) as display_order,
       type_name,
       nvl(sum(jul), 0) as jul,
       nvl(sum(aug), 0) as aug,
       nvl(sum(sep), 0) as sep,
       nvl(sum(oct), 0) as oct,
       nvl(sum(nov), 0) as nov,
       nvl(sum(dec), 0) as dec,
       nvl(sum(jan), 0) as jan,
       nvl(sum(feb), 0) as feb,
       nvl(sum(mar), 0) as mar,
       nvl(sum(apr), 0) as apr,
       nvl(sum(may), 0) as may,
       nvl(sum(jun), 0) as jun
  from src_data
 group by substr(display_order, 3, 2),
       type_name
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
select display_order,
       type_name,
       nvl(sum(jul), 0) as jul,
       nvl(sum(aug), 0) as aug,
       nvl(sum(sep), 0) as sep,
       nvl(sum(oct), 0) as oct,
       nvl(sum(nov), 0) as nov,
       nvl(sum(dec), 0) as dec,
       nvl(sum(jan), 0) as jan,
       nvl(sum(feb), 0) as feb,
       nvl(sum(mar), 0) as mar,
       nvl(sum(apr), 0) as apr,
       nvl(sum(may), 0) as may,
       nvl(sum(jun), 0) as jun
  from (select lpad(typ.income_type_id, 2, '0') as display_order,
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
                 where org_category = 'C'
               ) typ,
               (select nvl(income_type_id, '0') as income_type_id,
                       to_char(income_date, 'MON') as income_month,
                       nvl(sum(gross_amt), 0) as net_amt
                  from usr_income
                 where income_year = '2017'
                   and org_id = '477'
                 group by income_type_id, to_char(income_date, 'MON')
               ) uim
         where typ.income_type_id = uim.income_type_id(+)
       )
 order by display_order
;