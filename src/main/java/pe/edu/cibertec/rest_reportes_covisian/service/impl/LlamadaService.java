package pe.edu.cibertec.rest_reportes_covisian.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Llamada;
import pe.edu.cibertec.rest_reportes_covisian.repository.LlamadaRepository;
import pe.edu.cibertec.rest_reportes_covisian.service.ILlamadaService;

@RequiredArgsConstructor
@Service
public class LlamadaService implements ILlamadaService {
    private final LlamadaRepository llamadaRepository;

    @Override
    public Llamada llamadaPorOrden(int orden) {
        return llamadaRepository.findByOrden(orden);
    }
}
