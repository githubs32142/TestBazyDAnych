package com.example.android.testbazydanych;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by android on 2017-12-13.
 */

public class MySqlLite extends SQLiteOpenHelper {
    public MySqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS OSOBY;");
        db.execSQL("CREATE TABLE OSOBY (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "NAZWISKO CHAR (20) NOT NULL, "  +
                "PESEL CHAR(9) NOT NULL,"+
        "DATAUR DATE," +"WZROST REAL)");
        Log.d("DB","Tabela utworzona");
        db.execSQL("INSERT INTO OSOBY (NAZWISKO, PESEL, DATAUR, WZROST) VALUES("
        + "'Kowalski', '123456789', '2017-12-06', 172.4)");
        db.execSQL("INSERT INTO OSOBY (NAZWISKO, PESEL, DATAUR, WZROST) VALUES("
                + "'Nowak', '123456788', '2017-12-06', 181.0)");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
