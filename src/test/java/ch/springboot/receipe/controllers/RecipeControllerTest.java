package ch.springboot.receipe.controllers;

import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.services.RecipeService;
import ch.springboot.receipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {
    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeController = new RecipeController(recipeService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipes/index"));

    }

    @Test
    void getIndexPage() {

        //given
        Set<Recipe> recipes = new HashSet<>();

        Recipe re1 = new Recipe();
        re1.setId(23l);
        recipes.add(re1);

        Recipe re2 = new Recipe();
        re2.setId(6l);
        recipes.add(re2);
        when(recipeService.getRecipies()).thenReturn(recipes);

        // Capture
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = recipeController.getIndexPage(model);

        //then
        assertEquals("/recipes/index", viewName);
        verify(recipeService, times(1)).getRecipies();
        verify(model,times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }
}