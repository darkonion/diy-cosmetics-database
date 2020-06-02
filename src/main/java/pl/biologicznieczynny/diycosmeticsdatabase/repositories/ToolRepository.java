package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@Repository
@CrossOrigin
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
