package com.tootcat.cbcscgpacalculator;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase db=openOrCreateDatabase("gpaDb",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS gpaTable(subjectName text,gpa real,credit real);");

        final EditText tsubjectName=(EditText)findViewById(R.id.tSubjectName);
        final EditText tgpa=(EditText)findViewById(R.id.tGPA);
        final Spinner spinner=(Spinner)findViewById(R.id.sCredits);
        Button bFinish=(Button)findViewById(R.id.bFinish);
        Button bAdd=(Button)findViewById(R.id.bAdd);
        List<Integer> spinnerArray=new ArrayList<Integer>();
        spinnerArray.add(1);
        spinnerArray.add(2);
        spinnerArray.add(3);
        spinnerArray.add(4);
        spinnerArray.add(10);
        ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_dropdown_item_1line,spinnerArray);
        spinner.setAdapter(adapter);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String subjectName = tsubjectName.getText().toString();
                    Float gpa = Float.valueOf(tgpa.getText().toString());
                    Integer credits = Integer.valueOf(spinner.getSelectedItem().toString());
                    if (!subjectName.isEmpty()&&gpa>=6)
                        db.execSQL("INSERT INTO gpaTable VALUES('" + subjectName + "'," + gpa + "," + credits + ");");
                    if(gpa<6)
                        Toast.makeText(MainActivity.this,"Invalid cgpa!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                Intent repeat=new Intent(MainActivity.this,MainActivity.class);
                startActivity(repeat);
                MainActivity.this.finish();
            }
        });

        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String subjectName = tsubjectName.getText().toString();
                    Float gpa = Float.valueOf(tgpa.getText().toString());
                    Integer credits = Integer.valueOf(spinner.getSelectedItem().toString());
                    if (!subjectName.isEmpty()&&gpa>=6)
                        db.execSQL("INSERT INTO gpaTable VALUES('" + subjectName + "'," + gpa + "," + credits + ");");

                    if(gpa<6)
                        Toast.makeText(MainActivity.this,"Invalid cgpa!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                startActivity(new Intent(MainActivity.this,Finish.class));
                MainActivity.this.finish();
            }
        });










    }
}
