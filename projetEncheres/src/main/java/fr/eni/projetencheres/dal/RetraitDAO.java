package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.BusinessException;
import fr.eni.projetencheres.bo.Retrait;

public interface RetraitDAO {
	public void insertRetrait (Retrait retrait) throws BusinessException;
	public void getRetraitByUtilisateurId(int utilisateurId)throws BusinessException;
	

}
