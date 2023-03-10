package Modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Equipo {
    private long codigo;
    private String descripcion;
    private long precioArriendoDia;
    private EstadoEquipo estado;
    private ArrayList <DetalleArriendo> detalles;

    public Equipo(long codigo, String descripcion, long precioArriendoDia) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioArriendoDia = precioArriendoDia;
        this.estado = EstadoEquipo.OPERATIVO;
        this.detalles = new ArrayList<>();
    }

    public long getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getPrecioArriendoDia() {
        return precioArriendoDia;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }

    public void setEstado(EstadoEquipo estado) {
        this.estado = estado;
    }
    public void addDetalleArriendo (DetalleArriendo detalle){
        detalles.add(detalle);
    }

    public boolean isArrendado(){
        if(detalles.isEmpty()){
            return false;
        }
        int i=(detalles.size()-1);
        return detalles.get(i).getArriendo().getEstado() == (EstadoArriendo.ENTREGADO);
    }
}

