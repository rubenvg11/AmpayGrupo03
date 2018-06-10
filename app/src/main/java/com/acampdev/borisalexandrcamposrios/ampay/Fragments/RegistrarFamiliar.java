package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.acampdev.borisalexandrcamposrios.ampay.POJOS.Persona;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarFamiliar extends Fragment implements AdapterView.OnItemSelectedListener{
    //implements AdapterView.OnItemSelectedListener
    EditText nombre,direcion,celular,dniDoc;
    Spinner spinner;
    Button guardad;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_registro_familiar,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        spinner= (Spinner) view.findViewById(R.id.addParentesco);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getContext(),R.array.parentesco,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        nombre=(EditText) view.findViewById(R.id.addNombre);
        dniDoc=(EditText) view.findViewById(R.id.addDniPersona);
        direcion= (EditText) view.findViewById(R.id.addDirPersona);
        celular=(EditText) view.findViewById(R.id.addPhone);

        guardad=(Button) view.findViewById(R.id.addGuardarFamiliar);

        guardad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name= nombre.getText().toString();
                    String dni= dniDoc.getText().toString();
                    String address= direcion.getText().toString();
                    String phone= celular.getText().toString();
                    String parentesco= String.valueOf(spinner.getSelectedItem());


                    Persona persona= new Persona(name,address,parentesco,Integer.valueOf(phone),Integer.valueOf(dni));
                    databaseReference.child("Personas").child(name).setValue(persona);

                    Toast.makeText(getContext(),"Familiar agregado Exitosamente",Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction transaction= fragmentManager.beginTransaction();
                    PersonasViews personasViews = new PersonasViews();
                    transaction.replace(R.id.fragment,personasViews);
                    transaction.addToBackStack("registroUbicaciones");
                    transaction.commit();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getContext(),"Conexion fallida no tienes permisos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
