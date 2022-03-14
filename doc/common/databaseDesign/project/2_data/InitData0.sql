delete sys_org where org_id != '0';
delete sys_user where user_id not in ('0', '1');

delete usr_cc_alloc;
delete usr_cash_expense;
delete usr_cc_statement_d;
delete usr_cc_statement;

delete usr_bs_tran_alloc;
delete usr_bank_statement_d;
delete usr_bank_statement;

delete usr_bank_accnt;

delete usr_invoice_d;
delete usr_invoice;
delete usr_quotation_d;
delete usr_quotation;

delete usr_yearly_payroll_summary;