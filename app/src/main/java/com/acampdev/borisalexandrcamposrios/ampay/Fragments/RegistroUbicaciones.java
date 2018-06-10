package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.acampdev.borisalexandrcamposrios.ampay.Adapters.UbicacionesAdapter;
import com.acampdev.borisalexandrcamposrios.ampay.POJOS.Ubicacion;
import com.acampdev.borisalexandrcamposrios.ampay.R;

import java.util.ArrayList;
import java.util.List;

public class RegistroUbicaciones extends Fragment {

    RecyclerView recyclerViewUbicaciones;
    List<Ubicacion> ubicacionList;
    UbicacionesAdapter ubicacionesAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_registro_ubicaciones,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerViewUbicaciones= (RecyclerView) view.findViewById(R.id.recyclerID);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewUbicaciones.setLayoutManager(linearLayoutManager);

        createData();
        ubicacionesAdapter= new UbicacionesAdapter(getActivity().getApplicationContext(),ubicacionList);
        recyclerViewUbicaciones.setAdapter(ubicacionesAdapter);

    }

    public void createData(){
        ubicacionList= new ArrayList<>();
        ubicacionList.add(new Ubicacion(
                "ruben vg",
                "apurimat 438 urb Palermo Trujillo",
                "-8.104301",
                "-79.011024",
                "https://cdn.pixabay.com/photo/2016/03/22/04/23/map-1272165_960_720.png"));
        ubicacionList.add(new Ubicacion(
                "Luis Azabache",
                "Lucio Seneca 245 urb La Noria Trujillo",
                "-8.104508",
                "-79.009301",
                "https://cdn.pixabay.com/photo/2016/03/22/04/23/map-1272165_960_720.png"));
        ubicacionList.add(new Ubicacion(
                "Carlos Diez",
                "Marianoa Bejar Las Quintanas Trujillo",
                "-8.099901",
                " -79.029436",
                "https://cdn.pixabay.com/photo/2016/03/22/04/23/map-1272165_960_720.png"));
        ubicacionList.add(new Ubicacion(
                "Marco Polo",
                "-8.104508",
                "-79.009301",
                "Cosme Bueno 145 urb La Noria Trujillo",
                "https://cdn.pixabay.com/photo/2016/03/22/04/23/map-1272165_960_720.png"));
        ubicacionList.add(new Ubicacion(
                "Luchito Benites",
                "Av españa 676 Centro Historico Trujillo",
                "-8.108702",
                "-79.031112",
                "https://cdn.pixabay.com/photo/2016/03/22/04/23/map-1272165_960_720.png"));
    }
}
