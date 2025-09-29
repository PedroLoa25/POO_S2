package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.modelo.Participante;

import java.util.List;

public interface ParticipanteServicioI {
    void save(Participante participante);
    List<Participante> findAll();
    Participante update(Participante participante);
    void delete(String dni);

    Participante findbyId(int index);
}
