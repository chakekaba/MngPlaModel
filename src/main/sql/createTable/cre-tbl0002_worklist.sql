-- tbl0002_worklist 作業箇所一覧

-- テーブル削除
-- drop table tbl0002_worklist;

-- テーブル作成
create table tbl0002_worklist (
    workid character(4) primary key,
    plasticmodel varchar(20) not null,
    workplace varchar(30) not null,
    memo varchar(600) not null
);

-- 論理名・コメント付与
comment on table tbl0002_worklist is '作業箇所一覧';
comment on column tbl0002_worklist.workid is '作業id:4桁数字';
comment on column tbl0002_worklist.plasticmodel is '作業箇所';
comment on column tbl0002_worklist.workplace is '作業箇所';
comment on column tbl0002_worklist.memo is 'メモ';

-- サンプルデータ追加
-- insert into tbl0002_worklist (workid, plasticmodel, workplace, memo) values ();
-- insert into tbl0002_worklist (workid, plasticmodel, workplace, memo) values ();

