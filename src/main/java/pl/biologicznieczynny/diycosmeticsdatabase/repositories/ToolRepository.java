package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Page<Tool> findByRecipesId(@RequestParam("id") Long id, Pageable pageable);
}
