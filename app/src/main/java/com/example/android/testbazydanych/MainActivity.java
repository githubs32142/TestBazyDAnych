package com.example.android.testbazydanych;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= openOrCreateDatabase("mydb.db",MODE_PRIVATE,null);
        Log.d("DB","Baza utworzona");
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
        //db.close();
        Cursor cursor= db.rawQuery("SELECT * FROM OSOBY",null);
        sendToLog(cursor);
        addPerson(db,"Jaki","123443219","1993-10-10","182.0");
        addPerson(db,"Subpe","123443819","1999-10-10","132.0");
        cursor= db.rawQuery("SELECT * FROM OSOBY",null);
        sendToLog(cursor);
        updateHeidht(db,"2","200");
        cursor= db.rawQuery("SELECT * FROM OSOBY",null);
        sendToLog(cursor);
        selectPersonAbove(db,"180");
        agvHeight(db);
        deletePersonUnder(db,"180");
        cursor= db.rawQuery("SELECT * FROM OSOBY",null);
        sendToLog(cursor);
    }

    private void sendToLog(Cursor cursor) {
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String nazwisko= cursor.getString(1);
            String pesel= cursor.getString(cursor.getColumnIndex("PESEL"));
            String dataUr= cursor.getString(cursor.getColumnIndex("DATAUR"));
            double wzrost= cursor.getDouble(4);
            Log.d("DB",+id+"\t"+nazwisko+"\t"+pesel+"\t"+dataUr+"\t"+wzrost);
        }
    }
    private void addPerson(SQLiteDatabase bd,String surName,String pesel,String dataBorn,String height){
        db.execSQL("INSERT INTO OSOBY (NAZWISKO, PESEL, DATAUR, WZROST) VALUES("
                + "'"+surName+"', '"+pesel+"', '"+dataBorn+"',"+height+")");
    }
    //http://lewandowskit.s.pwste.edu.pl/android/AndroidDB.pdf
    private void updateHeidht(SQLiteDatabase bd,String id,String height){
        db.execSQL("UPDATE  OSOBY SET WZROST="+height+" WHERE ID="+id);
    }
    private void selectPersonAbove(SQLiteDatabase db, String height){
        Cursor cursor= db.rawQuery("SELECT * FROM OSOBY WHERE WZROST> "+height,null);
        sendToLog(cursor);
    }
    private void agvHeight(SQLiteDatabase db){
        Cursor cursor= db.rawQuery("SELECT AVG(WZROST) FROM OSOBY",null);
        while (cursor.moveToNext()){;
            double wzrost= cursor.getDouble(0);
            Log.d("DB",String.valueOf(wzrost)+"\n");
        }
    }
    private void deletePersonUnder(SQLiteDatabase db, String height){
         db.delete("Osoby","WZROST<"+height,new String[]{});

    }

}
