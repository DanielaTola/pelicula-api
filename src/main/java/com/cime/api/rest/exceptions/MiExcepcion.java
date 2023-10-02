package com.cime.api.rest.exceptions;

public class MiExcepcion extends Exception {

	
    private int codigo;
    private String descripcion;

    // Constructor que toma un mensaje de error, código y descripción
    public MiExcepcion(String mensaje, int codigo, String descripcion) {
        super(mensaje);
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    // Obtener el código
    public int getCodigo() {
        return codigo;
    }

    // Obtener la descripción
    public String getDescripcion() {
        return descripcion;
    }
}
