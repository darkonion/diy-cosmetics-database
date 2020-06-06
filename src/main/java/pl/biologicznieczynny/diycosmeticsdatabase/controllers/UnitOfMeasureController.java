package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UnitOfMeasureController {

    private final UnitOfMeasureRepository repository;

    public UnitOfMeasureController(UnitOfMeasureRepository repository) {this.repository = repository;}

    @GetMapping("/units")
    public List<UnitOfMeasure> getUomsList() {
        log.info("Getting full list of Units of Measure");
        return repository.findAll();
    }
}
