-- Project Name : �v�����f���Ǘ��A�v��
-- Date/Time    : 2023/04/12 18:36:03
-- Author       : kk-ma
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- �u�����h�ꗗ
drop table if exists mst0000_brand cascade;

create table mst0000_brand (
  brandid character(2) not null
  , brandnm character(15) not null
  , compid character(3) not null
  , producttypeid character(3) not null
  , constraint mst0000_brand_PKC primary key (brandid)
) ;

-- ��ƈꗗ
drop table if exists mst0001_company cascade;

create table mst0001_company (
  compid character(3) not null
  , compnm character(15) not null
  , constraint mst0001_company_PKC primary key (compid)
) ;

-- ���i��ʈꗗ
drop table if exists mst0002_producttype cascade;

create table mst0002_producttype (
  producttypeid character(3) not null
  , producttypenm character(15) not null
  , abbr character(10) not null
  , constraint mst0002_producttype_PKC primary key (producttypeid)
) ;

-- �h���ꗗ
drop table if exists tbl0000_paint cascade;

create table tbl0000_paint (
  brandid character(2) not null
  , colorcode character(6) not null
  , colornm character(20) not null
  , posession character(1) not null
  , selvisible character(1) not null
  , appaintid character(8) not null
  , constraint tbl0000_paint_PKC primary key (brandid,colorcode)
) ;

-- �����h��
drop table if exists tbl0001_mixpaint cascade;

create table tbl0001_mixpaint (
  mixpaintid character(8) not null
  , colornm character(20) not null
  , constraint tbl0001_mixpaint_PKC primary key (mixpaintid)
) ;

-- �����h���ڍ�
drop table if exists tbl0002_mixpaintdetail cascade;

create table tbl0002_mixpaintdetail (
  mixpaintid character(8) not null
  , orgpaintid character(8) not null
  , mixrate real not null
  , constraint tbl0002_mixpaintdetail_PKC primary key (mixpaintid,orgpaintid)
) ;

-- �g�p�h��
drop table if exists tbl0003_usepaint cascade;

create table tbl0003_usepaint (
  plmdlid character(4) not null
  , paintid character(8) not null
  , memo character(100) not null
  , constraint tbl0003_usepaint_PKC primary key (plmdlid,paintid)
) ;

-- ��Ɖӏ��ꗗ
drop table if exists tbl0100_worklist cascade;

create table tbl0100_worklist (
  workid character(4) not null
  , plmdlid character(4) not null
  , workplace character(30) not null
  , memo character(100) not null
  , updatedate character(19) not null
  , constraint tbl0100_worklist_PKC primary key (workid)
) ;

-- ��Ɖӏ��ڍ�
drop table if exists tbl0101_workdetail cascade;

create table tbl0101_workdetail (
  workid character(4) not null
  , workdetailid character(8) not null
  , workdetail character(50) not null
  , priority real not null
  , progressrate integer not null
  , updatedate character(19) not null
  , constraint tbl0101_workdetail_PKC primary key (workid,workdetailid)
) ;

-- �v�����f���ꗗ
drop table if exists tbl0200_plmdl cascade;

create table tbl0200_plmdl (
  plmdlid character(4) not null
  , plmdlnm character(30) not null
  , brandid character(2) not null
  , soldym character(7) not null
  , posession character(1) not null
  , constraint tbl0200_plmdl_PKC primary key (plmdlid)
) ;

