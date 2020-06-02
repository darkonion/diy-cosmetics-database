package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.ToolRepository;

import java.net.URI;
import java.util.List;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {this.toolRepository = toolRepository;}


    public List<Tool> getToolsList() {
        return toolRepository.findAll();
    }

    public ResponseEntity<Tool> addNewTool(Tool tool) {
        Tool savedTool = toolRepository.save(tool);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTool.getId())
                .toUri();

        ResponseEntity<Tool> response = ResponseEntity.created(uri).body(savedTool);
        return response;
    }

}
