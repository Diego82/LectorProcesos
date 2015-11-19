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
import logica.JTablaModelPsAux;
import logica.JTablaModelServicios;
import logica.JTableModelFree;
import logica.LanzarProcesoFree;
import logica.ProcesoPsAux;
import logica.ProcesoServicio;
import logica.ProcesoFree;
import logica.LanzarProcesoPsaux;
import logica.LanzarServicio;

import javax.swing.JPanel;
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

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JFrame frame;
	private static JTablaModelPsAux modeloCPU;
	private static JTableModelFree modeloMemoria;
	private static JTablaModelServicios modeloServicios;
	static List<ProcesoPsAux> listadoProcesosCPU;
	static List<ProcesoFree> listadoProcesosMemoria;
	static List<ProcesoServicio> listadoServicios;
	private JTable tableProcesoPsAux;
	private JTable tableProcessFree;
	private JTable tableProcessServicios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		listadoProcesosCPU = LanzarProcesoPsaux.lanzar("ps", "aux");
		listadoProcesosMemoria = LanzarProcesoFree.lanzarFree("free");
		listadoServicios = LanzarServicio.lanzarService("service", "--status-all");
		modeloCPU = new JTablaModelPsAux(listadoProcesosCPU);
		modeloMemoria = new JTableModelFree(listadoProcesosMemoria);
		modeloServicios = new JTablaModelServicios(listadoServicios);
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
		frame.setBounds(100, 0, 800, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Creamos el grafico
		 */
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(.65);

		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tableProcesoPsAux = new JTable(modeloCPU);
		scrollPane.setViewportView(tableProcesoPsAux);

		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(panelPestañas);

		JPanel panelUsoCPU = new JPanel();
		JPanel panelUsoRAM = new JPanel();
		JPanel panelServicios = new JPanel();

		panelPestañas.addTab("Uso CPU", null, panelUsoCPU, null);
		panelPestañas.addTab("Uso RAM", null, panelUsoRAM, null);
		panelPestañas.addTab("Servicios", null, panelServicios, null);

		/**
		 * Con este evento mantenemos sincronizado el panel mostrado con su
		 * correspondiente tabla
		 */
		panelPestañas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				if (panelPestañas.getSelectedIndex() == 0) {
					// Cargar procesos en la tabla
					tableProcesoPsAux = new JTable(modeloCPU);
					scrollPane.setViewportView(tableProcesoPsAux);
					modeloCPU = new JTablaModelPsAux(listadoProcesosCPU);
				} else if (panelPestañas.getSelectedIndex() == 1) {
					// cargar datos de memoria
					tableProcessFree = new JTable(modeloMemoria);
					scrollPane.setViewportView(tableProcessFree);
					modeloMemoria = new JTableModelFree(listadoProcesosMemoria);
				} else {
					// cargar servicios
					tableProcessServicios = new JTable(modeloServicios);
					scrollPane.setViewportView(tableProcessServicios);
					modeloServicios = new JTablaModelServicios(listadoServicios);
				}
			}
		});

		JChartPanelPlantilla plantilla = new JChartPanelPlantilla(listadoProcesosCPU);
		panelUsoCPU.add(plantilla.ventanaCPU());

		JChartPanelPlantilla plantilla2 = new JChartPanelPlantilla(listadoProcesosMemoria, 1);
		panelUsoRAM.add(plantilla2.ventanaRAM());

		JChartPanelPlantilla plantilla3 = new JChartPanelPlantilla(listadoServicios, true);
		panelServicios.add(plantilla3.ventanaServicios());

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

		/**
		 * En este evento guardamos las gráficas
		 */

		JMenuItem mntmGuardarGrafica = new JMenuItem("Guardar gráfica");
		mntmGuardarGrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFreeChart aux1 = JChartPanelPlantilla.chart1;
				JFreeChart aux2 = JChartPanelPlantilla.chart2;
				JFreeChart aux3 = JChartPanelPlantilla.chart3;
				String nameFileCPU = "grafico_CPU.jpg";
				String nameFileMemoria = "grafico_Memoria.jpg";
				String nameFileServicios = "grafico_Servicios.jpg";

				/**
				 * Con este switch llamamos al metodo crearArchivo() Las
				 * graficas que se encuentre cargada en la vista será la que se
				 * guarde en disco
				 */

				switch (panelPestañas.getSelectedIndex()) {
				case 0:
					crearArchivo(aux1, nameFileCPU);
					break;
				case 1:
					crearArchivo(aux2, nameFileMemoria);
					break;
				case 2:
					crearArchivo(aux3, nameFileServicios);
					break;
				default:
					break;
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

			/**
			 * Muestra los creadores del programa
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"Programa creado por:\nDaniel García Merino\nDiego Jesús Torres Peinado", "Informacion",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * Metodo que nos permite guardar las diferentes graficas con su
	 * correspondiente nombre
	 * 
	 * @param chart
	 *            grafica que se va a guardar
	 * @param nameFile
	 *            nombre del archivo
	 */
	public static void crearArchivo(JFreeChart chart, String nameFile) {
		// File ruta = new File("~/graficas/grafico.jpg");
		// ruta.mkdirs();
		try {
			ChartUtilities.saveChartAsJPEG(new File(nameFile), chart, 800, 500);
			System.out.println("entramos y pintamos");
			JOptionPane.showMessageDialog(null, "El archivo se ha guardado con exito", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
