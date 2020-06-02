package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UnitOfMeasureController {

    private final UnitOfMeasureRepository repository;

    public UnitOfMeasureController(UnitOfMeasureRepository repository) {this.repository = repository;}

    @GetMapping("/units")
    public List<UnitOfMeasure> getUomsList() {
        return repository.findAll();
    }
}
