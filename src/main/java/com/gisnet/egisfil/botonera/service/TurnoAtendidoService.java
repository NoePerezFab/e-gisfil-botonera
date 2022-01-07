
package com.gisnet.egisfil.botonera.service;

import com.gisnet.egisfil.domain.TurnoAtendido;
import java.util.List;
import java.util.Optional;

public interface TurnoAtendidoService {
    Optional<TurnoAtendido> findOne(String id);
    List<TurnoAtendido> findAll();
    TurnoAtendido create(TurnoAtendido turnoAtendido);
    TurnoAtendido update(TurnoAtendido turnoAtendido);
}
