-- Project Name : プラモデル管理アプリ
-- Date/Time    : 2023/04/12 18:36:03
-- Author       : kk-ma
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- ブランド一覧
drop table if exists mst0000_brand cascade;

create table mst0000_brand (
  brandid character(2) not null
  , brandnm character(15) not null
  , compid character(3) not null
  , producttypeid character(3) not null
  , constraint mst0000_brand_PKC primary key (brandid)
) ;

-- 企業一覧
drop table if exists mst0001_company cascade;

create table mst0001_company (
  compid character(3) not null
  , compnm character(15) not null
  , constraint mst0001_company_PKC primary key (compid)
) ;

-- 商品種別一覧
drop table if exists mst0002_producttype cascade;

create table mst0002_producttype (
  producttypeid character(3) not null
  , producttypenm character(15) not null
  , abbr character(10) not null
  , constraint mst0002_producttype_PKC primary key (producttypeid)
) ;

-- 塗料一覧
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

-- 混合塗料
drop table if exists tbl0001_mixpaint cascade;

create table tbl0001_mixpaint (
  mixpaintid character(8) not null
  , colornm character(20) not null
  , constraint tbl0001_mixpaint_PKC primary key (mixpaintid)
) ;

-- 混合塗料詳細
drop table if exists tbl0002_mixpaintdetail cascade;

create table tbl0002_mixpaintdetail (
  mixpaintid character(8) not null
  , orgpaintid character(8) not null
  , mixrate real not null
  , constraint tbl0002_mixpaintdetail_PKC primary key (mixpaintid,orgpaintid)
) ;

-- 使用塗料
drop table if exists tbl0003_usepaint cascade;

create table tbl0003_usepaint (
  plmdlid character(4) not null
  , paintid character(8) not null
  , memo character(100) not null
  , constraint tbl0003_usepaint_PKC primary key (plmdlid,paintid)
) ;

-- 作業箇所一覧
drop table if exists tbl0100_worklist cascade;

create table tbl0100_worklist (
  workid character(4) not null
  , plmdlid character(4) not null
  , workplace character(30) not null
  , memo character(100) not null
  , updatedate character(19) not null
  , constraint tbl0100_worklist_PKC primary key (workid)
) ;

-- 作業箇所詳細
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

-- プラモデル一覧
drop table if exists tbl0200_plmdl cascade;

create table tbl0200_plmdl (
  plmdlid character(4) not null
  , plmdlnm character(30) not null
  , brandid character(2) not null
  , soldym character(7) not null
  , posession character(1) not null
  , constraint tbl0200_plmdl_PKC primary key (plmdlid)
) ;

-- 塗料ビュー
drop view if exists paintView;

create view paintView as 
select
    brandid || colorcode as paintid,    -- 塗料ID
    brandid,    -- ブランドID
    colorcode,  -- カラーコード
    colornm,  -- カラー名
    posession,  -- 所持
    selvisible, -- 選択肢表示
    appaintid   -- 近似塗料ID
from
    tbl0000_paint
;

comment on table mst0000_brand is 'ブランド一覧';
comment on column mst0000_brand.brandid is 'ブランドID';
comment on column mst0000_brand.brandnm is 'ブランド名';
comment on column mst0000_brand.compid is '企業ID';
comment on column mst0000_brand.producttypeid is '商品種別ID';

comment on table mst0001_company is '企業一覧';
comment on column mst0001_company.compid is '企業ID';
comment on column mst0001_company.compnm is '企業名';

comment on table mst0002_producttype is '商品種別一覧';
comment on column mst0002_producttype.producttypeid is '商品種別ID';
comment on column mst0002_producttype.producttypenm is '商品種別名:塗料_ラッカー、塗料_アクリル、塗料_エナメル';
comment on column mst0002_producttype.abbr is '略称';

comment on view paintView is '塗料ビュー:カラーID（ブランドID + カラーコード）を主キーとした塗料一覧';
comment on column paintView.paintid is '塗料ID';
comment on column paintView.brandid is 'ブランドID';
comment on column paintView.colorcode is 'カラーコード';
comment on column paintView.colornm is 'カラー名';
comment on column paintView.posession is '所持';
comment on column paintView.selvisible is '選択肢表示';
comment on column paintView.appaintid is '近似塗料ID';

comment on table tbl0000_paint is '塗料一覧';
comment on column tbl0000_paint.brandid is 'ブランドID';
comment on column tbl0000_paint.colorcode is 'カラーコード:販売されている商品名としてのカラーコード';
comment on column tbl0000_paint.colornm is 'カラー名';
comment on column tbl0000_paint.posession is '所持:0:無,1:有';
comment on column tbl0000_paint.selvisible is '選択肢表示:0:無,1:有';
comment on column tbl0000_paint.appaintid is '近似塗料ID:ブランドID+カラーコード';

comment on table tbl0001_mixpaint is '混合塗料';
comment on column tbl0001_mixpaint.mixpaintid is '混合塗料ID:''MIX''&(自動採番5桁数値)';
comment on column tbl0001_mixpaint.colornm is 'カラー名';

comment on table tbl0002_mixpaintdetail is '混合塗料詳細';
comment on column tbl0002_mixpaintdetail.mixpaintid is '混合塗料ID';
comment on column tbl0002_mixpaintdetail.orgpaintid is '配合元塗料ID';
comment on column tbl0002_mixpaintdetail.mixrate is '混合率';

comment on table tbl0003_usepaint is '使用塗料';
comment on column tbl0003_usepaint.plmdlid is 'プラモデルID';
comment on column tbl0003_usepaint.paintid is '塗料ID';
comment on column tbl0003_usepaint.memo is 'メモ';

comment on table tbl0100_worklist is '作業箇所一覧';
comment on column tbl0100_worklist.workid is '作業ID:4桁自動採番';
comment on column tbl0100_worklist.plmdlid is 'プラモデルID';
comment on column tbl0100_worklist.workplace is '作業箇所';
comment on column tbl0100_worklist.memo is 'メモ';
comment on column tbl0100_worklist.updatedate is '更新日時';

comment on table tbl0101_workdetail is '作業箇所詳細';
comment on column tbl0101_workdetail.workid is '作業ID';
comment on column tbl0101_workdetail.workdetailid is '作業内容ID';
comment on column tbl0101_workdetail.workdetail is '作業内容:作業内容IDから生成したテキスト&任意のコメントを設定';
comment on column tbl0101_workdetail.priority is '優先度';
comment on column tbl0101_workdetail.progressrate is '進捗率';
comment on column tbl0101_workdetail.updatedate is '更新日時';

comment on table tbl0200_plmdl is 'プラモデル一覧';
comment on column tbl0200_plmdl.plmdlid is 'プラモデルID:4桁自動採番';
comment on column tbl0200_plmdl.plmdlnm is 'プラモデル名';
comment on column tbl0200_plmdl.brandid is 'ブランドID';
comment on column tbl0200_plmdl.soldym is '発売年月';
comment on column tbl0200_plmdl.posession is '所持:0:無,1:有';

