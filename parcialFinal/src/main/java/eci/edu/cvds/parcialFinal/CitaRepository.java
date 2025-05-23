package eci.edu.cvds.repository;

import eci.edu.cvds.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {
    List<Cita> findByCorreo(String correo);
    List<Cita> findByCorreoAndEstado(String correo, String estado);
}
