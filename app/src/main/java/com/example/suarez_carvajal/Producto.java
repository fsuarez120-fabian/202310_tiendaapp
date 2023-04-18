package com.example.suarez_carvajal;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private Double precio;
    private String urlImagen;

    //para conectar Firebase se debe tener un constructor vacio y los gettes y setters
    public Producto(){}

    public Producto(String nombre, Double precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    //@Exclude es para ignorar en Firebase pero debe ser en los getters y setter

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    @PropertyName("url_imagen")
    public String getUrlImagen() {
        return urlImagen;
    }
    @PropertyName("url_imagen")
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
