package com.example.katsuya.mydbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tableの定義 -contract class

        //今回のテーブル(name, score)

        //open helper -> open db -> 処理 -> close db

        //dbオープン
        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();

        //トランザクシン
        try {
            db.beginTransaction();
            db.execSQL("update users " +
                    "set score = score + 10 " +
                    "where name = 'taguchi'");
            db.execSQL("update users " +
                    "set score = score - 10 " +
                    "where name = 'fkoji'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        //挿入
        ContentValues newUser = new ContentValues();
        newUser.put(UserContract.Users.COL_NAME, "tanaka");
        newUser.put(UserContract.Users.COL_SCORE, 44);
        long newId = db.insert(
                UserContract.Users.TABLE_NAME,
                null,
                newUser
        );

        //更新
        ContentValues newScore = new ContentValues();
        newScore.put(UserContract.Users.COL_SCORE, 100);
        int updatedCount = db.update(  //更新した数が返ってくる
                UserContract.Users.TABLE_NAME,
                newScore,
                UserContract.Users.COL_NAME + " = ?",
                new String[] { "fkoji" }
        );

        //削除
        int deletedCount = db.delete(  //削除した数が返ってくる
                UserContract.Users.TABLE_NAME,
                UserContract.Users.COL_NAME + " = ?",
                new String[] { "dotinstall" }
        );

        // 抽出
        Cursor c = null;

        c = db.query(
                UserContract.Users.TABLE_NAME,
                null, // fields
                UserContract.Users.COL_SCORE + " > ?", // where
                new String[] { "50" }, // where arg
                null, // groupBy
                null, // having
                UserContract.Users.COL_SCORE + " desc",  // order by
                "1" //limit
        );

        //テスト
        Log.v("DB_TEST", "Count: " + c.getCount());
        while(c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(UserContract.Users._ID));
            String name = c.getString(c.getColumnIndex(UserContract.Users.COL_NAME));
            int score = c.getInt(c.getColumnIndex(UserContract.Users.COL_SCORE));
            Log.v("DB_TEST", "id: " + id + " name: " + name + " score: " + score);
        }

        // cursor クローズ
        c.close();

        // dbクローズ
        db.close();
    }

}



//adb shell を使うと便利だよ ->ターミナルからdbを操作できる