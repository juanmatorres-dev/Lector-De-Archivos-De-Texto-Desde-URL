/**
 * 
 */
package App;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.DefaultEditorKit.PasteAction;

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
		
		obtenerYpegarContenidoDelPortapapeles();
		
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
			
			/*
			System.out.println(url.getFile());
			System.out.println(url.getAuthority());
			System.out.println(url.getContent());
			System.out.println(url.getDefaultPort());
			System.out.println(url.getHost());
			System.out.println(url.getPath());
			System.out.println(url.getPort());
			System.out.println(url.getProtocol());
			System.out.println(url.getQuery());
			System.out.println(url.getRef());
			System.out.println(url.getUserInfo());
			*/
			
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
	
	public void start_loading() {
		vista.loading.setVisible(true);
		System.out.println("loading...");
	}
	
	public void end_loading() {
		vista.loading.setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(vista.btn_recuperar_contenido)) {
			//JOptionPane.showMessageDialog(null, "Recuperando contenido ...");
			vista.textPane.setText("");
			start_loading();
			/**
			 * Esperamos un milisegundo antes de empezar la lectura del fichero para que no se superponga con la operación de poner el loader
			 */
			service.schedule(esperarAntesDeEmpezarOperacion, 1, TimeUnit.MILLISECONDS);
		}else if(e.getSource().equals(vista.btn_reemplazar)) {
			//JOptionPane.showMessageDialog(null, "Reemplazar");
			vista.url.setText("");
			service.schedule(esperarAntesDeEmpezarCopiadoDelPortapapeles, 5, TimeUnit.MILLISECONDS);
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
