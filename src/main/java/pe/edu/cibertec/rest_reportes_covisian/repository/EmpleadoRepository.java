package pe.edu.cibertec.rest_reportes_covisian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
