package grupo4;

public class Productos {
public int id, stock;
public double precio;
public String descripcion;
public Productos(int id, int stock, double precio, String descripcion) {
	super();
	this.id = id;
	this.stock = stock;
	this.precio = precio;
	this.descripcion = descripcion;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
