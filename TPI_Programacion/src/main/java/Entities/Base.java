/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;


/**
 *
 * @author Lucia Ortiz
 */
public abstract class Base {
    private Long id;
    private boolean eliminado;
    private LocalDateTime CreatedAt;

    public Base(Long id, boolean eliminado, LocalDateTime CreatedAt) {
        this.id = id;
        this.eliminado = eliminado;
        this.CreatedAt = CreatedAt;
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    @Override
    public abstract String toString();
    
    
}
