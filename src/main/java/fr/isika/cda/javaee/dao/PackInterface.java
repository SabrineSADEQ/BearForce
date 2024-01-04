package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.platform.Pack;

public interface PackInterface {
	
	
    Pack getPack(Long id);
	
	//Pack getPack(String strId);
	
	List<Pack> getPacks();
	
	void createPack(Pack Pack);
	
	//void updatePack(Pack Pack);
	
	
	//void deletePack(int id);

}
