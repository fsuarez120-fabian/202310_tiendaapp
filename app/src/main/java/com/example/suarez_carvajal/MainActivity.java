package com.example.suarez_carvajal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos = new ArrayList<>();;
    private RecyclerView rvListadoProductos;
    private AdaptadorPersonalizado miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));

        //cargarDatos();

        rvListadoProductos = findViewById(R.id.rv_listado_productos);

        miAdaptador = new AdaptadorPersonalizado(listaPrincipalProductos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {

                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto", miProducto);
                startActivity(intent);
            }

            @Override
            public void onItemBtnEliminaClick(Producto miProducto, int posicion) {

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(miProducto.getKey()).delete();

                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInfomacion(listaPrincipalProductos);
            }
        });

        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPrincipalProductos.clear();
        cargarDatos();
    }

    public void cargarDatos() {

        //guardarlo en FireStore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){

                    for (DocumentSnapshot myDocumento : task.getResult()){
                        Producto myProduct = myDocumento.toObject(Producto.class);
                        myProduct.setKey(myDocumento.getId());
                        listaPrincipalProductos.add(myProduct);
                    }
                    miAdaptador.setListadoInfomacion(listaPrincipalProductos);
                }else{
                    Toast.makeText(MainActivity.this, "No se pudo conectar con el servidor.", Toast.LENGTH_SHORT).show();
                }
            }
        });




        /*// Crear productos
        Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrlImagen("https://pcsystemcolombia.com/wp-content/uploads/2020/11/Todo-En-Uno-HP-200-G4-22-Core-i3.png");

        Producto producto2 = new Producto("Teclado DEll", 250000.0, "https://img2.freepng.es/20180204/jke/kisspng-computer-keyboard-das-keyboard-switch-usb-numeric-keyboard-png-image-5a77ac2241e081.8561166015177922902698.jpg");
        Producto producto3 = new Producto("Mouse", 50000.0, "https://static.vecteezy.com/system/resources/previews/001/203/999/non_2x/mouse-computer-png.png");
        //inicializar el arraylist
        listaPrincipalProductos = new ArrayList<>();
        //agregar los productos al arraylist
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);*/

    }


    public void clickCerrarSesion(View view) {

        /*SharedPreferences miPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = miPreferencias.edit();
        myEditor.clear();
        myEditor.apply();*/

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


    public void clickAgregarProducto(View view) {
        startActivity(new Intent(this, FormularioActivity.class));
    }

}