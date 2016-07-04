package ve.com.gem.services;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Guava;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.Gem;
import ve.com.gem.repositories.IGemRepository;

@Transactional(readOnly=true)
@Service
public class GemService implements IGemService {
	
	@Autowired
	IGemRepository gemRepository;
    List<Gem> gems = new ArrayList<Gem>();
    
	public GemService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Gem> findAll(Pageable pageable) {
		
		gems = Lists.newArrayList(gemRepository.findAll(pageable));
		PageImpl<Gem> pages= new PageImpl<>(gems,pageable,gemRepository.count());
		
	return pages;
	}
	

	@Override
	public List<Gem> search(String key) {
		return gemRepository.findByNameLike("%"+key+"%");
	}

	@Override
	public void addByName(String name) {
		// TODO Auto-generated method stub
	}

	@Transactional(readOnly=false)
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

	@Override
	public Page<Gem> findAll(Sort sort) {
		 PageImpl<Gem> pages= new PageImpl<Gem>(Lists.newArrayList(gemRepository.findAll(sort)));
		 return pages;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean delete(Gem gem) {
		Long id=0L;
		if(null != gem){
			System.out.println("No es nula.");
			id=gem.getId();
		}
		gemRepository.delete(id);
		System.out.println(gemRepository.exists(id));
		return !gemRepository.exists(id);
	}
}
