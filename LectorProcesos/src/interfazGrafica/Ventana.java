package interfazGrafica; 

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logica.JTablaModelo;
import logica.Proceso;
import logica.LanzarProceso;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

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

		JMenuItem mntmSalir = new JMenuItem("salir");
		mnArchivo.add(mntmSalir);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(mntmAcercaDe);

		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);

		JButton btnKillProcess = new JButton("Kill process");
		panelNorte.add(btnKillProcess);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(.5);
		//splitPane.setDividerLocation(0.5);
		// splitPane.setDividerLocation();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		tableProcess = new JTable(modelo);
		scrollPane.setViewportView(tableProcess);

		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(panelPestañas);

		JPanel panelUsoCPU = new JPanel();
		//panelUsoCPU.setMaximumSize(getMaximumSize());
		//panelUsoCPU.setBounds(50, 50, 500, 400);
		
		//JLayeredPane usoCPU = new JLayeredPane();
		panelPestañas.addTab("Uso CPU", null, panelUsoCPU, null);
		
		

		modelo = new JTablaModelo(listadoProcesos);
		//Creamos la base de datos del proceso
		
		JChartPanelPlantilla plantilla = new JChartPanelPlantilla(listadoProcesos);
		
        panelUsoCPU.add(plantilla.ventana());
        //usoCPU.add(panelUsoCPU);
		
        
		JLayeredPane usoRAM = new JLayeredPane();
		panelPestañas.addTab("Uso RAM", null, usoRAM, null);

		JLayeredPane usoSwap = new JLayeredPane();
		panelPestañas.addTab("Uso Swap", null, usoSwap, null);
		

	}
}
