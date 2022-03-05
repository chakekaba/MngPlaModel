-- tbl0003_workdetail 作業詳細一覧

-- テーブル削除
-- drop table tbl0003_workdetail;

-- テーブル作成
create table tbl0003_workdetail (
    workid character(4) not null,
    workdetailid character(8) not null,
    workdetail varchar(30) not null,
    priority character(1) not null,
    progressrate integer not null
);

-- 制約設定
-- -- 主キー
alter table tbl0003_workdetail add primary key (workid ,workdetailid);
-- -- 外部キー制約
alter table tbl0003_workdetail add foreign key (workid) references tbl0002_worklist(workid);

-- 論理名・コメント付与
comment on table tbl0003_workdetail is '作業詳細一覧';
comment on column tbl0003_workdetail.workid is '作業id:4桁数字';
comment on column tbl0003_workdetail.workdetailid is '作業内容id:半角英数';
comment on column tbl0003_workdetail.workdetail is '作業内容:日本語表記';
comment on column tbl0003_workdetail.priority is '優先度:0:最低、1:低、2:中、3:高、4:最高';
comment on column tbl0003_workdetail.progressrate is '進捗率:';


-- サンプルデータ追加
-- insert into tbl0003_workdetail () values ();
-- insert into tbl0003_workdetail () values ();

