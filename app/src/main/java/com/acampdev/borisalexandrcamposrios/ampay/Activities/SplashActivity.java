package com.acampdev.borisalexandrcamposrios.ampay.Activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import android.os.Bundle;
import android.view.Window;
import android.graphics.PixelFormat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 3500;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashTeard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StarAnimations();
    }

    private void StarAnimations(){
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.alpha);
        animation.reset();
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.splashActivityID);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(animation);

        animation= AnimationUtils.loadAnimation(this,R.anim.translate);
        animation.reset();
        ImageView imageView=(ImageView) findViewById(R.id.loadingApp);
        imageView.clearAnimation();
        imageView.startAnimation(animation);

        splashTeard = new Thread(){
            @Override
            public  void run(){
                try{
                    int waited =0;
                    // splash screen pause tieme
                    while (waited <SPLASH_SCREEN_DELAY){
                        sleep(100);
                        waited+=100;
                    }
                    Intent intent= new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }catch (Exception e){e.printStackTrace();}
                finally {
                    SplashActivity.this.finish();
                }
            }
        };
        splashTeard.start();
    }
}
