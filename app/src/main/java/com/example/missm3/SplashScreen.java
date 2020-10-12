package com.example.missm3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    String emailStored = "", passwordStored = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                //editor.clear();  //for bebugging
                //editor.commit(); //for bebugging
                emailStored = pref.getString("name", null);
                passwordStored = pref.getString("password", null);

                if(emailStored == null){

                    Date c = Calendar.getInstance().getTime();
                    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
//                    todaysDate.setText(df.format(c));

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH");
//                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                    String heure = sdf.format(cal.getTime());

                    SimpleDateFormat dd = new SimpleDateFormat("EEEE");
                    Date d = new Date();
                    String dayOfTheWeek = dd.format(d);


                    int hour=Integer.parseInt(heure);


                   Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);

                }
                SplashScreen.this.finish();
            }
        }, 3000);

    }
}
