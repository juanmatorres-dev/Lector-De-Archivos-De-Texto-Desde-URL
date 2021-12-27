package App;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Locale;

public class VistaPrincipal {

	private JFrame ventana_principal;
	public JButton btn_recuperar_contenido;
	public JEditorPane url;
	public JTextPane textPane;
	public JLabel loading;
	private JScrollPane input;
	public JLabel aviso;
	public JButton btn_reemplazar;
	public JLabel version;
	public JLabel code;
	public JLabel author;
	public JLabel license;

	/**
	 * Create the application.
	 */
	public VistaPrincipal() {
		initialize();
		ventana_principal.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventana_principal = new JFrame();
		ventana_principal.setResizable(false);
		ventana_principal.setTitle("Lector De Archivos De Texto Desde URL");
		ventana_principal.setBounds(100, 100, 830, 423);
		ventana_principal.setLocationRelativeTo(null);
		//ventana_principal.setVisible(true);
		ventana_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana_principal.getContentPane().setLayout(null);
		
		input = new JScrollPane();
		input.setBounds(177, 46, 627, 41);
		ventana_principal.getContentPane().add(input);
		
		url = new JEditorPane();
		input.setViewportView(url);
		
		JLabel lblNewLabel = new JLabel("URL del archivo de texto :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(21, 46, 161, 49);
		ventana_principal.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 135, 783, 202);
		ventana_principal.getContentPane().add(scrollPane_1);
		
		textPane = new JTextPane();
		textPane.setLocale(new Locale("es", "ES"));
		scrollPane_1.setViewportView(textPane);
		
		JLabel lblContenidoDelArchivo = new JLabel("Contenido del archivo :");
		lblContenidoDelArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblContenidoDelArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContenidoDelArchivo.setBounds(176, 88, 179, 41);
		ventana_principal.getContentPane().add(lblContenidoDelArchivo);
		
		loading = new JLabel("");
		loading.setVisible(false);
		loading.setIcon(new ImageIcon("images/Loading.gif"));
		loading.setBounds(90, 100, 18, 18);
		ventana_principal.getContentPane().add(loading);
		
		btn_recuperar_contenido = new JButton("Recuperar contenido");
		btn_recuperar_contenido.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btn_recuperar_contenido.setBounds(21, 98, 161, 23);
		ventana_principal.getContentPane().add(btn_recuperar_contenido);
		
		aviso = new JLabel("");
		aviso.setForeground(new Color(34, 139, 34));
		aviso.setFont(new Font("Tahoma", Font.BOLD, 13));
		aviso.setBounds(177, 7, 529, 28);
		ventana_principal.getContentPane().add(aviso);
		
		btn_reemplazar = new JButton("Reemplazar");
		btn_reemplazar.setBounds(21, 22, 108, 23);
		ventana_principal.getContentPane().add(btn_reemplazar);
		
		version = new JLabel("");
		version.setFont(new Font("Tahoma", Font.BOLD, 17));
		version.setHorizontalAlignment(SwingConstants.CENTER);
		version.setBounds(353, 348, 108, 14);
		ventana_principal.getContentPane().add(version);
		
		code = new JLabel("Código del proyecto 🏹");
		code.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		code.setBounds(21, 348, 147, 14);
		ventana_principal.getContentPane().add(code);
		
		author = new JLabel("juanmatorres-dev");
		author.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		author.setHorizontalAlignment(SwingConstants.RIGHT);
		author.setBounds(657, 348, 147, 14);
		ventana_principal.getContentPane().add(author);
		
		license = new JLabel("");
		license.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		license.setIcon(new ImageIcon("images/88x31.png"));
		license.setBounds(716, 4, 88, 31);
		ventana_principal.getContentPane().add(license);
	}
}
