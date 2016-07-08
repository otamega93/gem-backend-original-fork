package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.DocumentStateResource;

@Component
public class DocumentStateResourceAssembler extends ResourceAssemblerSupport<DocumentState, DocumentStateResource>{

	public DocumentStateResourceAssembler() {
		super(DocumentStateController.class, DocumentStateResource.class);
	}
	
	@Override
	public DocumentStateResource toResource(DocumentState documentState) {
		DocumentStateResource documentStateResource = new DocumentStateResource();
		documentStateResource.setName(documentState.getName());
		documentStateResource.setDescription(documentState.getDescription());
		documentStateResource.setCreatedAt(documentState.getCreatedAt());
		documentStateResource.setUpdatedAt(documentState.getUpdatedAt());
		documentStateResource.setDeletedAt(documentState.getDeletedAt());
		documentStateResource.setIds(documentState.getId());
		documentStateResource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withSelfRel());
	    documentStateResource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withRel("documentState"));
		return documentStateResource;
	}

	
}
