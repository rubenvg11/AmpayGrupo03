package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acampdev.borisalexandrcamposrios.ampay.Activities.LoginActivity;
import com.acampdev.borisalexandrcamposrios.ampay.Activities.SplashEndActivity;
import com.acampdev.borisalexandrcamposrios.ampay.R;

import java.util.Timer;
import java.util.TimerTask;

public class Salir extends Fragment {

    private static final long SPLASH_SCREEN_DELAY = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_salir,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), SplashEndActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,SPLASH_SCREEN_DELAY);

    }
}
