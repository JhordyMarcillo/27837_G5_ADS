package espe.edu.ec.Proyecto_Analisis.repository;

import espe.edu.ec.Proyecto_Analisis.entity.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    List<Donacion> findByFechaDonacionBetween(LocalDate startDate, LocalDate endDate);
}