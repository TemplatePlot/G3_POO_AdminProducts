package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;


public class MainWindow extends JFrame {
		
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 355);
		
		MAINW Prods = new MAINW(this);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ventanas");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Productos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoPanel(Prods);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Compra");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Ganancias");
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblBienvenida = new JLabel("Bienvenido a Admin Products");
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenida.setForeground(new Color(0, 0, 128));
		lblBienvenida.setBounds(134, 11, 281, 14);
		contentPane.add(lblBienvenida);
		
		
		JButton btnInventario = new JButton("");
		btnInventario.setBounds(102, 163, 94, 64);
		ImageIcon iconoInventario = new ImageIcon(getClass().getResource("/caja.png"));
		Image imagenEscalada = iconoInventario.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		btnInventario.setIcon(new ImageIcon(imagenEscalada));
		btnInventario.setBorderPainted(false);
		btnInventario.setFocusPainted(false);
		btnInventario.setContentAreaFilled(false);
		btnInventario.setOpaque(false);
		contentPane.add(btnInventario);
		
		btnInventario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Botón Inventario presionado");
		    }
		});
		
		JButton btnCompras = new JButton("");
		btnCompras.setBounds(194, 163, 94, 64);
		ImageIcon iconocompras = new ImageIcon(getClass().getResource("/carro.png"));
		Image imagenCompras = iconocompras.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		btnCompras.setIcon(new ImageIcon(imagenCompras));
		btnCompras.setBorderPainted(false);
		btnCompras.setFocusPainted(false);
		btnCompras.setContentAreaFilled(false);
		btnCompras.setOpaque(false);
		contentPane.add(btnCompras);
		
		JButton btnIngresos = new JButton("");
		btnIngresos.setBounds(297, 163, 94, 64);
		ImageIcon iconoingresos = new ImageIcon(getClass().getResource("/ingresos.png"));
		Image imageningresos = iconoingresos.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		btnIngresos.setIcon(new ImageIcon(imageningresos));
		btnIngresos.setBorderPainted(false);
		btnIngresos.setFocusPainted(false);
		btnIngresos.setContentAreaFilled(false);
		btnIngresos.setOpaque(false);
		contentPane.add(btnIngresos);
		
		
		JLabel lblNewLabel = new JLabel("INVENTARIO");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(117, 225, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCompras = new JLabel("COMPRAS");
		lblCompras.setForeground(new Color(0, 0, 128));
		lblCompras.setBounds(215, 225, 76, 14);
		contentPane.add(lblCompras);
		
		JLabel lblIngresos = new JLabel("INGRESOS");
		lblIngresos.setForeground(new Color(0, 0, 128));
		lblIngresos.setBounds(315, 225, 76, 14);
		contentPane.add(lblIngresos);
		
		JLabel lblNewLabel_1 = new JLabel("SISTEMA DE GESTION Y VENTAS");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(153, 54, 294, 14);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 506, 294);
		ImageIcon icon = new ImageIcon("imagenes/mainbackground.png");
		Image imagen = icon.getImage().getScaledInstance(506,294,Image.SCALE_SMOOTH);
		lblBackground.setIcon(new ImageIcon(imagen));
		contentPane.add(lblBackground);
		


		



	}
	
	private void nuevoPanel (JPanel panelAct) {
		contentPane.removeAll();
		contentPane.add(panelAct);
		contentPane.repaint();
		contentPane.revalidate();
	}
}
