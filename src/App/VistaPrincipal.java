package App;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class VistaPrincipal {

	private JFrame ventana_principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal window = new VistaPrincipal();
//					window.ventana_principal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventana_principal = new JFrame();
		ventana_principal.setTitle("Lector De Archivos De Texto Desde URL");
		ventana_principal.setBounds(100, 100, 450, 300);
		ventana_principal.setLocationRelativeTo(null);
		ventana_principal.setVisible(true);
		ventana_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
