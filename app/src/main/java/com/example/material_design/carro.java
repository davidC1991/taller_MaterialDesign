package com.example.material_design;

public class carro {
    private String marca,color,placa;
    private int precio,velocidad,foto;

    public carro() {
    }

    public carro(String marca, String color, String placa, int precio, int velocidad, int foto) {
        this.marca = marca;
        this.color = color;
        this.placa = placa;
        this.precio = precio;
        this.velocidad = velocidad;
        this.foto = foto;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
