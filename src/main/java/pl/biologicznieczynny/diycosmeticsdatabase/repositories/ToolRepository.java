package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@RepositoryRestResource(exported = false)
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Page<Tool> findByRecipesId(@RequestParam("id") Long id, Pageable pageable);
}
