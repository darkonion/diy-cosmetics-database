package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;

public interface StepRepository extends JpaRepository<Step, Long> {
}
