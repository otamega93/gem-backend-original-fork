package ve.com.gem.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ve.com.gem.entities.Gem;
import ve.com.gem.services.IGemService;

@RestController
@RequestMapping(value = "/gems")
public class GemController {
	@Autowired
	IGemService gemService;
	public GemController() {
	}
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Gem>> loadAll(){
		List<Gem> gems = gemService.findAll();
		
		return new ResponseEntity<List<Gem>>(gems,HttpStatus.OK);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Gem> load(@PathVariable Long id)
	{
		Gem gem = gemService.findById(id);
		if(null == gem)
		{
			return new ResponseEntity<Gem>(gem,HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Gem>(gem,HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Gem> save(@RequestBody Gem gem)
	{
		if(gemService.save(gem)!=null)
		{
			return new ResponseEntity<Gem>(gem,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Gem>(gem,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Gem> update(@PathVariable Long id,@RequestBody Gem gem)
	{
		Gem gemCatch = gemService.findById(id);
		if(null == gemCatch)
		{
			return new ResponseEntity<Gem>(gem,HttpStatus.NOT_FOUND);
		}else
		
		if(null != gem )
		{
			gem.setId(id);
			gemService.save(gem);
			return new ResponseEntity<Gem>(gem,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Gem>(gem,HttpStatus.NOT_FOUND);
	}
}