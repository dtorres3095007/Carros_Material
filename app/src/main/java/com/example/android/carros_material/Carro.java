package com.example.android.carros_material;

/**
 * Created by android on 07/10/2017.
 */

public class Carro {

    private String placa;
    private int marca;
    private int modelo;
    private int color;
    private double precio;
    private int foto;
    private String id;
    public Carro(String placa, int marca, int modelo, int color, double precio, int foto) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.foto = foto;
    }
    public Carro(String placa, int marca, int modelo, int color, double precio,int foto,String id) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.id=id;
        this.foto = foto;
    }
    public Carro(){
    }
    public Carro(String id) {
    this.id=id;
    }
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void Guardar(){
        datos.guardarCarros(this);
    }
    public void Eliminar(){
        datos.eliminarCarro(this);
    }
    public void Modificar(){
        datos.ModificarCarro(this);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