-- �h���r���[
drop view if exists paintView;

create view paintView as 
select
    brandid || colorcode as paintid,    -- �h��ID
    brandid,    -- �u�����hID
    colorcode,  -- �J���[�R�[�h
    colornm,  -- �J���[��
    posession,  -- ����
    selvisible, -- �I�����\��
    appaintid   -- �ߎ��h��ID
from
    tbl0000_paint
;

comment on table mst0000_brand is '�u�����h�ꗗ';
comment on column mst0000_brand.brandid is '�u�����hID';
comment on column mst0000_brand.brandnm is '�u�����h��';
comment on column mst0000_brand.compid is '���ID';
comment on column mst0000_brand.producttypeid is '���i���ID';

comment on table mst0001_company is '��ƈꗗ';
comment on column mst0001_company.compid is '���ID';
comment on column mst0001_company.compnm is '��Ɩ�';

comment on table mst0002_producttype is '���i��ʈꗗ';
comment on column mst0002_producttype.producttypeid is '���i���ID';
comment on column mst0002_producttype.producttypenm is '���i��ʖ�:�h��_���b�J�[�A�h��_�A�N�����A�h��_�G�i����';
comment on column mst0002_producttype.abbr is '����';

comment on view paintView is '�h���r���[:�J���[ID�i�u�����hID + �J���[�R�[�h�j����L�[�Ƃ����h���ꗗ';
comment on column paintView.paintid is '�h��ID';
comment on column paintView.brandid is '�u�����hID';
comment on column paintView.colorcode is '�J���[�R�[�h';
comment on column paintView.colornm is '�J���[��';
comment on column paintView.posession is '����';
comment on column paintView.selvisible is '�I�����\��';
comment on column paintView.appaintid is '�ߎ��h��ID';

comment on table tbl0000_paint is '�h���ꗗ';
comment on column tbl0000_paint.brandid is '�u�����hID';
comment on column tbl0000_paint.colorcode is '�J���[�R�[�h:�̔�����Ă��鏤�i���Ƃ��ẴJ���[�R�[�h';
comment on column tbl0000_paint.colornm is '�J���[��';
comment on column tbl0000_paint.posession is '����:0:��,1:�L';
comment on column tbl0000_paint.selvisible is '�I�����\��:0:��,1:�L';
comment on column tbl0000_paint.appaintid is '�ߎ��h��ID:�u�����hID+�J���[�R�[�h';

comment on table tbl0001_mixpaint is '�����h��';
comment on column tbl0001_mixpaint.mixpaintid is '�����h��ID:''MIX''&(�����̔�5�����l)';
comment on column tbl0001_mixpaint.colornm is '�J���[��';

comment on table tbl0002_mixpaintdetail is '�����h���ڍ�';
comment on column tbl0002_mixpaintdetail.mixpaintid is '�����h��ID';
comment on column tbl0002_mixpaintdetail.orgpaintid is '�z�����h��ID';
comment on column tbl0002_mixpaintdetail.mixrate is '������';

comment on table tbl0003_usepaint is '�g�p�h��';
comment on column tbl0003_usepaint.plmdlid is '�v�����f��ID';
comment on column tbl0003_usepaint.paintid is '�h��ID';
comment on column tbl0003_usepaint.memo is '����';

comment on table tbl0100_worklist is '��Ɖӏ��ꗗ';
comment on column tbl0100_worklist.workid is '���ID:4�������̔�';
comment on column tbl0100_worklist.plmdlid is '�v�����f��ID';
comment on column tbl0100_worklist.workplace is '��Ɖӏ�';
comment on column tbl0100_worklist.memo is '����';
comment on column tbl0100_worklist.updatedate is '�X�V����';

comment on table tbl0101_workdetail is '��Ɖӏ��ڍ�';
comment on column tbl0101_workdetail.workid is '���ID';
comment on column tbl0101_workdetail.workdetailid is '��Ɠ��eID';
comment on column tbl0101_workdetail.workdetail is '��Ɠ��e:��Ɠ��eID���琶�������e�L�X�g&�C�ӂ̃R�����g��ݒ�';
comment on column tbl0101_workdetail.priority is '�D��x';
comment on column tbl0101_workdetail.progressrate is '�i����';
comment on column tbl0101_workdetail.updatedate is '�X�V����';

comment on table tbl0200_plmdl is '�v�����f���ꗗ';
comment on column tbl0200_plmdl.plmdlid is '�v�����f��ID:4�������̔�';
comment on column tbl0200_plmdl.plmdlnm is '�v�����f����';
comment on column tbl0200_plmdl.brandid is '�u�����hID';
comment on column tbl0200_plmdl.soldym is '�����N��';
comment on column tbl0200_plmdl.posession is '����:0:��,1:�L';

