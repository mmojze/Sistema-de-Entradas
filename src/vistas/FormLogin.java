package vistas;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Usuario;
import exceptions.ErrorConexionBDException;
import exceptions.NoSeEncontroUsuarioException;
import exceptions.ServiceErrorDeConexionBDException;
import exceptions.ServiceErrorEjecucionSentenciaException;
import exceptions.ServiceNoHayDatosException;
import servicios.UsuarioService;

import javax.swing.*;

public class FormLogin extends JPanel implements ActionListener {

	private JLabel lUsuario;
	private JTextField tUsuario;
	private JLabel lContrase�a;
	private JPasswordField tContrase�a;
	private JButton login;
	private JFrame popupLogin;
	private PanelManager panelManager;

	public FormLogin(PanelManager m) {

		this.panelManager = m;

	}

	public void armarFormLogin() {

		this.setLayout(null);
		this.setBounds(300, 90, 500, 600);

		lUsuario = new JLabel("Usuario");
		lUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		lUsuario.setSize(100, 20);
		lUsuario.setLocation(130, 200);
		this.add(lUsuario);

		tUsuario = new JTextField();
		tUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		tUsuario.setSize(150, 20);
		tUsuario.setLocation(200, 200);
		this.add(tUsuario);

		lContrase�a = new JLabel("Contrase�a");
		lContrase�a.setFont(new Font("Arial", Font.PLAIN, 15));
		lContrase�a.setSize(100, 20);
		lContrase�a.setLocation(110, 250);
		this.add(lContrase�a);

		tContrase�a = new JPasswordField();
		tContrase�a.setFont(new Font("Arial", Font.PLAIN, 15));
		tContrase�a.setSize(150, 20);
		tContrase�a.setLocation(200, 250);
		this.add(tContrase�a);

		login = new JButton("Login");
		login.setFont(new Font("Arial", Font.PLAIN, 15));
		login.setSize(120, 20);
		login.setLocation(190, 300);
		login.addActionListener(this);
		this.add(login);

		popupLogin = new JFrame();

		setVisible(true);

	}

	public void actionPerformed(ActionEvent accion) {

		if (accion.getSource() == login) {

			String contrase�a = new String(tContrase�a.getPassword());
			String usuario = tUsuario.getText();

			UsuarioService servicio = new UsuarioService();

			Usuario usuarioLogeado;
			try {
				usuarioLogeado = servicio.logearse(tUsuario.getText(), contrase�a);
				String tipoUsuario = usuarioLogeado.getTipoUsuario();
				switch (tipoUsuario) {
				case "Administrador":
					panelManager.mostrarFormHomeAdmin(usuarioLogeado);
					break;
				case "Vendedor":
					panelManager.mostrarFormHomeVendedor();
					break;
				}
			} catch (ServiceNoHayDatosException e) {
				JOptionPane.showMessageDialog(popupLogin,
						"El usuario no existe o la contrase�a es incorrecta, por favor revise los datos");
			} catch (ServiceErrorDeConexionBDException | ServiceErrorEjecucionSentenciaException e) {
				JOptionPane.showMessageDialog(popupLogin, "Hubo un error de conexi�n a la base de datos");
			}

		}

	}
}
