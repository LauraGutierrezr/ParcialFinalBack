package eci.edu.cvds.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eci.edu.cvds.model.Cita;
import eci.edu.cvds.service.CitaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CitaController.class)
class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CitaService service;

    @Test
    @DisplayName("POST /api/citas/programar → 200 OK")
    void programarCita_ok() throws Exception {
        Cita dummy = new Cita();
        dummy.setEstado("Confirmada");
        when(service.programarCita(any(Cita.class))).thenReturn(dummy);

        mockMvc.perform(post("/api/citas/programar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dummy)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.estado").value("Confirmada"));
    }

    @Test
    @DisplayName("GET /api/citas/{correo} → []")
    void historial_ok() throws Exception {
        when(service.obtenerPorCorreo("u@x.com")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/citas/u@x.com"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("GET /api/citas/{correo}/{estado} → []")
    void historialPorEstado_ok() throws Exception {
        when(service.obtenerPorCorreoYEstado("u@x.com","Confirmada"))
            .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/citas/u@x.com/Confirmada"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }

}
