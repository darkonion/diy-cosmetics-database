package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;

import java.util.Optional;

@Repository
@CrossOrigin
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUom(String uom);
}
