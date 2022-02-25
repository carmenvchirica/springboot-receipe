package ch.springboot.receipe.services;

import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeServiceImpl;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void name() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeServiceImpl.getRecipies()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeServiceImpl.getRecipies();
        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }
}