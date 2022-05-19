/**
 * Table Name  : USR_INVOICE_D
 * Description : Invoice detail info
 */
drop table usr_invoice_d cascade constraints;
purge recyclebin;

create table usr_invoice_d (
    invoice_d_id                    varchar2(30)                                 not null,      -- Invoice detail UID (PK)
    invoice_id                      varchar2(30)                                 not null,      -- Invoice UID
    row_index                       number                                       not null,      -- Detail row index - just for sort order
    unit                            number,                                                     -- Unit - how many | how much
    amt_per_unit                    number,                                                     -- Price per unit
    item_amt                        number,                                                     -- Amount (exclude gst)
    description                     varchar2(1000),                                             -- Description
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint fk_29690386282100 foreign key(invoice_id) references usr_invoice(invoice_id),
    constraint pk_usr_invoice_d primary key(invoice_d_id)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_invoice_d                                                  is 'Invoice detail info';
comment on column usr_invoice_d.invoice_d_id                                     is 'Invoice detail UID (PK)';
comment on column usr_invoice_d.invoice_id                                       is 'Invoice UID';
comment on column usr_invoice_d.row_index                                        is 'Detail row index - just for sort order';
comment on column usr_invoice_d.unit                                             is 'Unit - how many | how much';
comment on column usr_invoice_d.amt_per_unit                                     is 'Price per unit';
comment on column usr_invoice_d.item_amt                                         is 'Amount (exclude gst)';
comment on column usr_invoice_d.description                                      is 'Description';
comment on column usr_invoice_d.insert_user_id                                   is 'Insert User UID';
comment on column usr_invoice_d.insert_date                                      is 'Insert Date';
comment on column usr_invoice_d.update_user_id                                   is 'Update User UID';
comment on column usr_invoice_d.update_date                                      is 'Update Date';


/**
 * Table Name  : USR_INVOICE_D
 * Data        : 
 */
