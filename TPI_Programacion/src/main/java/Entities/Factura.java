/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;

public class Factura extends Base {
    private String tipoComprobante;
    private String numeroFiscal;

    public Factura() {
        super(0L, false, LocalDateTime.now()); 
        
        this.tipoComprobante = "Consumidor Final";
        this.numeroFiscal = "0001-" + (int)(Math.random() * 100000); 
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumeroFiscal() {
        return numeroFiscal;
    }

    public void setNumeroFiscal(String numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    @Override
    public String toString() {
        return "Factura{" + "tipoComprobante=" + tipoComprobante + ", numeroFiscal=" + numeroFiscal + '}';
    }
}