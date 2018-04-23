/**
 * 
 */
package es.upm.dit.isst.proy.dao;

import es.upm.dit.isst.proy.dao.model.Contrato;

/**
 * @author dsuarezsouto
 *
 */
public interface ContratoDAO {
	public void createContrato(Contrato Contrato);
	public Contrato readContrato(int id);
	public void updateContrato(Contrato Contrato);
	public void deleteContrato(Contrato Contrato);
}
