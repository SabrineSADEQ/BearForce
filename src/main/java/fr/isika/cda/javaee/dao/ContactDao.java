package fr.isika.cda.javaee.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;

@Stateless
@LocalBean
public class ContactDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	
	
	public Contact getContactById(Long id) {
        return entityManager.find(Contact.class, id);
    }
	
	
	
}
