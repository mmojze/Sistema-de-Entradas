package entidades;

import java.util.List;

import dao.UsuarioDAO;
import dao.UsuarioDAOH2;
import exceptions.DAOErrorDeCierreBDException;
import exceptions.DAOErrorDeConexionBDException;
import exceptions.DAONoHayResultadosException;
import exceptions.ErrorConexionBDException;
import exceptions.NoSeEncontroUsuarioException;

public class Usuario {

	private String nombre;
	private String apellido;
	private String usuario;
	private String tipoUsuario;

	public Usuario(String nombre, String apellido, String usuario, String tipoUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {

	}

	public String getTipoUsuario() {

		return this.tipoUsuario;

	}

	public String getNombre() {

		return this.nombre;

	}

	public String getApellido() {

		return this.apellido;

	}

	public String getUsuario() {

		return this.usuario;

	}

	public void setTipoUsuario(String tipoUsuario) {

		this.tipoUsuario = tipoUsuario;

	}

	public void setNombre(String nombre) {

		this.nombre = nombre;

	}

	public void setApellido(String apellido) {

		this.apellido = apellido;

	}

	public void setUsuario(String usuario) {

		this.usuario = usuario;

	}

	public Usuario logearse(String usuario, String contrase�a) throws NoSeEncontroUsuarioException, ErrorConexionBDException {

		UsuarioDAO usuarioLogin = new UsuarioDAOH2();
		Usuario usuarioLogeado = new Usuario();
		try {

			usuarioLogeado = usuarioLogin.consultarUsuario(usuario, contrase�a);

		} catch (DAONoHayResultadosException a) {
			throw new NoSeEncontroUsuarioException();
		} catch (DAOErrorDeConexionBDException | DAOErrorDeCierreBDException b) {
			throw new ErrorConexionBDException();
		}

		return usuarioLogeado;

	}

}
