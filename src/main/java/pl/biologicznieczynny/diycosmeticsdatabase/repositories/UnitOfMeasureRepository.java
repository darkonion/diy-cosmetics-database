package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUom(String uom);
}
