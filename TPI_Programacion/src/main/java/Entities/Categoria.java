/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo Insaurralde
 */
public class Categoria extends Base{
    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(Long id, boolean eliminado, LocalDateTime CreatedAt,String nombre, String descripcion) {
        super(id, eliminado, CreatedAt);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categoria{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", productos=" + productos + '}';
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    
    }
    
}

