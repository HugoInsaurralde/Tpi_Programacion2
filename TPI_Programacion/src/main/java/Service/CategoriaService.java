/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Categoria;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo Insaurralde
 */
public class CategoriaService {
    private List<Categoria> categorias;

    public CategoriaService() {
        this.categorias = new ArrayList<>();
    }

    public void crearCategoria(String nombre, String descripcion) throws ValidacionException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("Error: El nombre de la categoria no puede estar vacio.");
        }
        for (Categoria cat : categorias) {
            if (cat.getNombre().equalsIgnoreCase(nombre) && !cat.isEliminado()) {
                throw new ValidacionException("Error: Ya existe una categoria activa con el nombre '" + nombre + "'.");
            }
        }

        Long nuevoId = (long) (categorias.size() + 1);

        Categoria nuevaCat = new Categoria(nuevoId, false, LocalDateTime.now(), nombre, descripcion);
        categorias.add(nuevaCat);
        System.out.println("¡exito! Categoria creada con el ID: " + nuevoId);
    }

    public void listarCategorias() {
        boolean hayActivas = false;
        
        System.out.println("--- LISTADO DE CATEGORIAS ---");
        for (Categoria cat : categorias) {
            if (!cat.isEliminado()) { 
                System.out.println("ID: " + cat.getId() + " | Nombre: " + cat.getNombre() + " | Desc: " + cat.getDescripcion());
                hayActivas = true;
            }
        }
        if (!hayActivas) {
            System.out.println("No hay categorias cargadas.");
        }
    }
    
    public Categoria buscarCategoriaPorId(Long id) throws EntidadNoEncontradaException {
        for (Categoria cat : categorias) {
            if (cat.getId().equals(id) && !cat.isEliminado()) {
                return cat;
            }
        }
        throw new EntidadNoEncontradaException("Error: No se encontro una categoría activa con el ID " + id);
    }

    public void editarCategoria(Long id, String nuevoNombre, String nuevaDescripcion) throws EntidadNoEncontradaException, ValidacionException {
        Categoria cat = buscarCategoriaPorId(id);

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            for (Categoria c : categorias) {
                if (!c.getId().equals(id) && c.getNombre().equalsIgnoreCase(nuevoNombre) && !c.isEliminado()) {
                    throw new ValidacionException("Error: Ya existe otra categoria con el nombre '" + nuevoNombre + "'.");
                }
            }
            cat.setNombre(nuevoNombre);
        }

        if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
            cat.setDescripcion(nuevaDescripcion);
        }
        
        System.out.println("¡exito! Categoria actualizada correctamente.");
    }

    public void eliminarCategoria(Long id) throws EntidadNoEncontradaException {
        Categoria cat = buscarCategoriaPorId(id);
        
        cat.setEliminado(true);
        System.out.println("¡exito! Categoria eliminada correctamente.");
    }
}