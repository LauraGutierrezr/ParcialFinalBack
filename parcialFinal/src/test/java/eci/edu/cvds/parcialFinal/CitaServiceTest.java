package eci.edu.cvds.service;

import eci.edu.cvds.model.Cita;
import eci.edu.cvds.repository.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CitaServiceTest {

    private CitaRepository repo;
    private CitaService service;

    @BeforeEach
    void init() {
        repo = Mockito.mock(CitaRepository.class);
        service = new CitaService(repo);
        when(repo.save(any(Cita.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void programar_confirmada() {
        Cita c = new Cita(); c.setNombreCompleto("A"); c.setFecha("31-12-2099");
        Cita out = service.programarCita(c);
        assertEquals("Confirmada", out.getEstado());
    }

    @Test
    void programar_rechazada_por_nombre() {
        Cita c = new Cita(); c.setNombreCompleto(""); c.setFecha("31-12-2099");
        Cita out = service.programarCita(c);
        assertEquals("Rechazada", out.getEstado());
    }


}
