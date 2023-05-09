package com.example.suarez_carvajal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormularioActivity extends AppCompatActivity {

    EditText etNombre, etPrecio, etUrlImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etNombre = findViewById(R.id.et_nombre_formulario);
        etPrecio = findViewById(R.id.et_precio_formulario);
        etUrlImagen = findViewById(R.id.et_imgen_formulario);

    }

    public void clickGuardar(View view){

        String nombre = etNombre.getText().toString();
        Double precio = Double.parseDouble(etPrecio.getText().toString());
        String urlImagen = etUrlImagen.getText().toString();

        Producto newProduct = new Producto(nombre,precio,urlImagen);

        //guardarlo en FireStore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").add(newProduct);

        //guardarlo en RealTime
        Retrofit myRetrofit = TiendaAppService.obtenerInstancia();
        ProductoAPI myProductoAPI = myRetrofit.create(ProductoAPI.class);
        myProductoAPI.agregar(newProduct).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(FormularioActivity.this, "SE GUARDO EL PRODUCTO EN FIREBASE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });



        finish();
        Toast.makeText(this, "SE GUARDO EL PRODUCTO EN FIREBASE", Toast.LENGTH_SHORT).show();
    }
}