package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.ControllerExceptionHandler;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.services.ToolService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ToolControllerTest {

    @Mock
    ToolService toolService;

    ToolController toolController;

    MockMvc mockMvc;

    Tool testTool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        toolController = new ToolController(toolService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(toolController)
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();

        prepareTool();
    }

    private void prepareTool() {
        testTool = new Tool();
        testTool.setId(1L);
        testTool.setName("test");
        testTool.setDescription("test");
    }

    @Test
    void getToolList() throws Exception {
        //given
        List<Tool> tools = new ArrayList<>();
        tools.add(testTool);

        //when
        when(toolService.getToolsList()).thenReturn(tools);

        //then
        mockMvc.perform(get("/api/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getToolListEmpty() throws Exception {
        //given
        List<Tool> tools = new ArrayList<>();

        //when
        when(toolService.getToolsList()).thenReturn(tools);

        //then
        mockMvc.perform(get("/api/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void addNewTool() throws Exception {

        //given
        String json = new ObjectMapper().writeValueAsString(testTool);

        //when
        when(toolService.addNewTool(any(Tool.class))).thenReturn(testTool);

        //then
        mockMvc.perform(post("/api/tools")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(toolService, times(1)).addNewTool(any(Tool.class));
    }

    @Test
    void addNewToolExceptionEmptyBody() throws Exception {
        //then
        mockMvc.perform(post("/api/tools")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.debugMessage")
                        .value(containsString("Required request body is missing")));

        verify(toolService, never()).addNewTool(any(Tool.class));
    }

    @Test
    void deleteTool() throws Exception {

        mockMvc.perform(delete("/api/tools/1"))
                .andExpect(status().isOk());

        verify(toolService, times(1)).deleteToolById(1L);
    }

    @Test
    void updateTool() throws Exception {

        //given
        String json = new ObjectMapper().writeValueAsString(testTool);

        //when
        when(toolService.updateTool(any(Tool.class))).thenReturn(testTool);

        //then
        mockMvc.perform(put("/api/tools")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"));

        verify(toolService, times(1)).updateTool(any(Tool.class));
    }

    @Test
    void updateToolFailWrongId() throws Exception {

        //given
        String json = new ObjectMapper().writeValueAsString(testTool);

        //when
        when(toolService.updateTool(any(Tool.class)))
                .thenThrow(new NotFoundException("Tool with id: 1 does not exist - cannot be updated!"));

        //then
        mockMvc.perform(put("/api/tools")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.debugMessage")
                        .value(containsString("Tool with id: 1 does not exist")));

        verify(toolService, times(1)).updateTool(any(Tool.class));
    }
}
