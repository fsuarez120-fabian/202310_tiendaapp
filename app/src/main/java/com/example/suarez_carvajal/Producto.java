package com.example.suarez_carvajal;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Producto implements Serializable {

    private String id;
    private String nombre;
    private Double precio;
    private String urlImagen;

    public Producto(){}

    public Producto(String nombre, Double precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }
    //@Exclude
    public String getNombre() {
        return nombre;
    }
    //@Exclude
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    @PropertyName("url_image")
    public String getUrlImagen() {
        return urlImagen;
    }
    @PropertyName("url_image")
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
