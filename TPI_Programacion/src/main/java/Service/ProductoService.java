/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Categoria;
import Entities.Producto;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo Insaurralde
 */
public class ProductoService {
    private List<Producto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    public void crearProducto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) throws ValidacionException {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("Error: El nombre del producto no puede estar vacio.");
        }
        
        if (precio < 0) {
            throw new ValidacionException("Error: El precio no puede ser negativo.");
        }
        
        if (stock < 0) {
            throw new ValidacionException("Error: El stock no puede ser negativo.");
        }
        
        if (categoria == null || categoria.isEliminado()) {
            throw new ValidacionException("Error: La categoria seleccionada no es valida o esta eliminada.");
        }

        Long nuevoId = (long) (productos.size() + 1);
        Producto nuevoProd = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria, nuevoId, false, LocalDateTime.now());
        productos.add(nuevoProd);
        System.out.println("¡exito! Producto creado con el ID: " + nuevoId);
    }

    public void listarProductos() {
        boolean hayActivos = false;
        
        System.out.println("--- LISTADO DE PRODUCTOS ---");
        for (Producto prod : productos) {
            if (!prod.isEliminado()) {
                System.out.println("ID: " + prod.getId() + " | Nombre: " + prod.getNombre() + " | Precio: $" + prod.getPrecio() + " | Stock: " + prod.getStock() + " | Categoria: " + prod.getCategoria().getNombre());
                hayActivos = true;
            }
        }
        if (!hayActivos) {
            System.out.println("No hay productos cargados.");
        }
    }
    public Producto buscarProductoPorId(Long id) throws EntidadNoEncontradaException {
        for (Producto prod : productos) {
            if (prod.getId().equals(id) && !prod.isEliminado()) {
                return prod;
            }
        }
        throw new EntidadNoEncontradaException("Error: No se encontro un producto activo con el ID " + id);
    }

    public void editarProducto(Long id, String nuevoNombre, double nuevoPrecio, int nuevoStock, Categoria nuevaCategoria) throws EntidadNoEncontradaException, ValidacionException {
        Producto prod = buscarProductoPorId(id);
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            prod.setNombre(nuevoNombre);
        }
        
        if (nuevoPrecio < 0) {
            throw new ValidacionException("Error: El nuevo precio no puede ser negativo.");
        } else {
            prod.setPrecio(nuevoPrecio);
        }
        if (nuevoStock < 0) {
             throw new ValidacionException("Error: El nuevo stock no puede ser negativo.");
        } else {
             prod.setStock(nuevoStock);
        }
        if (nuevaCategoria != null && !nuevaCategoria.isEliminado()) {
            prod.setCategoria(nuevaCategoria);
        }

        System.out.println("¡exito! Producto actualizado correctamente.");
    }

    public void eliminarProducto(Long id) throws EntidadNoEncontradaException {
        Producto prod = buscarProductoPorId(id);
        
        prod.setEliminado(true);
        System.out.println("¡exito! Producto eliminado correctamente.");
    }
}
