package com.tootcat.cbcscgpacalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        final SQLiteDatabase db=openOrCreateDatabase("gpaDb",MODE_PRIVATE,null);
        TextView tcgpa=(TextView)findViewById(R.id.cgpa);
        int totalCredits;
        float product=0,cgpa;
        Cursor cursor = db.rawQuery("SELECT SUM(credit) FROM gpaTable", null);
        cursor.moveToFirst();
        totalCredits=cursor.getInt(0);

        cursor=db.rawQuery("SELECT SUM(gpa*credit) FROM gpaTable",null);
        cursor.moveToFirst();
        product=cursor.getFloat(0);

        cgpa=product/totalCredits;
        tcgpa.setText(String.valueOf(cgpa));



    }
}
