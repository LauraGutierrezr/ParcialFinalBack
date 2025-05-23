package eci.edu.cvds.service;

import eci.edu.cvds.model.Cita;
import eci.edu.cvds.repository.CitaRepository;
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
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(cita.getFecha(), fmt);
        if (fecha.isBefore(LocalDate.now()) || cita.getNombreCompleto() == null || cita.getNombreCompleto().isEmpty()) {
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
        Cita cita = repo.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("Cancelada");
        return repo.save(cita);
    }
}
