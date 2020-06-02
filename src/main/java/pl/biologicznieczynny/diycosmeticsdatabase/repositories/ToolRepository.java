package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@CrossOrigin
@RepositoryRestResource(exported = false)
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
