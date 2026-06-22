/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Usuario;
import Enums.Rol;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo Insaurralde
 */
public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    public void crearUsuario(String nombre, String apellido, String email, String celular, String contrasenia, Rol rol) throws ValidacionException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidacionException("Error: El mail no puede estar vacio.");
        }
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && !u.isEliminado()) {
                throw new ValidacionException("Error: Ya existe un usuario activo registrado con el mail '" + email + "'.");
            }
        }
        Long nuevoId = (long) (usuarios.size() + 1);
        Usuario nuevoUsuario = new Usuario(nombre, apellido, email, celular, contrasenia, rol, nuevoId, false, LocalDateTime.now());
        usuarios.add(nuevoUsuario);
        
        System.out.println("¡exito! Usuario creado con el ID: " + nuevoId);
    }

    public void listarUsuarios() {
        boolean hayActivos = false;
        
        System.out.println("--- LISTADO DE USUARIOS ---");
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() + " " + u.getApellido() + " | Mail: " + u.getEmail() + " | Rol: " + u.getRol());
                hayActivos = true;
            }
        }
        if (!hayActivos) {
            System.out.println("No hay usuarios cargados.");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("Error: No se encontro un usuario activo con el ID " + id);
    }
    public void editarUsuario(Long id, String nuevoNombre, String nuevoApellido, String nuevoEmail, String nuevoCelular, String nuevaContrasenia, Rol nuevoRol) throws EntidadNoEncontradaException, ValidacionException {
        
        Usuario u = buscarUsuarioPorId(id);

        if (nuevoEmail != null && !nuevoEmail.trim().isEmpty()) {
            for (Usuario user : usuarios) {
                if (!user.getId().equals(id) && user.getEmail().equalsIgnoreCase(nuevoEmail) && !user.isEliminado()) {
                    throw new ValidacionException("Error: El mail '" + nuevoEmail + "' ya esta en uso por otro usuario.");
                }
            }
            u.setEmail(nuevoEmail);
        }

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) { u.setNombre(nuevoNombre); }
        if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) { u.setApellido(nuevoApellido); }
        if (nuevoCelular != null && !nuevoCelular.trim().isEmpty()) { u.setCelular(nuevoCelular); }
        if (nuevaContrasenia != null && !nuevaContrasenia.trim().isEmpty()) { u.setContrasenia(nuevaContrasenia); }
        if (nuevoRol != null) { u.setRol(nuevoRol); }

        System.out.println("¡exito! Usuario actualizado correctamente.");
    }

    public void eliminarUsuario(Long id) throws EntidadNoEncontradaException {
        Usuario u = buscarUsuarioPorId(id);
        
        u.setEliminado(true);
        System.out.println("¡exito! Usuario eliminado correctamente.");
    }
}
