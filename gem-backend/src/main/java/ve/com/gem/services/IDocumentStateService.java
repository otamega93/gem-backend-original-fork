package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.DocumentState;

public interface IDocumentStateService {
	
	public Page<DocumentState> findAll(Pageable pageable);
	
	public DocumentState save(DocumentState documentState);

}
