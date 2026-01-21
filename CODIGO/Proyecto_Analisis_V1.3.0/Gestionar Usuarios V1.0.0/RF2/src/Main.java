import donaciones.controller.DonacionController;
import donaciones.service.DonacionService;
import donaciones.view.DonacionView;

public class Main {
    public static void main(String[] args) {
        DonacionView vista = new DonacionView();
        DonacionService servicio = new DonacionService();
        new DonacionController(vista, servicio);
    }
}
