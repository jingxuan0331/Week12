package com.example.week12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week12.Main2Activity;
import com.example.week12.Main3Activity;
import com.example.week12.MyDBHandler;
import com.example.week12.UserData;
public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private TextView tv;
    private EditText etuser;
    private EditText etpass;
    private static final String Tag = "MainActivity.java";

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.NewUser);
        tv.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {

        Log.v(Tag, "Touch start");
        Intent intent = new Intent(com.example.week12.MainActivity.this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }

    public void onClick(View v)
    {
       etuser = findViewById(R.id.UserName);
       etpass = findViewById(R.id.Password);

        if (isValidUser(etuser.getText().toString(),etpass.getText().toString())){
            Intent intent = new Intent(com.example.week12.MainActivity.this, Main3Activity.class);
            Toast.makeText(com.example.week12.MainActivity.this,"valid user",Toast.LENGTH_LONG).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(com.example.week12.MainActivity.this,"Invalid user",Toast.LENGTH_LONG).show();
        }


    }

    public boolean isValidUser(String userName, String password)
    {
        UserData dbData = dbHandler.findUser(userName);
        //UserData dbData1 = dbHandler.findUser(password);


        if ((dbData.getMyUserName().equals(userName)) && dbData.getMyPassword().equals(password))
        {
            return true;
        }
        return false;
    }


}
