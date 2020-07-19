package dao;

import entidades.Usuario;
import exceptions.DAOErrorDeCierreBDException;
import exceptions.DAOErrorDeConexionBDException;
import exceptions.DAONoHayResultadosException;

public interface UsuarioDAO {
	
	public Usuario consultarUsuario(String usuario, String contrase�a) throws DAONoHayResultadosException, DAOErrorDeConexionBDException, DAOErrorDeCierreBDException; 
	
}
