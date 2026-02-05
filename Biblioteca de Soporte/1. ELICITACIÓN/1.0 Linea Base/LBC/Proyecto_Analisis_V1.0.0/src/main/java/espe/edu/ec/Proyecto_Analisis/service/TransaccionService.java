package espe.edu.ec.Proyecto_Analisis.service;

import espe.edu.ec.Proyecto_Analisis.entity.Donacion;
import espe.edu.ec.Proyecto_Analisis.entity.Egreso;
import espe.edu.ec.Proyecto_Analisis.repository.DonacionRepository;
import espe.edu.ec.Proyecto_Analisis.repository.EgresoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import espe.edu.ec.Proyecto_Analisis.repository.UsuarioRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final DonacionRepository donacionRepository;
    private final EgresoRepository egresoRepository;


    @Transactional
    public void guardarDonacion(Donacion donacion) {
        if (donacion.getFechaDonacion() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        donacionRepository.save(donacion);
    }

    public List<Donacion> obtenerTodasDonaciones() {
        return donacionRepository.findAll();
    }


    @Transactional
    public void guardarEgreso(Egreso egreso) {
        if (egreso.getMonto() == null) {
            throw new IllegalArgumentException("El monto es obligatorio");
        }
        egresoRepository.save(egreso);
    }

    public List<Egreso> obtenerTodosEgresos() {
        return egresoRepository.findAll();
    }
}