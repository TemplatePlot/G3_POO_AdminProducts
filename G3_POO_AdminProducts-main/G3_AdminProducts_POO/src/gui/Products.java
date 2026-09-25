package gui;

public class Products {

	public String id, prod, tipo;
	public int stock;
	public double prec;
	public Products(String id, String prod, String tipo, int stock, double prec) {
		super();
		this.id = id;
		this.prod = prod;
		this.tipo = tipo;
		this.stock = stock;
		this.prec = prec;
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
	
	
}
