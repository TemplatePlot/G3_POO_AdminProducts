package gui;

public class Products {

	private String id, prod, tipo;
	private int stock;
	private double prec;
	private double costo;
	
	public Products(String id, String prod, String tipo, int stock, double prec, double costo) {
		this.id = id;
		this.prod = prod;
		this.tipo = tipo;
		this.stock = stock;
		this.prec = prec;
		this.costo = costo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrec() {
		return prec;
	}
	public void setPrec(double prec) {
		this.prec = prec;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
}
