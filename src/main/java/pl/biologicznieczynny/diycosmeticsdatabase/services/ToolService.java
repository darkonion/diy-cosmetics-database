package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
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

        return ResponseEntity
                .created(uri)
                .body(savedTool);
    }

    public void deleteToolById(Long id) {
        if (toolRepository.existsById(id)) {
            toolRepository.deleteById(id);
        } else {
            throw new NotFoundException("Tool with id: " + id + " already does not exist");
        }
    }

    public ResponseEntity<Tool> updateTool(Tool tool) {
        Long id = tool.getId();
        if (toolRepository.existsById(id)) {
            Tool updatedTool = toolRepository.save(tool);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(updatedTool.getId())
                    .toUri();

            return ResponseEntity
                    .created(uri)
                    .body(updatedTool);
        } else {
            throw new NotFoundException("Tool with id:" + id + " does not exist - cannot be updated!");
        }
    }

}
