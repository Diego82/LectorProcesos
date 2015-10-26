package interfazGrafica;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

import logica.JTablaModelo;
import logica.Proceso;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana {

	private JFrame frame;
	private JTable table;
	private JTablaModelo modelo = null;
	List<Proceso> listaProcesos = new ArrayList<Proceso>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 150, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu MenuArchivo = new JMenu("Archivo");
		menuBar.add(MenuArchivo);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JMenuItem mntmVerProceso = new JMenuItem("Ver procesos");
		mntmVerProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proceso p = new Proceso("hola", "hola", "hola", "hola", "hola", "hola", "hola", "hola", "hola", "hola", "hola");
				listaProcesos.add(p);
				modelo = new JTablaModelo(listaProcesos);
				table  = new JTable(modelo);
				scrollPane.setViewportView(table);
				
			}
		});
		MenuArchivo.add(mntmVerProceso);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		MenuArchivo.add(mntmSalir);
		
		JMenu mnCreditos = new JMenu("Ayuda");
		menuBar.add(mnCreditos);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnCreditos.add(mntmAcercaDe);
			
	}
}
