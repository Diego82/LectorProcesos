package interfazGrafica; 

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import logica.JTablaModelo;
import logica.Proceso;
import logica.LanzarProceso;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JFrame frame;
	// JPanel panel = new JPanel();
	private static JTablaModelo modelo;
	static List<Proceso> listadoProcesos;
	private JTable tableProcess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		listadoProcesos = LanzarProceso.lanzar("ps", "aux");
		modelo = new JTablaModelo(listadoProcesos);
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
		frame.setBounds(200, 150, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmGuardarEstado = new JMenuItem("Guardar estado");
		mnArchivo.add(mntmGuardarEstado);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Programa creado por:\nDaniel García Merino\nDiego Jesús Torres Peinado", "Informacion", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);

		JButton btnKillProcess = new JButton("Kill process");
		panelNorte.add(btnKillProcess);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(.5);

		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		tableProcess = new JTable(modelo);
		scrollPane.setViewportView(tableProcess);

		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(panelPestañas);

		JPanel panelUsoCPU = new JPanel();
		JPanel panelUsoRAM = new JPanel();
		JPanel panelUsoSWAP = new JPanel();
		
		panelPestañas.addTab("Uso CPU", null, panelUsoCPU, null);
		panelPestañas.addTab("Uso RAM", null, panelUsoRAM, null);	
		panelPestañas.addTab("Uso Swap", null, panelUsoSWAP, null);
		
		modelo = new JTablaModelo(listadoProcesos);
		
		JChartPanelPlantilla plantilla = new JChartPanelPlantilla(listadoProcesos);
		
		panelUsoCPU.add(plantilla.ventanaCPU());
		panelUsoRAM.add(plantilla.ventanaRAM());
		panelUsoSWAP.add(plantilla.ventanaSWAP());

	}
}
