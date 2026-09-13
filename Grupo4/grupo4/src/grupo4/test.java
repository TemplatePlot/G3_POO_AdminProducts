package grupo4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class test extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField txtId;
    private JTextField txtProd;
    private JTextField txtPrec;
    private JTextField txtStock;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTextArea textArea;
    private ArrayList<Productos> lista = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test frame = new test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        {
            lblNewLabel = new JLabel("ID");
            lblNewLabel.setBounds(20, 10, 44, 12);
            contentPane.add(lblNewLabel);
        }
        {
            lblNewLabel_1 = new JLabel("Producto");
            lblNewLabel_1.setBounds(20, 32, 44, 12);
            contentPane.add(lblNewLabel_1);
        }
        {
            lblNewLabel_2 = new JLabel("Precio");
            lblNewLabel_2.setBounds(20, 54, 44, 12);
            contentPane.add(lblNewLabel_2);
        }
        {
            lblNewLabel_3 = new JLabel("Stock");
            lblNewLabel_3.setBounds(20, 76, 44, 12);
            contentPane.add(lblNewLabel_3);
        }
        {
            txtId = new JTextField();
            txtId.setBounds(83, 7, 96, 18);
            contentPane.add(txtId);
            txtId.setColumns(10);
        }
        {
            txtProd = new JTextField();
            txtProd.setBounds(83, 29, 96, 18);
            contentPane.add(txtProd);
            txtProd.setColumns(10);
        }
        {
            txtPrec = new JTextField();
            txtPrec.setBounds(83, 51, 96, 18);
            contentPane.add(txtPrec);
            txtPrec.setColumns(10);
        }
        {
            txtStock = new JTextField();
            txtStock.setBounds(83, 73, 96, 18);
            contentPane.add(txtStock);
            txtStock.setColumns(10);
        }
        {
            btnInsertar = new JButton("Insertar");
            btnInsertar.setBounds(207, 6, 84, 20);
            btnInsertar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int id        = Integer.parseInt(txtId.getText().trim());
                        String desc   = txtProd.getText().trim();
                        double precio = Double.parseDouble(txtPrec.getText().trim());
                        int stock     = Integer.parseInt(txtStock.getText().trim());

                        Productos p = new Productos(id, stock, precio, desc);
                        lista.add(p);

                        textArea.append("ID: " + id + " | Producto: " + desc +
                                        " | Precio: " + precio + " | Stock: " + stock + "\n");

                        txtId.setText("");
                        txtProd.setText("");
                        txtPrec.setText("");
                        txtStock.setText("");

                    } catch (NumberFormatException ex) {
                        textArea.setText("Error: ID, Precio y Stock deben ser numéricos.");
                    }
                }
            });
            contentPane.add(btnInsertar);
        }
        {
            btnModificar = new JButton("Modificar");
            btnModificar.addActionListener(this);
            btnModificar.setBounds(207, 28, 84, 20);
            contentPane.add(btnModificar);
        }
        {
            btnEliminar = new JButton("Eliminar");
            btnEliminar.setBounds(207, 50, 84, 20);
            contentPane.add(btnEliminar);
        }
        {
            textArea = new JTextArea();
            textArea.setBounds(21, 110, 405, 143);
            contentPane.add(textArea);
        }
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		textArea.setText("");
		String id = txtId.getText();
		String prod = txtProd.getText();
    	String desc   = txtProd.getText().trim();
		String precio = txtPrec.getText();
		String stock = txtStock.getText();

		textArea.append("ID: " + id + " | Producto: " + desc +
                " | Precio: " + precio + " | Stock: " + stock + "\n");

	}
}
