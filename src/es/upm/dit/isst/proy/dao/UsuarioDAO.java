/**
 * 
 */
package es.upm.dit.isst.proy.dao;

import es.upm.dit.isst.proy.dao.model.Usuario;

/**
 * @author dsuarezsouto
 *
 */
public interface UsuarioDAO {
	public void createUsuario(Usuario usuario);
	public Usuario readUsuario(String email);
	public void updateUsuario(Usuario usuario);
	public void deleteUsuario(Usuario usuario);
	public Usuario loginUsuario(String email, String password);
	//public List<Professor> readAllProfessor();

}
