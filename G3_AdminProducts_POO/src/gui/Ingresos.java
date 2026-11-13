package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ingresos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdresumen;
	private JTextField txtBoleta;
	private ArrayList<Venta> ventas;
	private JTextArea areaResultado;
	/**
	 * Create the panel.
	 */
	public Ingresos() {
		ventas = new ArrayList<>();
		
		setLayout(null);
		
		 areaResultado = new JTextArea();
		 areaResultado.setWrapStyleWord(true);
		  areaResultado.setEditable(false);
		   areaResultado.setBounds(211, 5, 229, 284);
		   add(areaResultado);
		
		JPanel PanelResumenProd = new JPanel();
		PanelResumenProd.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelResumenProd.setBounds(10, 10, 174, 131);
		add(PanelResumenProd);
		PanelResumenProd.setLayout(null);
		
		txtIdresumen = new JTextField();
		txtIdresumen.setBounds(10, 37, 111, 20);
		PanelResumenProd.add(txtIdresumen);
		txtIdresumen.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(13, 22, 44, 12);
		PanelResumenProd.add(lblNewLabel_2);
		
		JButton btnResumenProd = new JButton("Ver resumen");
		btnResumenProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idresumen = txtIdresumen.getText().trim();
				resumenProducto(idresumen);
			}
		});
		btnResumenProd.setBounds(10, 87, 124, 23);
		PanelResumenProd.add(btnResumenProd);
		
		JPanel PanelResumenBolet = new JPanel();
		PanelResumenBolet.setLayout(null);
		PanelResumenBolet.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Boleta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PanelResumenBolet.setBounds(10, 152, 174, 131);
		add(PanelResumenBolet);
		
		txtBoleta = new JTextField();
		txtBoleta.setColumns(10);
		txtBoleta.setBounds(10, 37, 111, 20);
		PanelResumenBolet.add(txtBoleta);
		
		JLabel lblNewLabel_2_1 = new JLabel("Boleta");
		lblNewLabel_2_1.setBounds(13, 22, 44, 12);
		PanelResumenBolet.add(lblNewLabel_2_1);
		
		JButton btnResumenBolet_1 = new JButton("Ver resumen");
		btnResumenBolet_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idBolet = txtBoleta.getText().trim();
				
				resumenBoleta(idBolet);
			}
		});
		btnResumenBolet_1.setBounds(10, 87, 124, 23);
		PanelResumenBolet.add(btnResumenBolet_1);

		
		
		
	}
	private void resumenProducto(String id) {
		int cantidad = 0;
		double ingreso = 0, costoTotal = 0, ganancia = 0;
		String nombre = null;

		for (Venta v : ventas) {
			if (v.getIdProducto().equals(id)) {
				nombre = v.getProducto();
				cantidad += v.getCantidad();
				ingreso += v.getPrecioVenta() * v.getCantidad();
				costoTotal += v.getCosto() * v.getCantidad();
				ganancia += v.getGanancia();
			}
		}

		if (nombre == null) {
			areaResultado.setText("No hay ventas registradas para el ID " + id + ".");
			return;
		}
		areaResultado.setText("--- RESUMEN DE VENTAS ---\n");
		areaResultado.append("Producto: " + nombre
				+ "\nCantidad vendida: " + cantidad
				+ "\nIngreso total: " + ingreso
				+ "\nCosto total: " + costoTotal
				+ "\nGanancia: " + ganancia
				+ "\nPrecio promedio: " + (ingreso / cantidad));
	}
	
	private void resumenBoleta(String numero) {
		int IDboleta;
		try {
			IDboleta = Integer.parseInt(numero);
		} catch (NumberFormatException ex) {
			areaResultado.setText("Error: el número de boleta debe ser entero.");
			return;
		}

		StringBuilder sb = new StringBuilder("--- Boleta " + IDboleta + " ---\n");
		double total = 0;
		boolean encontrada = false;

		for (Venta v : ventas) {
			if (v.getNumeroBoleta() == IDboleta) {
				encontrada = true;
				sb.append("Producto:").append(v.getProducto()).append("\n | Cant: ").append(v.getCantidad())
				  .append("\n | Precio Venta: ").append(v.getPrecioVenta()).append("\n | Costo de compra: ").append(v.getCosto())
				  .append("\n | Ganancia total: ").append(v.getGanancia()).append("\n");
				total += v.getPrecioVenta() * v.getCantidad();
			}
		}

		if (!encontrada) {
			areaResultado.setText("No existe la boleta " + IDboleta + ".");
			return;
		}
		sb.append("TOTAL: ").append(total);
		areaResultado.setText(sb.toString());
	}
	public void setVentas(ArrayList<Venta> ventas) {
	   	this.ventas = ventas;
	   }
}
