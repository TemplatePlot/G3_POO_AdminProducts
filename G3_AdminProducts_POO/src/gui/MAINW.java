package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class MAINW extends JPanel implements ActionListener {
	private JFrame ventanaMen;
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtProd;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextArea textArea;
    private JComboBox<String> comboSelect;
    private ArrayList <Products> lista = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public MAINW(JFrame vent) {
		//Constructor + ventana absoluta jframe
		this.ventanaMen = vent;
		 setBounds(0, 0, 450, 300);
		
		{
			setLayout(null);
			setLayout(null);
		}
		
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
                    String id        = txtId.getText().trim();
                    String prod   = txtProd.getText().trim();
                    
                    if (comboSelect.getSelectedItem() == null || comboSelect.getSelectedItem().toString().isEmpty()) {
                        textArea.setText("Error: Debe seleccionar un tipo de producto.\n");
                        return;
                    }
					String tipo = comboSelect.getSelectedItem().toString();
                    double precio = Double.parseDouble(txtPrecio.getText().trim());
                    int stock     = Integer.parseInt(txtStock.getText().trim());

                    Products p = new Products(id,prod,tipo,stock,precio);
                    lista.add(p);

                    textArea.append("ID: " + id + " | Producto: " + prod + "| Tipo: "+ tipo+
                                    " | Precio: " + precio + " | Stock: " + stock + "\n");

                    txtId.setText("");
                    txtProd.setText("");
                    txtPrecio.setText("");
                    txtStock.setText("");
                    comboSelect.setSelectedIndex(0);

                } catch (NumberFormatException ex) {
                    textArea.setText("Error: ID, Precio y Stock deben ser numéricos.\n");
                }
			}
		});
		btnInsertar.setBounds(213, 22, 84, 20);
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

							//Modifica los atributos del objeto
							p.setProd(nuevoProd);      
							p.setTipo(nuevoTipo);
							p.setPrec(nuevoPrecio);
							p.setStock(nuevoStock);

							encontrado = true;
							break; // Sale del ciclo ya que encontramos el producto
						}
					}

					if (encontrado) {
						// Limpiam el texto anterior
						textArea.setText("--- Lista de Productos Actualizada ---\n");
						for (Products p : lista) {
							textArea.append("ID: " + p.getId() + " | Producto: " + p.getProd() + 
											" | Tipo: " + p.getTipo() + " | Precio: " + p.getPrec() + 
											" | Stock: " + p.getStock() + "\n");
						}
						
						// Limpia campos de texto
						txtId.setText("");
						txtProd.setText("");
						txtPrecio.setText("");
						txtStock.setText("");
						comboSelect.setSelectedIndex(0);
						
					} else {
						textArea.setText("Error: No se encontró ningún producto con el ID: " + idBuscar + "\n");
					}

				} catch (NumberFormatException ex) {
					textArea.setText("Error: Al modificar, Precio y Stock deben ser numéricos.\n");
				}
			}
		});
		btnModificar.setBounds(213, 44, 84, 20);
		add(btnModificar);

		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(213, 66, 84, 20);
		add(btnEliminar);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(26, 146, 405, 143);
		add(textArea);
		
		comboSelect = new JComboBox<String>();
		comboSelect.setModel(new DefaultComboBoxModel<>(new String[] {"", "Aseo", "Comestibles", "Enlatados", "Proteinas", "Cereales", "Golosinas"}));
		comboSelect.setBounds(89, 118, 96, 22);
		add(comboSelect);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tipo");
		lblNewLabel_3_1.setBounds(26, 123, 44, 12);
		add(lblNewLabel_3_1);

	}
	public void actionPerformed(ActionEvent e) {
	}
}
