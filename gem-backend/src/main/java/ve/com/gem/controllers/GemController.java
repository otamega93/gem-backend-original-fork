package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ve.com.gem.entities.Gem;
import ve.com.gem.resources.GemResource;
import ve.com.gem.resources.assembler.GemResourceAssembler;
import ve.com.gem.services.IGemService;

@RestController
@RequestMapping(value = "/api/v1/gems")
public class GemController {
	
	@Autowired
	IGemService gemService;
	
	@Autowired
	private GemResourceAssembler gemResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<Gem> pageAssembler;
	
	public GemController() {
	}
	
	@RequestMapping(value="",method=RequestMethod.OPTIONS,produces="application/json")
	@ResponseBody
	public ResponseEntity<Gem> options(){
		ResponseEntity<Gem> response = new ResponseEntity<Gem>(HttpStatus.ACCEPTED);
		response.getHeaders().setAccessControlAllowOrigin("*");
		return response;
	}
	
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public PagedResources<GemResource> loadAll(Pageable pageable){
		
		Page<Gem> gems = gemService.findAll(pageable);
		//List<Gem> gems = gemService.findAll();
		//return new ResponseEntity<PagedResources<GemResource>>(gems,HttpStatus.OK);

		return pageAssembler.toResource(gems, gemResourceAssembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<GemResource> load(@PathVariable Long id)
	{
		Gem gem = gemService.findById(id);
		if(null == gem)
		{
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gem),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gem),HttpStatus.OK);
		}
	}
	
	
    
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@CrossOrigin(origins = "*")
	public ResponseEntity<GemResource> save(@RequestBody Gem gem)
	{
		if(gemService.save(gem)!=null)
		{
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gem),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gem),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	@CrossOrigin(origins = "*")
	public ResponseEntity<GemResource> update(@PathVariable Long id,@RequestBody Gem gem)
	{
		Gem gemCatch = gemService.findById(id);
		if(null == gemCatch)
		{
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gemCatch),HttpStatus.NOT_FOUND);
		}else
		
		if(null != gem )
		{
			gem.setId(id);
			gemService.save(gem);
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gemCatch),HttpStatus.OK);
		}
		else
			return new ResponseEntity<GemResource>(gemResourceAssembler.toResource(gemCatch),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Gem> delete(@PathVariable Long id){
		Gem gemCatch = gemService.findById(id);
		System.out.println(gemCatch);
		if(null != gemCatch)
		{
			gemService.delete(gemCatch);
			return new ResponseEntity<Gem>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Gem>(HttpStatus.BAD_REQUEST);
		}
	}
}