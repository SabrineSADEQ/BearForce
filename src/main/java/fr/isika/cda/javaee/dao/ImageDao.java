package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.presentation.viewmodel.ImageViewModel;

@Stateless
public class ImageDao {
	
	@PersistenceContext
	private EntityManager em;

	public void persistImage(ImageViewModel imageViewModel) {
		
		
		
	}
	
	
}
