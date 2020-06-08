package com.example.material_design;

public class Carro {
    private String marca,color,placa,precio,velocidad,id;
    private int foto;


  public Carro(){}

    public Carro(String marca, String color, String placa, String precio, String velocidad, int foto,String id) {
        this.marca = marca;
        this.color = color;
        this.placa = placa;
        this.precio = precio;
        this.velocidad = velocidad;
        this.foto = foto;
        this.id=id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }
}
