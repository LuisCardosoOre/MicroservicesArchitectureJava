package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.service;

import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;

public interface AutorizacionService {

	Orden autorizar(Orden type);
}
