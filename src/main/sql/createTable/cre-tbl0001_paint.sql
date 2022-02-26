-- tbl0001_paint 塗料一覧

-- テーブル削除
-- drop table tbl0001_paint;

-- テーブル作成
create table tbl0001_paint (
    paintcode character(10) primary key,
    distributor varchar(20) not null,
    brand varchar(20) not null,
    color varchar(30) not null,
    painttype character(1) not null,
    possession character(1) not null,
    selvisible character(1) not null
);

-- 論理名・コメント付与
comment on column tbl0001_paint.paintcode is '塗料コード';
comment on column tbl0001_paint.distributor is '販売元企業';
comment on column tbl0001_paint.brand is 'ブランド名';
comment on column tbl0001_paint.color is 'カラー名';
comment on column tbl0001_paint.painttype is '塗料種別:0:ラッカー、1:アクリル、2:エナメル';
comment on column tbl0001_paint.possession is '所持:0:未所持、1:所持';
comment on column tbl0001_paint.selvisible is '選択肢表示:0:非表示、1:表示';


-- サンプルデータ追加
insert into tbl0001_paint (paintcode, distributor, brand, color, painttype, possession, selvisible) values ('XF-25', 'タミヤ', 'タミヤカラー', 'ライトシーグレイ', '1', '1', '1');
insert into tbl0001_paint (paintcode, distributor, brand, color, painttype, possession, selvisible) values ('XF-1', 'タミヤ', 'タミヤカラー', 'フラットブラック', '1', '1', '1');

