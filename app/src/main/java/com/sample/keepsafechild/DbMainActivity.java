package com.sample.keepsafechild;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DbMainActivity  extends AppCompatActivity {

    Button btn_reset, btn_search, btn_save;
    TextView lat,lng,watch_lat, watch_lon;

    // DB객체 선언
    MyDB myDB;
    SQLiteDatabase sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        btn_reset = findViewById(R.id.btn_reset);
        btn_search = findViewById(R.id.btn_search);
        btn_save = findViewById(R.id.btn_save);

        watch_lat = findViewById(R.id.watch_lat);
        watch_lon = findViewById(R.id.watch_lon);

        // DB객체 생성
        // context를 호출하는것 보통은 this
        myDB = new MyDB(this);

        // 저장
        // Et들의 내용을 가져와서 DB에 저장
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 쓰기 권한을 허용하겠다.
                sql = myDB.getWritableDatabase();

                //  et_hakbun.getText().toString()
                //  String.valueOf(et_name()) --> 자바코드
                //  위 아래 같은코드지만 자바에선 아래 코드를 사용한다.
                String query = "INSERT INTO member(hakbun, name, address)\n" +
                        "VALUES (" + lat.getText().toString() + ",'" + lng.getText().toString() + "')";
                sql.execSQL(query);
                sql.close();    // DB와 연결 객체를 닫겠다.

                Toast.makeText(DbMainActivity.this, "좌표 저장 완료", Toast.LENGTH_SHORT).show();
            }
        });

        // 조회버튼
        // member테이블 내용 조회해서 화면에 보여주기
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 읽기 권한을 허용하겠다.
                sql = myDB.getReadableDatabase();
                // 조회권환은 리턴값이 있기 때문에 커서를 사용한다. rawQuery()
                Cursor cursor;
                cursor = sql.rawQuery("SELECT * FROM member", null);
                String  kd = "경도: \r\n";
                String  wd = "위도: \r\n";

                while (cursor.moveToNext()) {
                   kd += cursor.getString(1) + "\r\n";
                   wd += cursor.getString(2) + "\r\n";
                }
                watch_lat.setText(kd);
                watch_lon.setText(wd);

                //  커서라는것은 깜빡이는것
                //  무언가 가르키는 것
                //
                cursor.close();
                sql.close();
            }
        });


    }
}
