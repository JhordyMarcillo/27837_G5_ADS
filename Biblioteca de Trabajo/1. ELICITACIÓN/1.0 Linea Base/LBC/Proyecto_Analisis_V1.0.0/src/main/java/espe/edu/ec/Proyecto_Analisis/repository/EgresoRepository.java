package espe.edu.ec.Proyecto_Analisis.repository;

import espe.edu.ec.Proyecto_Analisis.entity.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, Long> {

    List<Egreso> findByFechaEgresoBetween(LocalDate startDate, LocalDate endDate);
}