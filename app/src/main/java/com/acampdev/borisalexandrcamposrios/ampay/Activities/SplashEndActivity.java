package com.acampdev.borisalexandrcamposrios.ampay.Activities;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.google.firebase.auth.FirebaseAuth;

public class SplashEndActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_splash_end);

        StarAnimations();
    }

    private void StarAnimations(){

        ImageView imageView = (ImageView) findViewById(R.id.loadingEndImgID);
        Glide.with(this).load(R.drawable.preloader)
                .into(imageView);
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
                    FirebaseAuth.getInstance().signOut();
                    Intent intent= new Intent(SplashEndActivity.this,LoginActivity.class);
                    startActivity(intent);
                    SplashEndActivity.this.finish();
                }catch (Exception e){e.printStackTrace();}
                finally {
                    SplashEndActivity.this.finish();
                }
            }
        };
        splashTeard.start();
    }
}
