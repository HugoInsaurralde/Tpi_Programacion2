/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import Enums.Estado;
import Enums.FormaPago;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.ValidacionException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo Insaurralde
 */
public class PedidoService {
    private List<Pedido> pedidos;

    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }

    public Pedido iniciarPedido(Usuario usuario, FormaPago formaPago) throws ValidacionException {
        if (usuario == null || usuario.isEliminado()) {
            throw new ValidacionException("Error: No se puede crear un pedido sin un usuario valido y activo.");
        }

        Long nuevoId = (long) (pedidos.size() + 1);
        Pedido nuevoPedido = new Pedido(LocalDate.now(), Estado.PENDIENTE, formaPago, usuario, nuevoId, false, LocalDateTime.now());
        pedidos.add(nuevoPedido);
        usuario.agregarPedido(nuevoPedido);
        return nuevoPedido;
    }

    public void agregarDetalle(Pedido pedido, Producto producto, int cantidad) throws ValidacionException {
        if (cantidad <= 0) {
            throw new ValidacionException("Error: La cantidad debe ser mayor a 0.");
        }
        if (producto.getStock() < cantidad) {
            throw new ValidacionException("Error: Stock insuficiente. Solo quedan " + producto.getStock() + " unidades de " + producto.getNombre());
        }
        producto.setStock(producto.getStock() - cantidad);
        pedido.addDetallePedido(cantidad, producto);
        pedido.CalcularTotal();
    }

    public void listarPedidos() {
        boolean hayActivos = false;

        System.out.println("--- LISTADO DE PEDIDOS ---");
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                System.out.println("Pedido ID: " + p.getId() + " | Fecha: " + p.getFecha() + 
                   " | Usuario: " + p.getUsuario().getNombre() + " " + p.getUsuario().getApellido() + 
                   " | Estado: " + p.getEstado() + " | Total: $" + p.getTotal() + 
                   " | Factura: " + p.getFactura().getNumeroFiscal());
            }
            hayActivos = true;
        }
        if (!hayActivos) {
            System.out.println("No hay pedidos cargados en el sistema.");
        }
    }
    public Pedido buscarPedidoPorId(Long id) throws EntidadNoEncontradaException {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new EntidadNoEncontradaException("Error: No se encontro un pedido activo con el ID " + id);
    }

    public void actualizarPedido(Long id, Estado nuevoEstado, FormaPago nuevaFormaPago) throws EntidadNoEncontradaException {
        Pedido p = buscarPedidoPorId(id);

        if (nuevoEstado != null) {
            p.setEstado(nuevoEstado);
        }
        if (nuevaFormaPago != null) {
            p.setFormapago(nuevaFormaPago);
        }
        System.out.println("¡exito! El pedido fue actualizado correctamente.");
    }

    public void eliminarPedido(Long id) throws EntidadNoEncontradaException {
        Pedido p = buscarPedidoPorId(id);
        p.setEliminado(true);
        
        for (int i = 0; i < p.getDetalles().size(); i++) {
            p.getDetalles().get(i).setEliminado(true);
        }
        
        System.out.println("¡exito! Pedido y sus detalles eliminados correctamente.");
    }
}