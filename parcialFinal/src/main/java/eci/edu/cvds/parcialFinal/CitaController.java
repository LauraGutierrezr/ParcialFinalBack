package eci.edu.cvds.controller;

import eci.edu.cvds.model.Cita;
import eci.edu.cvds.service.CitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping("/programar")
    public Cita programar(@RequestBody Cita cita) {
        return service.programarCita(cita);
    }

    @GetMapping("/{correo}")
    public List<Cita> historial(@PathVariable String correo) {
        return service.obtenerPorCorreo(correo);
    }

    @GetMapping("/{correo}/{estado}")
    public List<Cita> historialPorEstado(@PathVariable String correo, @PathVariable String estado) {
        return service.obtenerPorCorreoYEstado(correo, estado);
    }

    @PostMapping("/cancelar/{id}")
    public Cita cancelar(@PathVariable String id) {
        return service.cancelarCita(id);
    }
}
