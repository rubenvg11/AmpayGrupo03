package com.acampdev.borisalexandrcamposrios.ampay.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.acampdev.borisalexandrcamposrios.ampay.R;
import com.acampdev.borisalexandrcamposrios.ampay.POJOS.Ubicacion;

import java.util.List;

public class UbicacionesAdapter extends RecyclerView.Adapter<UbicacionesAdapter.ViewHolder>{

    Context context;
    private List<Ubicacion> ubicacionList;

    public UbicacionesAdapter(Context context, List<Ubicacion> ubicacionList){
        this.context=context;
        this.ubicacionList=ubicacionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ubicaciones_items,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(ubicacionList.get(position).getNombre());
        holder.direccion.setText(ubicacionList.get(position).getDireccion());
        holder.latitud.setText(ubicacionList.get(position).getLatitud());
        holder.longitud.setText(ubicacionList.get(position).getLongitud());
        holder.imageView.setImageResource(R.drawable.maps_icon);
    }

    @Override
    public int getItemCount() {
        return ubicacionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView nombre,direccion,latitud,longitud;

        public ViewHolder(View itemView){
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.cardViewUbicaciones);
            nombre= (TextView) itemView.findViewById(R.id.nombreUbicaciones);
            direccion= (TextView) itemView.findViewById(R.id.dirUbicaciones);
            latitud= (TextView) itemView.findViewById(R.id.latitudUbicacion);
            longitud= (TextView) itemView.findViewById(R.id.longitudUbicacion);
            imageView= (ImageView) itemView.findViewById(R.id.imageUbicaciones);
        }
    }
}
