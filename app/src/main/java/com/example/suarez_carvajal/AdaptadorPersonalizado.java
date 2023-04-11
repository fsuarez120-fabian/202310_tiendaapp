package com.example.suarez_carvajal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    private ArrayList<Producto> listadoInfomacion;
    private OnItemClickListener onItemClickListener;

    public void setListadoInfomacion(ArrayList<Producto> listadoInfomacion) {
        this.listadoInfomacion = listadoInfomacion;
        notifyDataSetChanged();
    }

    public AdaptadorPersonalizado(ArrayList<Producto> listadoInfomacion) {
        this.listadoInfomacion = listadoInfomacion;
        this.onItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_productos,parent,false);
        return new ViewHolder(miView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.ViewHolder holder, int position) {
        Producto miProducto = listadoInfomacion.get(position);
        holder.enlazar(miProducto);
    }

    @Override
    public int getItemCount() {
        return listadoInfomacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre, tvPrecio;
        private ImageView ivProducto;
        private Button btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre= itemView.findViewById(R.id.tv_item_nombre);
            tvPrecio = itemView.findViewById(R.id.tv_item_precio);
            ivProducto = itemView.findViewById(R.id.iv_item_imagen);
            btnEliminar = itemView.findViewById(R.id.btn_item_eliminar);

        }

        public void enlazar(Producto miProducto){
            tvNombre.setText(miProducto.getNombre());
            tvPrecio.setText(miProducto.getPrecio().toString());
            Picasso.get()
                    .load(miProducto.getUrlImagen())
                    .error(R.drawable.ic_launcher_background)
                    .into(ivProducto);

            if(onItemClickListener != null){

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(miProducto,getAdapterPosition());
                    }
                });

                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemBtnEliminaClick(miProducto,getAdapterPosition());
                    }
                });
            }



        }
    }


    public interface OnItemClickListener{
        void onItemClick(Producto miProducto , int posicion);
        void onItemBtnEliminaClick(Producto miProducto , int posicion);
    }

}
