//package fr.isika.cda.javaee.account.controller;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import fr.isika.cda.javaee.dao.PackDao;
//import fr.isika.cda.javaee.entity.platform.Pack;
//
//import java.io.Serializable;
//
//@Named
//@SessionScoped
//public class PackController implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//
//    private Pack Pack = new Pack();
//
//    @Inject
//    private PackDao PackDao;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("PackController bean initialized!");
//    }
//
//    public void createPack() {
//        try {
//            PackDao.createPack(Pack);
//            Pack = new Pack();
//        } catch (Exception e) {
//       
//            e.printStackTrace();
//        }
//        
//       
//    }
//
//	
//
//}
