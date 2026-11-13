package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import java.awt.Font;

public class MAINW extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtProd;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextArea textArea;
	private JComboBox<String> comboSelect;
	private ArrayList<Products> lista = new ArrayList<>();
	private JTextField txtCosto;
	private Compras compras;
	  private File archivoActual = null;
	
	  private void guardarProductos() {
		   	JFileChooser chooser = new JFileChooser();
		   	chooser.setDialogTitle("Guardar lista de productos"); 
		   	chooser.setSelectedFile(new File("productos.txt"));
		   	if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;  //Crea el archivo

		   	File destino = chooser.getSelectedFile();
		   	if (destino.exists()) {
		   		int r = JOptionPane.showConfirmDialog(this,
		   				"El archivo ya existe. ¿Desea sobrescribirlo?",
		   				"Confirmar", JOptionPane.YES_NO_OPTION);
		   		if (r != JOptionPane.YES_OPTION) return; //Reescribe el archivo
		   	}

		   	try (PrintWriter pw = new PrintWriter(destino)) {
		   		for (Products p : lista) {
		   			pw.println(p.getId() + ";" + p.getProd() + ";" + p.getTipo() + ";"
		   					+ p.getStock() + ";" + p.getPrec() + ";" + p.getCosto()); //Escribe todos los productos registrados al archivo con un for
		   		}
		   	   if (compras != null) compras.guardarVentas(archivoVentasDe(destino));
		   		archivoActual = destino;
		   		textArea.setText("Lista guardada en " + destino.getAbsolutePath() + " (" + lista.size() + " registros).\n"); // Muestra la cantidad de registros guardados
		   	} catch (IOException ex) {
		   		textArea.setText("Error al guardar: " + ex.getMessage() + "\n");
		   	}
		   }
	   private void cargarProductos() {
		   	JFileChooser chooser = new JFileChooser();
		   	chooser.setDialogTitle("Cargar lista de productos");
		   	if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

		   	File origen = chooser.getSelectedFile();
		   	if (!origen.exists()) {
		   		textArea.setText("El archivo seleccionado no existe.\n");
		   		return;
		   	}

		   	ArrayList<Products> cargada = new ArrayList<>();
		   	try (BufferedReader br = new BufferedReader(new FileReader(origen))) {
		   		String linea;
		   		while ((linea = br.readLine()) != null) {
		   			if (linea.trim().isEmpty()) continue;
		   			String[] d = linea.split(";");
		   			cargada.add(new Products(d[0], d[1], d[2],
		   					Integer.parseInt(d[3]), Double.parseDouble(d[4]), Double.parseDouble(d[5])));
		   		}
		   	} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
		   		textArea.setText("Error al cargar: el archivo tiene un formato inválido.\n");
		   		return;
		   	}

		   	lista.clear();
		   	lista.addAll(cargada);
		    if (compras != null) compras.vaciarCanasta();
		    try {
		       	if (compras != null) compras.cargarVentas(archivoVentasDe(origen));
		       } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
		       	textArea.append("\nAviso: no se pudieron cargar las ventas (" + ex.getMessage() + ").\n");
		       }
		   	archivoActual = origen;
		   	mostrarLista();
		   	textArea.append("\nCargada: " + origen.getName() + " (" + lista.size() + " productos).\n");
		   }
	   private File archivoVentasDe(File base) {
		   	String nombre = base.getName();
		   	if (nombre.toLowerCase().endsWith(".txt")) {
		   		nombre = nombre.substring(0, nombre.length() - 4);
		   	}
		   	return new File(base.getParentFile(), nombre + "_ventas.txt");
		   }
	
	public ArrayList<Products> getLista() { // Permite vincular datos para ser usados en otras ventanas como Compras
		return lista;
	}
	/**
	 * Create the panel.
	 */
	public MAINW() {
		
		// Constructor + ventana absoluta jframe

		
		{
			setLayout(null);
			setLayout(null);
		}
		setBackground(new java.awt.Color(100, 112, 206));
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(26, 26, 44, 12);
		add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Producto");
		lblNewLabel_1_1.setBounds(26, 48, 44, 12);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Precio");
		lblNewLabel_2_1.setBounds(26, 70, 44, 12);
		add(lblNewLabel_2_1);

		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(26, 92, 44, 12);
		add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(89, 23, 96, 18);
		add(txtId);

		txtProd = new JTextField();
		txtProd.setColumns(10);
		txtProd.setBounds(89, 45, 96, 18);
		add(txtProd);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(89, 67, 96, 18);
		add(txtPrecio);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(89, 89, 96, 18);
		add(txtStock);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = txtId.getText().trim();
					String prod = txtProd.getText().trim();
					String idbuscar = txtId.getText().trim();

					if (comboSelect.getSelectedItem() == null || comboSelect.getSelectedItem().toString().isEmpty()) {
						textArea.setText("Error: Debe seleccionar un tipo de producto.\n");
						return;
					}
					for (Products p : lista) {
						if (p.getId().equals(idbuscar)) {
							textArea.setText("Error: Este ID ya se encuentra registrado.\n");
							return;
						}
						}
					String tipo = comboSelect.getSelectedItem().toString();
					double precio = Double.parseDouble(txtPrecio.getText().trim());
					int stock = Integer.parseInt(txtStock.getText().trim());
					double costo = Double.parseDouble(txtCosto.getText().trim());
					Products p = new Products(id, prod, tipo, stock, precio,costo);
					lista.add(p);
					mostrarLista();
					limpiarCampos();

				} catch (NumberFormatException ex) {
					textArea.setText("Error: Precio Venta, Costo de compra y Stock deben ser numéricos.\n");
				}
			}
		});
		btnInsertar.setBounds(314, 26, 84, 20);
		add(btnInsertar);
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idBuscar = txtId.getText().trim();

					// Valida si se insertó id
					if (idBuscar.isEmpty()) {
						textArea.setText("Error: Digite el ID del producto que desea modificar.\n");
						return;
					}

					boolean encontrado = false;

					// Recorre la lista para buscar el producto por id.
					for (Products p : lista) {
						if (p.getId().equals(idBuscar)) {

							// Obtiene los nuevos valores registrados en lso campos
							String nuevoProd = txtProd.getText().trim();
							String nuevoTipo = comboSelect.getSelectedItem().toString();
							double nuevoPrecio = Double.parseDouble(txtPrecio.getText().trim());
							int nuevoStock = Integer.parseInt(txtStock.getText().trim());
							double nuevoCosto = Double.parseDouble(txtCosto.getText().trim());

							// Modifica los atributos del objeto
							p.setProd(nuevoProd);
							p.setTipo(nuevoTipo);
							p.setPrec(nuevoPrecio);
							p.setStock(nuevoStock);
							p.setCosto(nuevoCosto);

							encontrado = true;
							break; // Sale del ciclo ya que encontramos el producto
						}
					}

					if (encontrado) {
						mostrarLista();
						limpiarCampos();

					} else {
						textArea.setText("Error: No se encontró ningún producto con el ID: " + idBuscar + "\n");
					}

				} catch (NumberFormatException ex) {
					textArea.setText("Error: Al modificar, Precio Venta, Costo de compra y Stock deben ser numéricos.\n");
				}
			}
		});
		btnModificar.setBounds(314, 48, 84, 20);
		add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idEliminar = txtId.getText().trim();

				if (idEliminar.isEmpty()) {
					textArea.setText("Error: Digite el ID del producto que desea eliminar.\n");
					return;
				}

				Products encontrado = null;
				for (Products p : lista) {
					if (p.getId().equals(idEliminar)) {
						encontrado = p;
						break;
					}
				}

				if (encontrado == null) {
					textArea.setText("Error: No se encontró ningún producto con el ID: " + idEliminar + "\n");
					return;
				}

				int confirmar = JOptionPane.showConfirmDialog(
						MAINW.this,
						"¿Seguro que desea eliminar el producto?\n"
								+ "ID: " + encontrado.getId() + "\n"
								+ "Producto: " + encontrado.getProd(),
						"Confirmar eliminación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (confirmar != JOptionPane.YES_OPTION) {
					textArea.setText("Eliminación cancelada.\n");
					return;
				}

				lista.remove(encontrado);

				mostrarLista();
				limpiarCampos();
			}});	
			
		btnEliminar.setBounds(314, 70, 84, 20);
		add(btnEliminar);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBounds(26, 146, 470, 120);
		add(textArea);

		comboSelect = new JComboBox<String>();
		comboSelect.setModel(new DefaultComboBoxModel<>(
				new String[] { "", "Aseo", "Comestibles", "Enlatados", "Proteinas", "Cereales", "Golosinas" }));
		comboSelect.setBounds(89, 118, 96, 22);
		add(comboSelect);

		JLabel lblNewLabel_3_1 = new JLabel("Tipo");
		lblNewLabel_3_1.setBounds(26, 123, 44, 12);
		add(lblNewLabel_3_1);
		
		JButton btnActualizar = new JButton("Actualizar Datos");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLista();
			}
		});
		btnActualizar.setBounds(314, 115, 117, 20);
		add(btnActualizar);
		
		JLabel lblNewLabel_2_2 = new JLabel("Costo");
		lblNewLabel_2_2.setBounds(201, 26, 44, 12);
		add(lblNewLabel_2_2);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(201, 42, 96, 18);
		add(txtCosto);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarProductos();
			}
		});
	    btnGuardar.setBounds(26, 275, 84, 20);
		add(btnGuardar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarProductos();
			}
		});
		btnCargar.setBounds(140, 275, 84, 20);
		add(btnCargar);

	}

	public void actionPerformed(ActionEvent e) {
	}
	private void mostrarLista() {
		textArea.setText("--- Lista de Productos Actualizada ---\n");
		for (Products p : lista) {
			textArea.append("ID: " + p.getId() + " | Producto: " + p.getProd()
					+ "\n | Tipo: " + p.getTipo() + " | Precio Venta: " + p.getPrec()
					+ " | Stock: " + p.getStock() + "\n [!!!] Costo de compra: " + p.getCosto() + "\n");
		}
	}
	private void limpiarCampos() {
		txtId.setText("");
		txtProd.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		txtCosto.setText("");
		comboSelect.setSelectedIndex(0);
	}
	  public void setCompras(Compras c) {
			this.compras = c;
		}
}
