package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;


public interface ToolRepository extends JpaRepository<Tool, Long> {
}
