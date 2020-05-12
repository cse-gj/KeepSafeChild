package com.sample.keepsafechild;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDB extends SQLiteOpenHelper {
    public MyDB(Context context) {
        // 내부저장소에 좌표값이 저장되게 하는 곳
        // 1: 어플리케이션 자체, 2: 데이터 베이스 파일이름, 3,4: 중요하지 않습니다.
        super(context, "safechildren", null, 1);
    }

    // mydb가 호출될때 sql을 만들어라.
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE member(\n" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                " , lat INTEGER\n" +
                " , lng INTEGER\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS member";   //만약 member테이블이 존재하면 삭제하
        db.execSQL(sql);
        onCreate(db);
    }
}

