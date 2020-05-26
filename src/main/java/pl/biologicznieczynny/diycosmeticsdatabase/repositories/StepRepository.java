package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
}
