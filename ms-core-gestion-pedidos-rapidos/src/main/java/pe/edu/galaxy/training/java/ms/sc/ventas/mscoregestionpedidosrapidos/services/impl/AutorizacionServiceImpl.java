package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.Orden;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.dto.OrdenDetalle;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.guia.GuiaService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.kakfa.OrdenProducerService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.producto.ProductoDTO;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.client.producto.ProductoService;
import pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionpedidosrapidos.services.service.AutorizacionService;

@Slf4j
@Service
public class AutorizacionServiceImpl implements AutorizacionService {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private OrdenProducerService ordenProducerService;

	@Autowired
	private GuiaService guiaService;

	@Override
	public Orden autorizar(Orden type) {
		try {
			System.out.println("TYPE : " + type);
			if (type.getTotal() <= 500.00) {
				System.out.println("ENTRE : " + type.getTotal());
				boolean rpta = true;
				for (OrdenDetalle dt : type.getItems()) {
					ProductoDTO p = productoService.findById(dt.getIdProducto());
					Integer rslt = p.getStock() - dt.getCantidad();
					if (rslt < 0)
						rpta = false;
				}

				if (rpta) {
					type.setEstado("2");
					Integer rptaKakfa = ordenProducerService.sendMessage(type);
					if (rptaKakfa == 1) {
						Integer rptaGuia = guiaService.insertarGuia(type);
						if (rptaGuia == 1) {
							for (OrdenDetalle det : type.getItems()) {
								ProductoDTO p = productoService.findById(det.getIdProducto());
								p.setStock(p.getStock() - det.getCantidad());
								productoService.update(p);
							}
						}
					}
				}else {
					type.setEstado("3");
				}

			} else {
				type.setEstado("3");
			}
			System.out.println("SALI X : ");
			return type;

		} catch (Exception e) {
			log.error("Autorization Error ", e.getMessage());
			type.setEstado("3");
			return type;
		}

	}

}
