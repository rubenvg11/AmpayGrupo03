package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Localizacion extends Fragment implements OnMapReadyCallback{

    GoogleMap mMap;
    MapView mapView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_localizacion,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsG);
        /*
        if(supportMapFragment==null){
            FragmentManager fragmentManager= getFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            supportMapFragment= SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapsG, supportMapFragment).commit();
        }*/
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
       googleMap.addMarker(new MarkerOptions().position(new LatLng(-8.130360, -79.043852)).title("Prueba ucv").snippet("inicamos"));
        CameraPosition cameraPosition= CameraPosition.builder().target(new LatLng(-8.130360, -79.043852)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
