/**
 * 
 */
package App;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


/**
 * @author Juan Manuel Torres
 *
 */
public class Controlador implements MouseListener {
	
	private VistaPrincipal vista;
	public String link;
	private String tmp;
	
	
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
	Runnable esperarAntesDeEmpezarOperacion = () -> getUrl();
	Runnable quitarElAviso = () -> quitarAviso();
	Runnable esperarAntesDeEmpezarCopiadoDelPortapapeles = () -> obtenerYpegarContenidoDelPortapapeles();
	
	
	public Controlador(VistaPrincipal vista) {
		this.vista = vista;
		
		vista.btn_recuperar_contenido.addMouseListener(this);
		vista.btn_reemplazar.addMouseListener(this);
		vista.code.addMouseListener(this);
		vista.author.addMouseListener(this);
		vista.license.addMouseListener(this);
		
		obtenerYpegarContenidoDelPortapapeles();
		getFixedUrl("https://juanmatorres-dev.me/Lector-De-Archivos-De-Texto-Desde-URL/version.txt");
		
	}
	
	public void obtenerYpegarContenidoDelPortapapeles() {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = cb.getContents(this);
		// Construimos el DataFlavor correspondiente al String java
		DataFlavor dataFlavorStringJava = null;
		try {
			dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Y si el dato se puede conseguir como String java, lo sacamos por pantalla
		if (t.isDataFlavorSupported(dataFlavorStringJava)) {
			String texto = null;
			try {
				texto = (String) t.getTransferData(dataFlavorStringJava);
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(texto);
			vista.url.setText(texto);
			vista.aviso.setForeground(new Color(34, 139, 34));
			vista.aviso.setText("Se ha copiado el último texto de tu portapapeles :)");
			service2.schedule(quitarElAviso, 5, TimeUnit.SECONDS);
		} else {
			vista.aviso.setForeground(Color.RED);
			vista.aviso.setText("No se ha podido copiar el contenido de tu portapepeles (no es texto)");
			service2.schedule(quitarElAviso, 5, TimeUnit.SECONDS);
		}
	}
	
	
	public void quitarAviso() {
		vista.aviso.setText("");
	}
	
	public void getUrl() {
		link = vista.url.getText();
		try {
			URL url = new URL(link);
			
			System.out.println("-----");
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = bfr.readLine()) != null) {
				tmp = vista.textPane.getText() + inputLine + "\n";
				vista.textPane.setText(tmp);
				System.out.println(inputLine);
			}
			
			bfr.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(link);
		end_loading();
	}
	
	/**
	 * Obtiene los datos de una url fija y devuelve el contenido
	 * @param url
	 */
	public String getFixedUrl(String url) {
		String content = "?";
		String tmp;
		
		try {
			URL fixedUrl = new URL(url);
			
			System.out.println("-----");
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(fixedUrl.openStream()));
			String inputLine;
			while ((inputLine = bfr.readLine()) != null) {
				tmp = vista.version.getText() + inputLine + "\n";
				vista.version.setText(tmp);
				System.out.println(inputLine);
			}
			
			bfr.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return content;
	}
	
	public void start_loading() {
		vista.btn_recuperar_contenido.setEnabled(false);
		vista.btn_recuperar_contenido.setText("");
		vista.btn_reemplazar.setEnabled(false);
		vista.loading.setVisible(true);
		System.out.println("loading...");
	}
	
	public void end_loading() {
		vista.loading.setVisible(false);
		vista.btn_recuperar_contenido.setEnabled(true);
		vista.btn_reemplazar.setEnabled(true);
		vista.btn_recuperar_contenido.setText("Recuperar contenido");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(vista.btn_recuperar_contenido) && vista.btn_recuperar_contenido.isEnabled()) {
			//JOptionPane.showMessageDialog(null, "Recuperando contenido ...");
			vista.textPane.setText("");
			start_loading();
			/**
			 * Esperamos un milisegundo antes de empezar la lectura del fichero para que no se superponga con la operación de poner el loader
			 */
			service.schedule(esperarAntesDeEmpezarOperacion, 1, TimeUnit.MILLISECONDS);
		}else if(e.getSource().equals(vista.btn_reemplazar) && vista.btn_reemplazar.isEnabled()) {
			//JOptionPane.showMessageDialog(null, "Reemplazar");
			vista.url.setText("");
			service.schedule(esperarAntesDeEmpezarCopiadoDelPortapapeles, 5, TimeUnit.MILLISECONDS);
		}else if(e.getSource().equals(vista.code)) {
			System.out.println("Code 🏹");
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/juanmatorres-dev/Lector-De-Archivos-De-Texto-Desde-URL"));
			} catch (Exception er) {
				JOptionPane.showInternalMessageDialog(null, "Error ");
			}
		}else if(e.getSource().equals(vista.author)) {
			System.out.println("author 😎");
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/juanmatorres-dev"));
			} catch (Exception er) {
				JOptionPane.showInternalMessageDialog(null, "Error ");
			}
		}else if(e.getSource().equals(vista.license)) {
			System.out.println("license 📚");
			try {
				Desktop.getDesktop().browse(new URI("https://creativecommons.org/licenses/by/4.0/deed.es"));
			} catch (Exception er) {
				JOptionPane.showInternalMessageDialog(null, "Error ");
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
