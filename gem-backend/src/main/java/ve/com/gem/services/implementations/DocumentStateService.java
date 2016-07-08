package ve.com.gem.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.DocumentState;
import ve.com.gem.repositories.IDocumentStateRepository;
import ve.com.gem.services.IDocumentStateService;

@Transactional(readOnly = true)
@Service
public class DocumentStateService implements IDocumentStateService {

	@Autowired
	private IDocumentStateRepository documentStateRepository;
	
	private List<DocumentState> documentState = new ArrayList<DocumentState>();
	
	@Override
	public Page<DocumentState> findAll(Pageable pageable) {
		
		documentState = Lists.newArrayList(documentStateRepository.findAll(pageable));
		PageImpl<DocumentState> documentStatePages = new PageImpl<DocumentState>(documentState, pageable, documentStateRepository.count());
		return documentStatePages;
	}

	@Transactional(readOnly = false)
	@Override
	public DocumentState save(DocumentState documentState) {
		if (null !=  documentState) {
			return documentStateRepository.save(documentState);
		}
		else
			return null;
	}

}
