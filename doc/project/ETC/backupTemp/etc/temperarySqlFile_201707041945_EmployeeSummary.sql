-- Employee - All Type
select code.common_code,
       code.description_en,
       nvl(emp.emp_entry_count, 0) as emp_entry_count,
       nvl(emp_wage_entry_count, 0) as emp_wage_entry_count,
       nvl(tot_gross_wage, 0) as tot_gross_wage,
       nvl(tot_tax, 0) as tot_tax,
       nvl(tot_super_amt, 0) as tot_super_amt,
       nvl(tot_net_wage, 0) as tot_net_wage
  from sys_common_code code,
       (select emp.visa_type,
               nvl(count(*), 0) as emp_entry_count,
               nvl(sum(emp_wage_entry_count), 0) as emp_wage_entry_count,
               nvl(sum(tot_gross_wage), 0) as tot_gross_wage,
               nvl(sum(tot_tax), 0) as tot_tax,
               nvl(sum(tot_super_amt), 0) as tot_super_amt,
               nvl(sum(tot_net_wage), 0) as tot_net_wage
          from usr_employee emp,
               (select employee_id,
                       count(*) as emp_wage_entry_count,
                       nvl(sum(gross_wage), 0) as tot_gross_wage,
                       nvl(sum(tax), 0) as tot_tax,
                       nvl(sum(super_amt), 0) as tot_super_amt,
                       nvl(sum(net_wage), 0) as tot_net_wage
                  from usr_employee_wage
                 where org_id = '439'
                   and wage_year = '2014'
                   and quarter_name = 'SEP'
                 group by employee_id
               ) ewg
         where emp.org_id = '439'
           and emp.employee_id = ewg.employee_id
         group by emp.visa_type
       ) emp
 where code.code_type = 'VISA_TYPE'
   and code.common_code <> '0000000000'
   and code.common_code = emp.visa_type(+)
 order by code.sort_order
;