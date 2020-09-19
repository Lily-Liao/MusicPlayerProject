package com.example.a1051647_final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db=null;

    private TextView txtAccount,txtPassword;
    private EditText edtAccount,edtPassword;
    private Button btnLogin,btnexit,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAccount=(TextView)findViewById(R.id.txtAccount);
        txtPassword=(TextView)findViewById(R.id.txtPassword);
        edtAccount=(EditText)findViewById(R.id.edtAccount);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnexit=(Button)findViewById(R.id.btnexit);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        Typeface tf=Typeface.createFromAsset(getAssets(),"materialdesignicons-webfont.ttf");
        txtAccount.setTypeface(tf);
        txtPassword.setTypeface(tf);
        btnLogin.setTypeface(tf);
        btnexit.setTypeface(tf);
        btnRegister.setTypeface(tf);

        btnLogin.setOnClickListener(Listener);
        btnexit.setOnClickListener(exitListener);
        btnRegister.setOnClickListener(registerListener);


        db=openOrCreateDatabase("db1.db", Context.MODE_PRIVATE,null);
        try{
            String table = "CREATE TABLE "+" user(userId TEXT PRIMARY KEY ,pwd TEXT NOT NULL)";
            db.execSQL(table);
            String str1="INSERT INTO user(userId,pwd) values"+" ('aaa','123456')";
            db.execSQL(str1);
//            Toast toast=Toast.makeText(MainActivity.this,"db create",Toast.LENGTH_LONG);
//            toast.show();
        }catch (Exception e){
            Toast toast=Toast.makeText(MainActivity.this,"welcome to use music app!",Toast.LENGTH_LONG);
            toast.show();

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }

    private Button.OnClickListener Listener=new Button.OnClickListener(){

        public void onClick(View v){
            String a=edtAccount.getText().toString();
            String b=edtPassword.getText().toString();

            Cursor cursor = db.rawQuery("select * from user where userId==? and pwd==?",new String[]{a,b});
            if( cursor.getCount() >= 1){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                edtAccount.setText("");
                edtPassword.setText("");
            }else{
                Toast toast=Toast.makeText(MainActivity.this,"帳號或密碼錯誤，請重新輸入!",Toast.LENGTH_LONG);
                toast.show();
                edtAccount.setText("");
                edtPassword.setText("");
                String[] cols = cursor.getColumnNames();
                while(cursor.moveToNext()){
                    for(String ColumnName:cols){
                        Log.i("info",ColumnName+":"+cursor.getString(cursor.getColumnIndex(ColumnName)));
                    }
                }
            }
        }
    };

    private Button.OnClickListener exitListener=new Button.OnClickListener(){
        public void onClick(View v){
            onDestroy();
            System.exit(0);
        }
    };

    private Button.OnClickListener registerListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    };
}
