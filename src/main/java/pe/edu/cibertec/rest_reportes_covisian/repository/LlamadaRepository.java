package pe.edu.cibertec.rest_reportes_covisian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Llamada;

@Repository
public interface LlamadaRepository extends JpaRepository<Llamada,Integer> {

    @Query(value = "SELECT * FROM Llamadas WHERE numero_orden = :numeroOrden", nativeQuery = true)
    Llamada findByOrden(@Param("numeroOrden") int numeroOrden);
}
