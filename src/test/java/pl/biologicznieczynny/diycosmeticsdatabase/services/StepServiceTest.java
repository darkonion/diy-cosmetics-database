package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.StepRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class StepServiceTest {

    @Mock
    StepRepository stepRepository;

    @Mock
    RecipeRepository recipeRepository;

    StepService stepService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        stepService = new StepService(stepRepository, recipeRepository);
    }

    Set<Step> generateSteps(int firstSeq, int secondSeq) {
        Set<Step> steps = new HashSet<>();

        Step test1 = new Step();
        test1.setId(1L);
        test1.setSeq(firstSeq);

        Step test2 = new Step();
        test2.setId(2L);
        test2.setSeq(secondSeq);

        steps.add(test1);
        steps.add(test2);

        return steps;
    }

    @ParameterizedTest(name = "For a given steps order - step 1: {0}, step 2: {1}, added step should have order of {2}")
    @CsvSource({
            "1, 2, 3",
            "3, 1, 4",
            "0, 0, 1",
            "-1, -2, 1",
            "99, 1, 100"
    })
    void addNewStepToRecipe(int s1, int s2, int s3) {

        //given
        Recipe testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.setSteps(generateSteps(s1, s2));

        Step test3 = new Step();
        test3.setName("testName");


        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(testRecipe));
        when(recipeRepository.save(any(Recipe.class))).then(AdditionalAnswers.returnsFirstArg());

        //when
        Recipe updatedRecipe = stepService.addNewStepToRecipe(anyLong(), test3);

        Step addedStep = null;
        for (Step step : updatedRecipe.getSteps()) {
            if (step.getName() != null) {
                addedStep = step;
            }
        }

        //then
        assertEquals(s3, addedStep.getSeq());

        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(recipeRepository, times(1)).findById(anyLong());
        verifyNoInteractions(stepRepository);
    }
}
