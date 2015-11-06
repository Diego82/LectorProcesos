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
	private JTablaModelo modelo = null;
	static List<Proceso> listadoProcesos;
	private JTable tableProcess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		listadoProcesos = LanzarProceso.lanzar("ps", "aux");
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
		splitPane.setResizeWeight(.25);
		// splitPane.setDividerLocation();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		tableProcess = new JTable();
		scrollPane.setViewportView(tableProcess);

		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(panelPestañas);

		JLayeredPane usoCPU = new JLayeredPane();
		panelPestañas.addTab("Uso CPU", null, usoCPU, null);
		
		JPanel panelUsoCPU = new JPanel();
		panelUsoCPU.setMaximumSize(getMaximumSize());
		panelUsoCPU.setBounds(50, 50, 500, 400);

		//Creamos la base de datos del proceso
		DefaultPieDataset data = new DefaultPieDataset();
        for (int i = 0; i < listadoProcesos.size(); i++) {
        	if(listadoProcesos.get(i).getUsoMemoria()>0.1)
        		data.setValue(listadoProcesos.get(i).getPid(),listadoProcesos.get(i).getUsoMemoria());        
		}
                
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Uso de la CPU", 
         data, 
         true, 
         true, 
         false);
 
        // Crear el Panel del Grafico con ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        panelUsoCPU.add(chartPanel);
        usoCPU.add(panelUsoCPU);
		
        
		JLayeredPane usoRAM = new JLayeredPane();
		panelPestañas.addTab("Uso RAM", null, usoRAM, null);

		JLayeredPane usoSwap = new JLayeredPane();
		panelPestañas.addTab("Uso Swap", null, usoSwap, null);

	}
}
