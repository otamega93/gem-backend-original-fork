package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Gem;

public interface IGemService {
	 
	 Page<Gem> findAll(Pageable pageable);
	 Page<Gem> findAll(Sort sort);
	 List<Gem> search(String key);
	 void addByName(String name);
	 Gem save (Gem gem);
	 Gem findById(Long id);
	boolean delete(Gem gemCatch);
	 
}
