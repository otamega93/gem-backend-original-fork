package ve.com.gem.services;

import java.util.List;

import ve.com.gem.entities.Gem;

public interface IGemService {
	 List<Gem> findAll();
	 List<Gem> search(String key);
	 void addByName(String name);
	 Gem save (Gem gem);
	Gem findById(Long id);
	 
}
