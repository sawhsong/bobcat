with src as (
    select dta.category_level,
           dta.category_id,
           dta.category_name,
           dta.account_code,
           dta.sort_order,
           decode(dta.proc_month, 'Sep', sum(nvl(dta.debit_amt, 0))) as debit_amt_sep,
           decode(dta.proc_month, 'Sep', sum(nvl(dta.credit_amt, 0))) as credit_amt_sep,
           decode(dta.proc_month, 'Sep', sum(nvl(dta.gst_amt, 0))) as gst_amt_sep,
           decode(dta.proc_month, 'Sep', sum(nvl(dta.net_amt, 0))) as net_amt_sep,
           decode(dta.proc_month, 'Dec', sum(nvl(dta.debit_amt, 0))) as debit_amt_dec,
           decode(dta.proc_month, 'Dec', sum(nvl(dta.credit_amt, 0))) as credit_amt_dec,
           decode(dta.proc_month, 'Dec', sum(nvl(dta.gst_amt, 0))) as gst_amt_dec,
           decode(dta.proc_month, 'Dec', sum(nvl(dta.net_amt, 0))) as net_amt_dec,
           decode(dta.proc_month, 'Mar', sum(nvl(dta.debit_amt, 0))) as debit_amt_mar,
           decode(dta.proc_month, 'Mar', sum(nvl(dta.credit_amt, 0))) as credit_amt_mar,
           decode(dta.proc_month, 'Mar', sum(nvl(dta.gst_amt, 0))) as gst_amt_mar,
           decode(dta.proc_month, 'Mar', sum(nvl(dta.net_amt, 0))) as net_amt_mar,
           decode(dta.proc_month, 'Jun', sum(nvl(dta.debit_amt, 0))) as debit_amt_jun,
           decode(dta.proc_month, 'Jun', sum(nvl(dta.credit_amt, 0))) as credit_amt_jun,
           decode(dta.proc_month, 'Jun', sum(nvl(dta.gst_amt, 0))) as gst_amt_jun,
           decode(dta.proc_month, 'Jun', sum(nvl(dta.net_amt, 0))) as net_amt_jun
      from (select cate.category_level,
                   cate.category_id,
                   cate.category_name,
                   cate.sort_order,
                   cate.account_code,
                   bsta.proc_month,
                   sum(nvl(bsta.debit_amt, 0)) as debit_amt,
                   sum(nvl(bsta.credit_amt, 0)) as credit_amt,
                   sum(nvl(bsta.gst_amt, 0)) as gst_amt,
                   sum(nvl(bsta.net_amt, 0)) as net_amt
              from ( select connect_by_root category_id as root,
                            level as category_level,
                            connect_by_isleaf as is_leaf,
                            substr(sys_connect_by_path(category_id, '-'), 2) as path,
                            sys_recon_category.*
                       from sys_recon_category
                    connect by prior category_id = parent_category_id
                      start with parent_category_id is null
                      order siblings by sort_order
                   ) cate,
                   (select ubta.main_category,
                           ubta.sub_category as category_id,
                           trunc(ubta.proc_date) as proc_date,
                           (select initcap(quarter_name) from sys_financial_period where period_year = '2021' and trunc(ubta.proc_date) between trunc(date_from) and trunc(date_to)) as proc_month,
                           case when sum(nvl(ubta.proc_amt, 0)) < 0 then sum(nvl(ubta.proc_amt, 0)) end as debit_amt,
                           case when sum(nvl(ubta.proc_amt, 0)) >= 0 then sum(nvl(ubta.proc_amt, 0)) end as credit_amt,
                           sum(nvl(ubta.gst_amt, 0)) as gst_amt,
                           sum(nvl(ubta.net_amt, 0)) as net_amt,
                           sum(nvl(ubta.balance, 0)) as balance
                      from usr_bs_tran_alloc ubta,
                           (select period_year,
                                   financial_year,
                                   min(date_from) as date_from,
                                   max(date_to) as date_to
                              from sys_financial_period
                             where period_year = '2021'
                             group by period_year, financial_year
                           ) fy
                     where user_id = '1'
                       and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)
                     group by ubta.main_category,
                           ubta.sub_category,
                           trunc(ubta.proc_date)
                   ) bsta
             where cate.category_id = bsta.category_id(+)
             group by cate.category_level,
                   cate.category_id,
                   cate.category_name,
                   cate.sort_order,
                   cate.account_code,
                   bsta.proc_month
           ) dta
     group by dta.category_level,
           dta.category_id,
           dta.category_name,
           dta.account_code,
           dta.sort_order,
           dta.proc_month
     order by dta.sort_order
)
select src.category_level,
       src.category_id,
       src.category_name,
       src.account_code,
       src.sort_order,
       sum(nvl(src.debit_amt_sep, 0)) as debit_amt_sep,
       sum(nvl(src.credit_amt_sep, 0)) as credit_amt_sep,
       sum(nvl(src.gst_amt_sep, 0)) as gst_amt_sep,
       sum(nvl(src.net_amt_sep, 0)) as net_amt_sep,
       sum(nvl(src.debit_amt_dec, 0)) as debit_amt_dec,
       sum(nvl(src.credit_amt_dec, 0)) as credit_amt_dec,
       sum(nvl(src.gst_amt_dec, 0)) as gst_amt_dec,
       sum(nvl(src.net_amt_dec, 0)) as net_amt_dec,
       sum(nvl(src.debit_amt_mar, 0)) as debit_amt_mar,
       sum(nvl(src.credit_amt_mar, 0)) as credit_amt_mar,
       sum(nvl(src.gst_amt_mar, 0)) as gst_amt_mar,
       sum(nvl(src.net_amt_mar, 0)) as net_amt_mar,
       sum(nvl(src.debit_amt_jun, 0)) as debit_amt_jun,
       sum(nvl(src.credit_amt_jun, 0)) as credit_amt_jun,
       sum(nvl(src.gst_amt_jun, 0)) as gst_amt_jun,
       sum(nvl(src.net_amt_jun, 0)) as net_amt_jun,
       max(nvl(this_year.debit_amt, 0)) as this_year_debit_amt,
       max(nvl(this_year.credit_amt, 0)) as this_year_credit_amt,
       max(nvl(this_year.gst_amt, 0)) as this_year_gst_amt,
       max(nvl(this_year.net_amt, 0)) as this_year_net_amt,
       max(nvl(last_year.debit_amt, 0)) as last_year_debit_amt,
       max(nvl(last_year.credit_amt, 0)) as last_year_credit_amt,
       max(nvl(last_year.gst_amt, 0)) as last_year_gst_amt,
       max(nvl(last_year.net_amt, 0)) as last_year_net_amt
  from src,
       (select a.sub_category as category_id,
               case when sum(nvl(a.proc_amt, 0)) < 0 then sum(nvl(a.proc_amt, 0)) end as debit_amt,
               case when sum(nvl(a.proc_amt, 0)) >= 0 then sum(nvl(a.proc_amt, 0)) end as credit_amt,
               sum(nvl(a.gst_amt, 0)) as gst_amt,
               sum(nvl(a.net_amt, 0)) as net_amt
          from usr_bs_tran_alloc a,
               (select period_year,
                       financial_year,
                       min(date_from) as date_from,
                       max(date_to) as date_to
                  from sys_financial_period
                 where period_year = '2021'
                 group by period_year, financial_year
               ) b
         where user_id = '1'
           and trunc(a.proc_date) between trunc(b.date_from) and trunc(b.date_to)
         group by a.sub_category
       ) this_year,
       (select a.sub_category as category_id,
               case when sum(nvl(a.proc_amt, 0)) < 0 then sum(nvl(a.proc_amt, 0)) end as debit_amt,
               case when sum(nvl(a.proc_amt, 0)) >= 0 then sum(nvl(a.proc_amt, 0)) end as credit_amt,
               sum(nvl(a.gst_amt, 0)) as gst_amt,
               sum(nvl(a.net_amt, 0)) as net_amt
          from usr_bs_tran_alloc a,
               (select period_year,
                       financial_year,
                       min(date_from) as date_from,
                       max(date_to) as date_to
                  from sys_financial_period
                 where period_year = '2020'
                 group by period_year, financial_year
               ) b
         where user_id = '1'
           and trunc(a.proc_date) between trunc(b.date_from) and trunc(b.date_to)
         group by a.sub_category
       ) last_year
 where src.category_id = this_year.category_id(+)
   and src.category_id = last_year.category_id(+)
 group by src.category_level,
          src.category_id,
          src.category_name,
          src.account_code,
          src.sort_order
 order by src.sort_order
;

select a.sub_category as category_id,
       case when sum(nvl(a.proc_amt, 0)) < 0 then sum(nvl(a.proc_amt, 0)) end as debit_amt,
       case when sum(nvl(a.proc_amt, 0)) >= 0 then sum(nvl(a.proc_amt, 0)) end as credit_amt,
       sum(nvl(a.gst_amt, 0)) as gst_amt,
       sum(nvl(a.net_amt, 0)) as net_amt,
       sum(nvl(a.balance, 0)) as balance
  from usr_bs_tran_alloc a,
       (select period_year,
               financial_year,
               min(date_from) as date_from,
               max(date_to) as date_to
          from sys_financial_period
         where period_year = '2021'
         group by period_year, financial_year
       ) b
 where user_id = '1'
   and trunc(a.proc_date) between trunc(b.date_from) and trunc(b.date_to)
 group by a.sub_category
;

select *
  from usr_bs_tran_alloc
;