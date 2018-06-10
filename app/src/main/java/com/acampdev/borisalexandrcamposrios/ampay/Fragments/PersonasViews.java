package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acampdev.borisalexandrcamposrios.ampay.POJOS.Persona;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonasViews extends Fragment{

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Persona,ViewHonder> personasAdapter;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView= inflater.inflate(R.layout.fragment_personas_views,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try{
            databaseReference= FirebaseDatabase.getInstance().getReference();
            recyclerView= (RecyclerView) view.findViewById(R.id.recyclerPerson);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);

            FirebaseRecyclerOptions<Persona> options= new FirebaseRecyclerOptions.Builder<Persona>()
                    .setQuery(databaseReference.child("Personas"), Persona.class).build();


            personasAdapter= new FirebaseRecyclerAdapter<Persona, ViewHonder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull ViewHonder holder, int position, @NonNull Persona model) {
                    holder.nom.setText(model.getNombres());
                    holder.dir.setText(model.getDir());
                    holder.parentesco.setText(model.getParentesco());
                    holder.cel.setText(String.valueOf(model.getTelefono()));
                    holder.dni.setText(String.valueOf(model.getDni()));

                }

                @NonNull
                @Override
                public ViewHonder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personas_items,parent,false);
                    ViewHonder viewHonder= new ViewHonder(view);
                    return viewHonder;
                }
            };
            recyclerView.setAdapter(personasAdapter);

            ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int position= viewHolder.getAdapterPosition();
                    personasAdapter = (FirebaseRecyclerAdapter<Persona,ViewHonder>) recyclerView.getAdapter();
                    personasAdapter.getRef(position).removeValue();
                }
            };

            ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);

        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onStart() {
        super.onStart();
        personasAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static class ViewHonder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView nom,dir,dni,cel,parentesco;

        public ViewHonder(View itemView){
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.cardViewPerson);
            nom=(TextView) itemView.findViewById(R.id.viewNombre);
            dir=(TextView) itemView.findViewById(R.id.viewDireaccion);
            parentesco=(TextView) itemView.findViewById(R.id.viewParentesco);
            cel=(TextView) itemView.findViewById(R.id.viewCel);
            dni=(TextView) itemView.findViewById(R.id.viewDni);
        }
    }


}
