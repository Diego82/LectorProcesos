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
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JFrame frmMonitoreat;
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

		// Lanzamos los procesos y recogemos las listas con los datos recibidos
		listadoProcesosCPU = LanzarProcesoPsaux.lanzar("ps", "aux");
		listadoProcesosMemoria = LanzarProcesoFree.lanzarFree("free");
		listadoServicios = LanzarServicio.lanzarService("service", "--status-all");

		// Cargamos las tablas cons los datos recogidos de las listas para
		// mostrar posteriormente
		modeloCPU = new JTablaModelPsAux(listadoProcesosCPU);
		modeloMemoria = new JTableModelFree(listadoProcesosMemoria);
		modeloServicios = new JTablaModelServicios(listadoServicios);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmMonitoreat.setVisible(true);
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
		frmMonitoreat = new JFrame();
		frmMonitoreat.setIconImage(Toolkit.getDefaultToolkit().getImage("/home/diego/git/LectorProcesos/LectorProcesos/status.png"));
		frmMonitoreat.setTitle("Monitorea-T");
		frmMonitoreat.setBounds(100, 0, 800, 900);
		frmMonitoreat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Creamos el grafico
		 */
		JPanel panelNorte = new JPanel();
		frmMonitoreat.getContentPane().add(panelNorte, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(.65);

		frmMonitoreat.getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		// Aqui cargamos los diferentes JTable que vamos a utilizar en las
		// diferentes vistas del programa y cargamos por defecto la primera
		// pestaña con la grafica

		tableProcesoPsAux = new JTable(modeloCPU);
		tableProcessFree = new JTable(modeloMemoria);
		tableProcessServicios = new JTable(modeloServicios);
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
					// Muestra en la tabla los procesos ps aux
					scrollPane.setViewportView(tableProcesoPsAux);
				} else if (panelPestañas.getSelectedIndex() == 1) {
					// Muestra en la tabla los procesos free
					scrollPane.setViewportView(tableProcessFree);
				} else {
					// Muestra en la tabla el estado de los servicios
					scrollPane.setViewportView(tableProcessServicios);
				}
			}
		});

		// Aqui creamos los JChartPanel necesarios para mostrar las graficas y
		// las añadimos a sus respectivas pestañas

		JChartPanelPlantilla plantilla = new JChartPanelPlantilla(listadoProcesosCPU);
		panelUsoCPU.add(plantilla.ventanaCPU());

		JChartPanelPlantilla plantilla2 = new JChartPanelPlantilla(listadoProcesosMemoria, 1);
		panelUsoRAM.add(plantilla2.ventanaRAM());

		JChartPanelPlantilla plantilla3 = new JChartPanelPlantilla(listadoServicios, true);
		panelServicios.add(plantilla3.ventanaServicios());

		// Creamos la barra menu

		JMenuBar menuBar = new JMenuBar();
		frmMonitoreat.setJMenuBar(menuBar);

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
			JOptionPane.showMessageDialog(null, "El archivo se ha guardado con exito", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
