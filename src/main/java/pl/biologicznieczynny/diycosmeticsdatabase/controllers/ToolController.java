package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.ToolRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToolController {

    private final ToolRepository toolRepository;

    public ToolController(ToolRepository toolRepository) {this.toolRepository = toolRepository;}

    @GetMapping("/tools")
    public List<Tool> getToolList() {
        return toolRepository.findAll();
    }
}
