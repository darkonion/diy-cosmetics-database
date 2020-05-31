package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@RepositoryRestResource(exported = false)
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
