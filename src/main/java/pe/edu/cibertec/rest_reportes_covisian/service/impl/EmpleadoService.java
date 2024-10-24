package pe.edu.cibertec.rest_reportes_covisian.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Empleado;
import pe.edu.cibertec.rest_reportes_covisian.repository.EmpleadoRepository;
import pe.edu.cibertec.rest_reportes_covisian.service.IEmpleadoService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmpleadoService implements IEmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listaEmpleados() {
        return empleadoRepository.findAll();
    }
}
