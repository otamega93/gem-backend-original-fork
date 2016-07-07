package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.resources.assembler.DocumentStateResourceAssembler;
import ve.com.gem.services.IDocumentStateService;

@RestController
@RequestMapping(value = "/api/v1/documentStates")
public class DocumentStateController {
	
	@Autowired
	private IDocumentStateService documentStateService;

	@Autowired
	private DocumentStateResourceAssembler documentStateResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<DocumentState> pageAssembler;
	

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<DocumentStateResource> saveAccount(@RequestBody DocumentState documentState) {
		if (null != documentState) {
			documentStateService.save(documentState);
			return new ResponseEntity<DocumentStateResource>(documentStateResourceAssembler.toResource(documentState), HttpStatus.OK);
		}
		else 
			return new ResponseEntity<DocumentStateResource>(documentStateResourceAssembler.toResource(documentState), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<DocumentStateResource> loadAll(Pageable pageable) {

		Page<DocumentState> documentStates = documentStateService.findAll(pageable);
		return pageAssembler.toResource(documentStates, documentStateResourceAssembler);
	}
}
