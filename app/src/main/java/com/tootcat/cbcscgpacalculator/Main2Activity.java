package com.tootcat.cbcscgpacalculator;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final SQLiteDatabase db=openOrCreateDatabase("gpaDb",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS gpaTable(subjectName text,gpa real,credit real);");

        Button bView=(Button)findViewById(R.id.bView);
        Button bMain=(Button)findViewById(R.id.bMain);

        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this,Display.class));

            }
        });

        bMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });
    }
}
