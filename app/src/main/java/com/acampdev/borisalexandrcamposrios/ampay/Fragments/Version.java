package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acampdev.borisalexandrcamposrios.ampay.R;

public class Version extends Fragment{

    private  static final long TIME_EFECT= 3000;

    public void onAttachedToWindow(){
        super.getActivity().onAttachedToWindow();
        Window window= getActivity().getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashTeard;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_version,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.alpha);
        animation.reset();
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.versionFragment);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(animation);

        animation= AnimationUtils.loadAnimation(getContext(),R.anim.translate);
        animation.reset();
        ImageView imageView=(ImageView) view.findViewById(R.id.versionImg);
        imageView.clearAnimation();
        imageView.startAnimation(animation);

        splashTeard= new Thread(){
            @Override
            public void run(){
                try{
                    int waited =0;
                    // splash screen pause tieme
                    while (waited <TIME_EFECT){
                        sleep(100);
                        waited+=100;
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        };
        splashTeard.start();

    }
}
