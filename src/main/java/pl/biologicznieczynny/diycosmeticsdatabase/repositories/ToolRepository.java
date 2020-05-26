package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
