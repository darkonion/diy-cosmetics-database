package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.stereotype.Service;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.ToolRepository;

import java.util.List;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {this.toolRepository = toolRepository;}


    public List<Tool> getToolsList() {
        return toolRepository.findAll();
    }

    public Tool addNewTool(Tool tool) {
        return toolRepository.save(tool);


    }

    public void deleteToolById(Long id) {
        if (toolRepository.existsById(id)) {
            toolRepository.deleteById(id);
        } else {
            throw new NotFoundException("Tool with id: " + id + " already does not exist");
        }
    }

    public Tool updateTool(Tool tool) {
        Long id = tool.getId();
        if (toolRepository.existsById(id)) {
            return toolRepository.save(tool);
        } else {
            throw new NotFoundException("Tool with id: " + id + " does not exist - cannot be updated!");
        }
    }

}
