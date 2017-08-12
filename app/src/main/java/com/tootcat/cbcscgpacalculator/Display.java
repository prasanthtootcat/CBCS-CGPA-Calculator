package com.tootcat.cbcscgpacalculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button bDrop=(Button)findViewById(R.id.bDrop);
        final SQLiteDatabase db=openOrCreateDatabase("gpaDb",MODE_PRIVATE,null);
        Cursor cursor=db.rawQuery("SELECT * FROM gpaTable",null);
        TextView output=(TextView)findViewById(R.id.tOutput);
        TextView tCgpa=(TextView)findViewById(R.id.tCgpa);
        cursor.moveToFirst();

        output.append("\t\t\tSubject Name \t \t Grades");

        do
        {
            if(cursor.getCount()!=0)
            output.append("\n\n\t\t\t"+cursor.getString(0)+"\t\t"+cursor.getString(1)+"\n");
        }while(cursor.moveToNext());

        int totalCredits;
        float product=0,cgpa;
        cursor = db.rawQuery("SELECT SUM(credit) FROM gpaTable", null);
        cursor.moveToFirst();
        totalCredits=cursor.getInt(0);

        cursor=db.rawQuery("SELECT SUM(gpa*credit) FROM gpaTable",null);
        cursor.moveToFirst();
        product=cursor.getFloat(0);

        cgpa=product/totalCredits;
        tCgpa.setText(String.valueOf(cgpa));

        bDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DROP TABLE gpaTable");
                startActivity(new Intent(Display.this,MainActivity.class));
                Display.this.finish();
            }
        });

    }
}
