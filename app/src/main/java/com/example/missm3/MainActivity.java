package com.example.missm3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    EditText editTextNum;
    private static final int PERMISSION_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum = findViewById(R.id.editTextNum);
        btnSend = findViewById(R.id.btnSendMsg);

    }

    public void SendSMS(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST);
        }
    }

    private void MyMessage() {
        String myNumber = editTextNum.getText().toString().trim();

        int nbre=Integer.valueOf(myNumber);
        if (myNumber.equals("")){
            Toast.makeText(this, "Remplissez les valeurs", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isDigitsOnly(myNumber)){

                SmsManager smsManager = SmsManager.getDefault();

                for (int i=0;i<nbre;i++){
                    smsManager.sendTextMessage("1192", null, "M3", null, null);
                }
                //SimUtil.sendSMS(this,1,"51520165",null,"Hi Stackoverflow! its me Maher. Sent by sim2",null,null);

                Toast.makeText(this, "Message envoyé", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Nombre incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MyMessage();
            } else {
                Toast.makeText(this, "You don't have required permission to send a message", Toast.LENGTH_SHORT).show();
            }
        }
    }


}