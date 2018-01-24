package com.example.aniketkumar.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by MNNIT on 1/11/2018.
 */

public class Login_Activity extends AppCompatActivity {

    TextView tv;
    Button continuewithoutlogin;

    Button login_button;
    TextView sign_up;
    TextView forgot;



    @Override
    public void onBackPressed() {
        //  super.onBackPressed();


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("Warning!!!");
        builder.setMessage("Are you really want to Exit");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //  AlertDialog alert=builder.create();
        //alert.show();
        builder.show();
    }


    @Override
    protected void onCreate(
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_button= (Button) findViewById(R.id.login_press);

        tv=findViewById(R.id.sign_link);
        tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
        continuewithoutlogin= (Button) findViewById(R.id.withoutlogin);
        continuewithoutlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

            }
        });


        //On clicking the login button

        }
}



