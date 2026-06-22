/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;

/**
 *
 * @author Hugo Insaurralde
 */
public class DetallePedido extends Base{
    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto, Long id, boolean eliminado, LocalDateTime CreatedAt) {
        super(id, eliminado, CreatedAt);
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
        
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "cantidad=" + cantidad + ", subtotal=" + subtotal + ", producto=" + producto + '}';
    }
    
    private double calcularSubtotal(){
    double subtotal = cantidad * producto.getPrecio();
    return subtotal;
    } 
}
