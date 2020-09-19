package com.example.a1051647_final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private SQLiteDatabase db=null;
    private TextView txtUserName,txtUserPwd;
    private EditText edtUserName,edtUserPwd;
    private Button btnAddUser,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = (TextView)findViewById(R.id.txtUserName);
        txtUserPwd = (TextView)findViewById(R.id.txtUserPwd);
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtUserPwd = (EditText)findViewById(R.id.edtUserPwd);
        btnAddUser = (Button)findViewById(R.id.btnAddUser);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnAddUser.setOnClickListener(addListener);
        btnCancel.setOnClickListener(cancelListener);

        db=openOrCreateDatabase("db1.db", Context.MODE_PRIVATE,null);
        try{
            String table = "CREATE TABLE"+" user(userId TEXT PRIMARY KEY ,pwd TEXT NOT NULL)";
            db.execSQL(table);
            Toast toast=Toast.makeText(LoginActivity.this,"db create success",Toast.LENGTH_LONG);
            toast.show();
        }catch (Exception e){
            Toast toast=Toast.makeText(LoginActivity.this,"welcome to log in!",Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private Button.OnClickListener addListener = new Button.OnClickListener(){
        public void onClick(View v){
            String name = edtUserName.getText().toString();
            String pwd1 = edtUserPwd.getText().toString();
            String add1 = "INSERT INTO user (userId,pwd)  values ('"+name+"','"+pwd1+"')";
            Cursor cursor = db.rawQuery("select * from user where userId==? or pwd==?",new String[]{name,pwd1});
            if(name.equals("") || pwd1.equals("")){
                Toast toast=Toast.makeText(LoginActivity.this,"帳號密碼不能是空白，請重新輸入~",Toast.LENGTH_LONG);
                toast.show();
            }
            else if (cursor.getCount() != 0){
                Toast toast=Toast.makeText(LoginActivity.this,"帳號密碼已存在，請重新輸入~",Toast.LENGTH_LONG);
                toast.show();
                edtUserName.setText("");
                edtUserPwd.setText("");
            }
            else{
                db.execSQL(add1);
                Toast toast=Toast.makeText(LoginActivity.this,"~註冊成功，歡迎使用~",Toast.LENGTH_LONG);
                toast.show();
                edtUserName.setText("");
                edtUserPwd.setText("");
            }

        }
    };

    private Button.OnClickListener cancelListener = new Button.OnClickListener(){
      public void onClick(View v){
          finish();
      }
    };

}
