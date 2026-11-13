package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Compras extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField comprarId;
	private JTextField comprarCant;
	private ArrayList<Products> lista; // Se comparte
	private ArrayList<ItemCanasta> canasta = new ArrayList<>();
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	private final ArrayList<Venta> ventas = new ArrayList<>();
	private int contadorBoletas = 0;

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public Compras(ArrayList<Products> lista) {

		this.lista = lista;
		setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(21, 157, 405, 143);
		add(textArea);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(21, 25, 44, 12);
		add(lblNewLabel_2);

		comprarId = new JTextField();
		comprarId.setColumns(10);
		comprarId.setBounds(21, 46, 96, 18);
		add(comprarId);

		JLabel lblNewLabel_2_1 = new JLabel("Cantidad a comprar:");
		lblNewLabel_2_1.setBounds(21, 75, 103, 12);
		add(lblNewLabel_2_1);

		comprarCant = new JTextField();
		comprarCant.setColumns(10);
		comprarCant.setBounds(21, 98, 96, 18);
		add(comprarCant);

		JButton btnCanasta = new JButton("Agregar");
		btnCanasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducs();
			}
		});
		btnCanasta.setBounds(326, 25, 84, 20);
		add(btnCanasta);

		JButton btnEliminarCanasta = new JButton("Eliminar");
		btnEliminarCanasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idEliminar = comprarId.getText().trim();
				canasta.removeIf(item -> item.producto.getId().equals(idEliminar));
				mostrarCanasta();
				comprarId.setText("");
			}
		});
		btnEliminarCanasta.setBounds(326, 56, 84, 20);
		add(btnEliminarCanasta);

		JButton GenerarBoleta = new JButton("Generar Boleta");
		GenerarBoleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarBoleta();
			}
		});
		GenerarBoleta.setBounds(326, 110, 119, 20);
		add(GenerarBoleta);

		JButton MostrarCanasta = new JButton("Mostrar Canasta");
		MostrarCanasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCanasta();
			}
		});
		MostrarCanasta.setBounds(21, 127, 119, 20);
		add(MostrarCanasta);

	}

	private void agregarProducs() {
		String idbuscar = comprarId.getText().trim();

		int cant;
		try {
			cant = Integer.parseInt(comprarCant.getText().trim());
		} catch (NumberFormatException ex) {
			textArea.setText("Error: la cantidad debe ser un número entero.\n");
			return;
		}
		if (cant <= 0) {
			textArea.setText("Error: la cantidad debe ser mayor que cero.\n");
			return;
		}

		Products producto = buscarProducto(idbuscar);
		if (producto == null) {
			textArea.setText("Error: no existe un producto con ID " + idbuscar + ".\n");
			return;
		}

		int disponible = producto.getStock() - cantidadEnCanasta(idbuscar);
		if (cant > disponible) {
			textArea.setText("Error: stock insuficiente. Disponible: " + disponible + "\n");
			return;
		}

		canasta.add(new ItemCanasta(producto, cant));
		mostrarCanasta();
		comprarId.setText("");
		comprarCant.setText("");
	}

	private void mostrarCanasta() {
		StringBuilder sb = new StringBuilder("--- Canasta ---\n");
		for (ItemCanasta item : canasta) {
			sb.append("ID: ").append(item.producto.getId()).append(" | Producto: ").append(item.producto.getProd())
					.append(" | Cantidad: ").append(item.cantidad).append(" | Precio: ").append(item.producto.getPrec())
					.append("\n");
		}
		sb.append("------------------\nTOTAL A PAGAR: ").append(calcularTotal());
		textArea.setText(sb.toString());
	}

	private Products buscarProducto(String id) {
		for (Products p : lista) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}

	private int cantidadEnCanasta(String id) {
		int total = 0;
		for (ItemCanasta item : canasta) {
			if (item.producto.getId().equals(id))
				total += item.cantidad;
		}
		return total;
	}

	private static class ItemCanasta {
		final Products producto;
		final int cantidad;

		ItemCanasta(Products producto, int cantidad) {
			this.producto = producto;
			this.cantidad = cantidad;
		}
	}

	private double calcularTotal() {
		double total = 0;
		for (ItemCanasta item : canasta) {
			total += item.producto.getPrec() * item.cantidad;
		}
		return total;
	}

	private void generarBoleta() {

		if (canasta.isEmpty()) {
			textArea.setText("Error: la canasta está vacía.\n");
			return;
		}
		StringBuilder boleta = new StringBuilder("===== BOLETA =====\n");
		contadorBoletas++;
		int numeroBoleta = contadorBoletas;
		for (ItemCanasta item : canasta) {
			double subtotal = item.producto.getPrec() * item.cantidad;
			boleta.append(item.producto.getProd()).append(" x").append(item.cantidad).append(" = ").append(subtotal)
					.append("\n");
			item.producto.setStock(item.producto.getStock() - item.cantidad);
			ventas.add(new Venta(numeroBoleta, item.producto.getId(), item.producto.getProd(), item.cantidad,
					item.producto.getPrec(), item.producto.getCosto()));
		}
		boleta.append("------------------\nTOTAL: ").append(calcularTotal());
		textArea.setText(boleta.toString());
		canasta.clear();
	}

	public void guardarVentas(File archivo) throws IOException {
		try (PrintWriter pw = new PrintWriter(archivo)) {
			for (Venta v : ventas) {
				pw.println(v.getNumeroBoleta() + ";" + v.getIdProducto() + ";" + v.getProducto() + ";" + v.getCantidad()
						+ ";" + v.getPrecioVenta() + ";" + v.getCosto());
			}
		}
	}

	public void cargarVentas(File archivo) throws IOException {
		ventas.clear();
		contadorBoletas = 0;
		if (!archivo.exists())
			return;

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty())
					continue;
				String[] d = linea.split(";");
				Venta v = new Venta(Integer.parseInt(d[0]), d[1], d[2], Integer.parseInt(d[3]),
						Double.parseDouble(d[4]), Double.parseDouble(d[5]));
				ventas.add(v);
				contadorBoletas = Math.max(contadorBoletas, v.getNumeroBoleta());
			}
		}
	}

	public void vaciarCanasta() {
		canasta.clear();
	}
}