with dt as (
select src.root,
       src.parent_category_id,
       src.category_id,
       src.category_name,
       src.account_code,
       src.sort_order,
       sum(nvl(src.proc_amt_sep, 0)) as proc_amt_sep,
       sum(nvl(src.proc_amt_dec, 0)) as proc_amt_dec,
       sum(nvl(src.proc_amt_mar, 0)) as proc_amt_mar,
       sum(nvl(src.proc_amt_jun, 0)) as proc_amt_jun,
       max(nvl(this_year.proc_amt, 0)) as this_year_proc_amt,
       max(nvl(last_year.proc_amt, 0)) as last_year_proc_amt
  from (select dta.root,
               dta.category_level,
               dta.parent_category_id,
               dta.category_id,
               dta.category_name,
               dta.account_code,
               dta.sort_order,
               decode(dta.proc_month, 'Sep', sum(nvl(dta.proc_amt, 0))) as proc_amt_sep,
               decode(dta.proc_month, 'Sep', sum(nvl(dta.gst_amt, 0))) as gst_amt_sep,
               decode(dta.proc_month, 'Sep', sum(nvl(dta.net_amt, 0))) as net_amt_sep,
               decode(dta.proc_month, 'Dec', sum(nvl(dta.proc_amt, 0))) as proc_amt_dec,
               decode(dta.proc_month, 'Dec', sum(nvl(dta.gst_amt, 0))) as gst_amt_dec,
               decode(dta.proc_month, 'Dec', sum(nvl(dta.net_amt, 0))) as net_amt_dec,
               decode(dta.proc_month, 'Mar', sum(nvl(dta.proc_amt, 0))) as proc_amt_mar,
               decode(dta.proc_month, 'Mar', sum(nvl(dta.gst_amt, 0))) as gst_amt_mar,
               decode(dta.proc_month, 'Mar', sum(nvl(dta.net_amt, 0))) as net_amt_mar,
               decode(dta.proc_month, 'Jun', sum(nvl(dta.proc_amt, 0))) as proc_amt_jun,
               decode(dta.proc_month, 'Jun', sum(nvl(dta.gst_amt, 0))) as gst_amt_jun,
               decode(dta.proc_month, 'Jun', sum(nvl(dta.net_amt, 0))) as net_amt_jun
            from (select cate.root as root,
                         cate.category_level,
                         cate.parent_category_id,
                         cate.category_id,
                         cate.category_name,
                         cate.sort_order,
                         cate.account_code,
                         bsta.proc_month,
                         sum(nvl(bsta.proc_amt, 0)) as proc_amt,
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
                                 sum(nvl(ubta.proc_amt, 0)) as proc_amt,
                                 sum(nvl(ubta.gst_amt, 0)) as gst_amt,
                                 sum(nvl(ubta.net_amt, 0)) as net_amt
                            from usr_bs_tran_alloc ubta,
                                 (select period_year,
                                         financial_year,
                                         min(date_from) as date_from,
                                         max(date_to) as date_to
                                    from sys_financial_period
                                   where period_year = '2021'
                                   group by period_year, financial_year
                                 ) fy
                           where user_id in (select user_id from sys_user where org_id = '0')
                             and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)
                           group by ubta.main_category,
                                 ubta.sub_category,
                                 trunc(ubta.proc_date)
                         ) bsta
                   where cate.category_id = bsta.category_id(+)
                   group by cate.root,
                         cate.category_level,
                         cate.parent_category_id,
                         cate.category_id,
                         cate.category_name,
                         cate.sort_order,
                         cate.account_code,
                         bsta.proc_month
                 ) dta
            group by dta.root,
                  dta.category_level,
                  dta.parent_category_id,
                  dta.category_id,
                  dta.category_name,
                  dta.account_code,
                  dta.sort_order,
                  dta.proc_month
       ) src,
       (select a.sub_category as category_id,
               sum(nvl(a.proc_amt, 0)) as proc_amt,
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
               sum(nvl(a.proc_amt, 0)) as proc_amt,
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
 group by src.root,
       src.parent_category_id,
       src.category_id,
       src.category_name,
       src.account_code,
       src.sort_order
)
select (grouping(parent_category_id)+grouping(category_id)+grouping(category_name)+grouping(account_code)+grouping(sort_order)) as div,
       parent_category_id,
       category_id,
       category_name,
       account_code,
       sort_order,
       round(sum(nvl(proc_amt_sep, 0)), 2) as proc_amt_sep,
       round(sum(nvl(proc_amt_dec, 0)), 2) as proc_amt_dec,
       round(sum(nvl(proc_amt_mar, 0)), 2) as proc_amt_mar,
       round(sum(nvl(proc_amt_jun, 0)), 2) as proc_amt_jun,
       round(sum(nvl(this_year_proc_amt, 0)), 2) as this_year_proc_amt,
       round(sum(nvl(last_year_proc_amt, 0)), 2) as last_year_proc_amt
  from dt
 where root in ('19151552932200', '0200')
   and (0 <> (proc_amt_sep+proc_amt_dec+proc_amt_mar+proc_amt_jun) or account_code is null)
 group by rollup(parent_category_id, category_id, category_name, account_code, sort_order)
having (grouping(parent_category_id)+grouping(category_id)+grouping(category_name)+grouping(account_code)+grouping(sort_order)) in ('0', '5')
 order by sort_order, category_name
;