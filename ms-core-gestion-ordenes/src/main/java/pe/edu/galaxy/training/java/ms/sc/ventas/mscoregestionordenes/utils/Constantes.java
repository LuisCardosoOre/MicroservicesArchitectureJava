package pe.edu.galaxy.training.java.ms.sc.ventas.mscoregestionordenes.utils;

public class Constantes {
	
	public final static String API_ORDEN = "/v1/ordenes";
	public final static String API_ORDEN_DETALLE = "/v1/ordenes/detalle";
	
	
	public final static Integer CODIGO_ERROR	=	-1; 
	public final static Integer CODIGO_ALERTA	=	 0;
	public final static Integer CODIGO_EXITO	=	1;
	
	public final static String MSG_ERROR_CONS	=	"Error al consultar"; 
	public final static String MSG_ALERTA_CONS	=	"Alerta al consultar";
	public final static String MSG_EXITO_CONS	=	"Exito al consultar";
	
	public final static String URL_CLIENTE = "ms-core-gestion-clientes";
	public final static String PATH_CLIENTE = "/v1/clientes/";
	public final static String URL_PRODUCTO = "ms-core-gestion-productos";
	public final static String PATH_PRODUCTO = "/v1/productos/";
	public final static String URL_PRODUCER = "ms-core-gestion-ordenes-producer";
	public final static String PATH_PRODUCER = "/v1/ordenes-producer";
	public final static String URL_GUIA = "ms-core-gestion-guias";
	public final static String PATH_GUIA = "/v1/guias/";
	public final static String URL_PEDIDO_AUTORIZACION = "ms-core-gestion-pedidos-autorizacion";
	public static final String PATH_PEDIDO_AUTORIZACION="/v1/pedidos-autorizacion";
	public final static String URL_GUIA_REDIS = "ms-core-gestion-guias-redis";
	public final static String PATH_GUIA_REDIS = "/v1/guias-redis/";
	public final static String ERROR_NOT_FOUND = "NOT FOUND";
	
	
	public final static String DOCUMENTACION_TITULO = "Ordenes API";
	public final static String DOCUMENTACION_DESCRIPCION = "Documentacion de Ordenes";
	public final static String DOCUMENTACION_VERSION = "version 1.0.0";
	public final static String DOCUMENTACION_LICENCIA = "Apache 2.0";
	public final static String DOCUMENTACION_LICENCIA_URL = "http://springdoc.org";
	public final static String DOCUMENTACION_CREADOR_CORREO = "luis_93_13@hotmail.com";
	public final static String DOCUMENTACION_CREADOR_URL = "https://github.com/LuisCardosoOre";
}
