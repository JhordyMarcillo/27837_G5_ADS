package donaciones.service;

import donaciones.model.Donacion;

import java.util.ArrayList;

public class DonacionService {

    private ArrayList<Donacion> lista = new ArrayList<>();

    public void guardarDonacion(Donacion d) {
        lista.add(d);
    }

    public ArrayList<Donacion> obtenerDonaciones() {
        return lista;
    }
}
