package ch.springboot.receipe.services;

import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(id).get();
            byte[] bytes = file.getBytes();
            Byte[] byteObj = new Byte[bytes.length];
            int i = 0;
            // convert from java primitive byte to java object Byte
            for(byte b : bytes) {
                byteObj[i++] = b;
            }

            recipe.setImage(byteObj);
            recipeRepository.save(recipe);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
