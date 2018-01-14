package com.example.aniketkumar.test;

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
    Button login_button,continuewithoutlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_button= (Button) findViewById(R.id.login_press);

        tv=(TextView)findViewById(R.id.sign);
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
                Intent intent=new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
