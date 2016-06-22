package ve.com.gem.services;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Guava;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import ve.com.gem.entities.Gem;
import ve.com.gem.repositories.IGemRepository;

@Service
public class GemService implements IGemService {
	
	@Autowired
	IGemRepository gemRepository;
    List<Gem> gems = new ArrayList<Gem>();
    
	public GemService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Gem> findAll() {
		gems = Lists.newArrayList(gemRepository.findAll());
	return gems;
	}

	@Override
	public List<Gem> search(String key) {
		return gemRepository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Override
	public Gem save(Gem gem) {
		if(null != gem)
		{
			if(null == gem.getCreatedAt())
				gem.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
			gemRepository.save(gem);
		}
		
		return gem;
	}

	@Override
	public Gem findById(Long id) {
		Gem gem = gemRepository.findOne(id);
		return gem;
	}
}
