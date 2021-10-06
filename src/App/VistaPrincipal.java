package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

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
		ventana_principal.setResizable(false);
		ventana_principal.setTitle("Lector De Archivos De Texto Desde URL");
		ventana_principal.setBounds(100, 100, 622, 389);
		ventana_principal.setLocationRelativeTo(null);
		ventana_principal.setVisible(true);
		ventana_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana_principal.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 32, 419, 41);
		ventana_principal.getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JLabel lblNewLabel = new JLabel("URL del archivo de texto :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 24, 161, 49);
		ventana_principal.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 135, 566, 202);
		ventana_principal.getContentPane().add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		
		JLabel lblContenidoDelArchivo = new JLabel("Contenido del archivo :");
		lblContenidoDelArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblContenidoDelArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContenidoDelArchivo.setBounds(216, 84, 179, 49);
		ventana_principal.getContentPane().add(lblContenidoDelArchivo);
		
		JButton btnNewButton = new JButton("Recuperar contenido");
		btnNewButton.setBounds(21, 98, 161, 23);
		ventana_principal.getContentPane().add(btnNewButton);
	}
}
