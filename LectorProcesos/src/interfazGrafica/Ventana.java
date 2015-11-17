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
import logica.JTablaModelo3;
import logica.JTableModelo2;
import logica.LanzarProcesoFree;
import logica.ProcesoPsAux;
import logica.ProcesoServicio;
import logica.ProcesoFree;
import logica.LanzarProcesoPsaux;
import logica.LanzarServicio;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import javax.swing.event.ChangeEvent;

public class Ventana extends JFrame {

	private JFrame frame;
	// JPanel panel = new JPanel();
	private static JTablaModelo modeloCPU;
	private static JTableModelo2 modeloMemoria;
	private static JTablaModelo3 modeloServicios;
	static List<ProcesoPsAux> listadoProcesosCPU;
	static List<ProcesoFree> listadoProcesosMemoria;
	static List<ProcesoServicio> listadoServicios;
	private JTable tableProcess;
	private JTable tableProcess2;
	private JTable tableProcess3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		listadoProcesosCPU = LanzarProcesoPsaux.lanzar("ps", "aux");
		listadoProcesosMemoria = LanzarProcesoFree.lanzarFree("free");
		listadoServicios = LanzarServicio.lanzarService("service", "--status-all");
		modeloCPU = new JTablaModelo(listadoProcesosCPU);
		modeloMemoria = new JTableModelo2(listadoProcesosMemoria);
		modeloServicios = new JTablaModelo3(listadoServicios);
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

		
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);

		JButton btnKillProcess = new JButton("Kill process");
		btnKillProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelNorte.add(btnKillProcess);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(.5);

		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		tableProcess = new JTable(modeloCPU);
		scrollPane.setViewportView(tableProcess);

		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		
		splitPane.setRightComponent(panelPestañas);

		JPanel panelUsoCPU = new JPanel();
		JPanel panelUsoRAM = new JPanel();
		JPanel panelUsoSWAP = new JPanel();
		
		panelPestañas.addTab("Uso CPU", null, panelUsoCPU, null);
		panelPestañas.addTab("Uso RAM", null, panelUsoRAM, null);	
		panelPestañas.addTab("Uso Swap", null, panelUsoSWAP, null);
		
		panelPestañas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("pestaña "+ panelPestañas.getSelectedIndex());
				
				if(panelPestañas.getSelectedIndex()==0){
					//Cargar procesos en la tabla
					tableProcess = new JTable(modeloCPU);
					scrollPane.setViewportView(tableProcess);
					modeloCPU = new JTablaModelo(listadoProcesosCPU);
				}else if(panelPestañas.getSelectedIndex()==1){
					//cargar datos de memoria
					tableProcess2 = new JTable(modeloMemoria);
					scrollPane.setViewportView(tableProcess2);
					modeloMemoria = new JTableModelo2(listadoProcesosMemoria);
				}else {
					//cargar servicios
					tableProcess3 = new JTable(modeloServicios);
					scrollPane.setViewportView(tableProcess3);
					modeloServicios = new JTablaModelo3(listadoServicios);
				}
			}
		});
		
		/*JChartPanelPlantilla plantilla 
		JChartPanelPlantilla plantilla2
		JChartPanelPlantilla plantilla3*/
		
		JChartPanelPlantilla plantilla = new JChartPanelPlantilla(listadoProcesosCPU);
		panelUsoCPU.add(plantilla.ventanaCPU());
		
		JChartPanelPlantilla plantilla2 = new JChartPanelPlantilla(listadoProcesosMemoria,1);
		panelUsoRAM.add(plantilla2.ventanaRAM());
		
		JChartPanelPlantilla plantilla3 = new JChartPanelPlantilla(listadoServicios,true);
		panelUsoSWAP.add(plantilla3.ventanaServicios());

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmGuardarGrafica = new JMenuItem("Guardar gráfica");
		mntmGuardarGrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				//Preguntar como creamos el archivo en el directorio home
				
				
				
				//File ruta = new File("~/graficas/grafico.jpg");
				//ruta.mkdirs();
				JFreeChart aux= JChartPanelPlantilla.chart1;
				try {
					ChartUtilities.saveChartAsJPEG(new File("grafico_CPU.jpg"), aux, 800, 500);
					System.out.println("entramos y pintamos");
					JOptionPane.showMessageDialog(null, "Guardado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);

					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnArchivo.add(mntmGuardarGrafica);
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
	}
}
