/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Estado;
import Enums.FormaPago;
import Interfaces.Calculable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucia Ortiz
 */
public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formapago;
    private List<DetallePedido> detalles;
    private Usuario usuario;
    private Factura factura = new Factura();

    public Pedido(LocalDate fecha, Estado estado, FormaPago formapago, Usuario usuario, Long id, boolean eliminado, LocalDateTime CreatedAt) {
        super(id, eliminado, CreatedAt);
        this.fecha = fecha;
        this.estado = estado;
        this.formapago = formapago;
        this.detalles = new ArrayList<>();
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormapago() {
        return formapago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFormapago(FormaPago formapago) {
        this.formapago = formapago;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "Pedido{" + "fecha=" + fecha + ", estado=" + estado + ", total=" + total + ", formapago=" + formapago + ", detalles=" + detalles + ", usuario=" + usuario + ", factura=" + factura.getNumeroFiscal() + '}';
    }
    
    public void CalcularTotal(){
        total = 0;
        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }
    }
    
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = new DetallePedido(cantidad, producto, (long) detalles.size() + 1, false, LocalDateTime.now());
        detalles.add(detalle);
        CalcularTotal();
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
         DetallePedido detalle = null;
        for (DetallePedido det : detalles) {
            if(det.getProducto().getId().equals(producto.getId()))
                detalle = det;
        }
        return detalle;
    }
    
    
    
    
}
