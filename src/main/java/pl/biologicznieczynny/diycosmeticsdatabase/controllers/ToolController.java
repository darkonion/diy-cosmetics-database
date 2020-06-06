package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.services.ToolService;

import java.net.URI;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {this.toolService = toolService;}


    //get's

    @GetMapping("/tools")
    public List<Tool> getToolList() {
        log.info("Getting full list of tools");
        return toolService.getToolsList();
    }

    @GetMapping("/tools/{id}")
    public Tool getToolById(@PathVariable Long id) {
        log.info("Getting tool by tool id: " + id);
        return this.toolService.findById(id);
    }

    //post's

    @PostMapping("/tools")
    public ResponseEntity<Tool> addNewTool(@RequestBody Tool tool) {
        log.info("Persisting new tool: " + tool.getName());
        Tool savedTool = toolService.addNewTool(tool);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTool.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedTool);
    }

    //delete's

    @DeleteMapping("/tools/{id}")
    public void deleteTool(@PathVariable Long id) {
        log.info("Deleting tool with id: " + id);
        toolService.deleteToolById(id);
    }

    //put's

    @PutMapping("/tools")
    public ResponseEntity<Tool> updateTool(@RequestBody Tool tool) {
        log.info("Updating tool:" + tool.getName());
        Tool updatedTool = toolService.updateTool(tool);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedTool.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(updatedTool);
    }
}
