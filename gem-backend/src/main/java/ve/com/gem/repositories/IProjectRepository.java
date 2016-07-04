package ve.com.gem.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Project;

@Repository
public interface IProjectRepository extends PagingAndSortingRepository<Project, Long>{

}
