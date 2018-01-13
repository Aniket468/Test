package com.example.aniketkumar.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
   ImageView iv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iv=(ImageView)findViewById(R.id.img);
        tv=(TextView)findViewById(R.id.sell);
        Animation ani= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
        iv.setAnimation(ani);
        tv.setAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                     finish();
                     startActivity(new Intent(getApplicationContext(),Login_Activity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
