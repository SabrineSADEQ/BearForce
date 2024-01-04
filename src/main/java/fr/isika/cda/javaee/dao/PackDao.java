//package fr.isika.cda.javaee.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import fr.isika.cda.javaee.entity.platform.Pack;
//import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;
//
//
//@Stateless
//public class PackDao implements PackInterface{
//
//	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	private List<Pack> packs = new ArrayList<>();
//	
//	@Override
//	public Pack getPack(Long id) {
//		
//		return entityManager.find(Pack.class, id);
//		
//	}
//
//	
//
//	@Override
//	public List<Pack> getPacks() {
//		
//		return entityManager.createQuery("SELECT p FROM Pack p", Pack.class)
//                .getResultList();
//	}
//
//	@Override
//	public void createPack(Pack pack) {
//		
//		packs.add(pack);
//        System.out.println("Configuration créée pour la plateforme : " + pack.getName()+pack.getPrice());
//		
//	}
//	
//	
//
//}
