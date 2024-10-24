package pe.edu.cibertec.rest_reportes_covisian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Memorandum;

public interface MemorandumRepository extends JpaRepository<Memorandum,Integer> {
}
