package com.apkglobal.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String sid,sname,smobile,semail;
    EditText et_name,et_mobile,et_email;
    Button btn_save,btn_show;
    SQLiteDatabase sd;
    TextView tvid,tvname,tvmobile,tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText)findViewById(R.id.et_name);
        et_mobile=(EditText)findViewById(R.id.et_mobile);
        et_email=(EditText)findViewById(R.id.et_email);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_show=(Button)findViewById(R.id.btn_show);
        tvid=(TextView)findViewById(R.id.tvid);
        tvname=(TextView)findViewById(R.id.tvname);
        tvmobile=(TextView)findViewById(R.id.tvmobile);
        tvemail=(TextView)findViewById(R.id.tvemail);




        //create database or open database for new or exoisting user
        createmydatabase();

    }

    private void createmydatabase() {
        //create database in sqlite with name and mode
        sd= openOrCreateDatabase("Register", Context.MODE_PRIVATE,null);
        //create a new table in register database
        sd.execSQL("create table if not exists register"+
                "(id integer primary key autoincrement,"+"name varchar(100),"+"mobile varchar(100),"+"email varchar(200));");
        Toast.makeText(getApplicationContext(),"Table Created !",Toast.LENGTH_LONG).show();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inser data into table
                insertmydatabase();
                fetchmydatabase();
            }

            private void fetchmydatabase() {
                String fetch="select * from register;/n";
                Cursor c=sd.rawQuery(fetch,null);
                c.moveToLast();
                tvid.setText(c.getString(0).toString());
                tvname.setText(c.getString(1).toString());
                tvmobile.setText(c.getString(2).toString());
                tvemail.setText(c.getString(3).toString());

            }

            private void insertmydatabase() {
                sname=et_name.getText().toString();
                smobile=et_mobile.getText().toString();
                semail=et_email.getText().toString();

                sd.execSQL("insert into register(name,mobile,email) values"+"('"+sname+"','"+smobile+"'.'"+semail+"')");
            }
        });
    }


}
