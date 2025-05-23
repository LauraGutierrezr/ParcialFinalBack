

package com.ecisalud.service;

import com.ecisalud.model.Cita;
import com.ecisalud.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CitaService {
    private final CitaRepository repo;

    public CitaService(CitaRepository repo) {
        this.repo = repo;
    }

    public Cita programarCita(Cita cita) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaCita = LocalDate.parse(cita.getFecha(), formatter);
        if (fechaCita.isBefore(LocalDate.now()) || cita.getNombreCompleto().isEmpty()) {
            cita.setEstado("Rechazada");
        } else {
            cita.setEstado("Confirmada");
        }
        return repo.save(cita);
    }

    public List<Cita> obtenerPorCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    public List<Cita> obtenerPorCorreoYEstado(String correo, String estado) {
        return repo.findByCorreoAndEstado(correo, estado);
    }

    public Cita cancelarCita(String id) {
        Cita cita = repo.findById(id).orElseThrow();
        cita.setEstado("Canceladaa");
        return repo.save(cita);
    }
}