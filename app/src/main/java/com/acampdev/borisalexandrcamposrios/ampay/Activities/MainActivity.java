package com.acampdev.borisalexandrcamposrios.ampay.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.acampdev.borisalexandrcamposrios.ampay.Fragments.Localizacion;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.PersonasViews;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.RegistrarFamiliar;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.RegistroUbicaciones;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.Salir;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.Soporte;
import com.acampdev.borisalexandrcamposrios.ampay.Fragments.Version;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    FloatingActionButton fabPlus, fabAdd, fabMap;
    Animation fabOpen, fabClose,fabrotateClowise,fabantiClowise;
    Boolean isOpen= false;

    //private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutID);

        // FAB
        fabPlus=(FloatingActionButton) findViewById(R.id.fab_plusID);
        fabAdd=(FloatingActionButton) findViewById(R.id.fab_addID);
        fabMap=(FloatingActionButton) findViewById(R.id.fab_mapID);
        fabOpen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabrotateClowise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabantiClowise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        // FAB animation
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fabAdd.startAnimation(fabClose);
                    fabMap.startAnimation(fabClose);
                    fabPlus.startAnimation(fabantiClowise);
                    fabMap.setClickable(false);
                    fabAdd.setClickable(false);
                    isOpen= false;
                }else{
                    fabAdd.startAnimation(fabOpen);
                    fabMap.startAnimation(fabOpen);
                    fabPlus.startAnimation(fabrotateClowise);
                    fabMap.setClickable(true);
                    fabMap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fabLocalizacion();
                            fabAdd.startAnimation(fabClose);
                            fabMap.startAnimation(fabClose);
                            fabPlus.startAnimation(fabantiClowise);
                            fabMap.setClickable(false);
                            fabAdd.setClickable(false);
                            isOpen= false;
                        }
                    });
                    fabAdd.setClickable(true);
                    fabAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fabAddPersonas();
                            fabAdd.startAnimation(fabClose);
                            fabMap.startAnimation(fabClose);
                            fabPlus.startAnimation(fabantiClowise);
                            fabMap.setClickable(false);
                            fabAdd.setClickable(false);
                            isOpen= false;
                        }
                    });
                    isOpen= true;
                }
            }
        }); // end Animation FAB

        navigationView= (NavigationView) findViewById(R.id.navigationView);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                drawerLayout.closeDrawers();

                switch (item.getItemId()){
                    case R.id.registroUbicaciones:
                        setFragment(0);
                        break;
                    case R.id.registrarFamiliar:
                        setFragment(1);
                        break;
                    case R.id.personViews:
                        setFragment(2);
                        break;
                    case R.id.localizacion:
                        setFragment(3);
                        break;
                    case R.id.soporte:
                        setFragment(4);
                        break;
                    case R.id.version:
                        setFragment(5);
                        break;
                    case R.id.salir:
                        setFragment(6);
                        break;
                    default:
                }


                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // iniciamos view principal pasando el setFragment
        setFragment(0);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            //if(getFragmentManager() instanceof ){}
            super.onBackPressed();}
    }

    public  void fabAddPersonas(){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        RegistrarFamiliar registrarFamiliar= new RegistrarFamiliar();
        transaction.replace(R.id.fragment,registrarFamiliar);
        transaction.commit();
    }

    public void fabLocalizacion(){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        Localizacion localizacion= new Localizacion();
        transaction.replace(R.id.fragment,localizacion);
        transaction.commit();
    }


    public void setFragment(int pos){

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();

        switch (pos){
            case 0:
                RegistroUbicaciones registroUbicaciones= new RegistroUbicaciones();
                transaction.replace(R.id.fragment,registroUbicaciones);
                //transaction.disallowAddToBackStack();
                transaction.commit();
                break;
            case 1:
                RegistrarFamiliar registrarFamiliar= new RegistrarFamiliar();
                transaction.replace(R.id.fragment,registrarFamiliar);
                //transaction.addToBackStack("registroUbicaciones");
                transaction.commit();
                break;
            case 2:
                PersonasViews personasViews= new PersonasViews();
                transaction.replace(R.id.fragment,personasViews);
                //transaction.addToBackStack("registroUbicaciones");
                transaction.commit();
                break;
            case 3:
                Localizacion localizacion= new Localizacion();
                transaction.replace(R.id.fragment,localizacion);
                //transaction.addToBackStack("registroUbicaciones");
                transaction.commit();
                break;
            case 4:
                Soporte soporte= new Soporte();
                transaction.replace(R.id.fragment,soporte);
                //transaction.addToBackStack("registroUbicaciones");
                transaction.commit();
                break;
            case 5:
                Version version= new Version();
                transaction.replace(R.id.fragment,version);
                //transaction.addToBackStack("registroUbicaciones");
                transaction.commit();
                break;
            case 6:
                Salir salir= new Salir();
                transaction.replace(R.id.fragment,salir);
                transaction.commit();
                break;
            default:
        }
    }
}
