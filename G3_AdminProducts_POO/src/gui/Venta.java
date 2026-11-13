package gui;

public class Venta {
//Creado especificamente para almacenar las ventas hechas durante el proceso
	private final int numeroBoleta; 
	private final String idProducto;
	private final String producto;
	private final int cantidad;
	private final double precioVenta;
	private final double costo;
	
	public Venta(int numeroBoleta, String idProducto, String producto, int cantidad, double precioVenta,
			double costo) {
		super();
		this.numeroBoleta = numeroBoleta;
		this.idProducto = idProducto;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.costo = costo;
	}

	public int getNumeroBoleta() {
		return numeroBoleta;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public String getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public double getCosto() {
		return costo;
	}

	public double getGanancia() { //Getter exclusivo para la ganancia de una venta
		return (precioVenta - costo) * cantidad;
	}	
}
