package fr.isika.cda.javaee.account.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import fr.isika.cda.javaee.dao.PackDao;
import fr.isika.cda.javaee.entity.platform.Pack;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PackController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pack pack = new Pack();
   //private List<Pack> packs;

	@Inject
	private PackDao packDao;

	@PostConstruct
	public void init() {
		System.out.println("PackController bean initialized!");
	}

	public void createPack() {
		try {
			packDao.createPack(pack);
			pack = new Pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Pack> listePacks() {
		return packDao.getPacksWithSubscriptions();
	}
	
	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

//	public List<Pack> getPacks() {
//		return packs;
//	}
//
//	public void setPacks(List<Pack> packs) {
//		this.packs = packs;
//	}
	

}
