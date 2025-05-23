package com.ecisalud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "citas")
public class Cita {
    @Id
    private String id;
    private String nombreCompleto;
    private String cedula;
    private String correo;
    private String especialidad;
    private String doctor;
    private String fecha; 
    private String ubicacion;
    private String estado; // que va a ser confirmada - Rechazada - Cancelada

}