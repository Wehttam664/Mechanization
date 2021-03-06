package io.vevox.mechanization.recipe;

import com.google.gson.JsonParseException;
import io.vevox.mechanization.Mechanization;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author Matthew Struble
 */
public class RecipeController {

    private static HashMap<String, Recipe> recipes;

    private RecipeController(){}

    public static void parseRecipes(){
        Mechanization.mechanization().getConsole().info("Parsing recipes...");
        File recipesDir = new File(Mechanization.mechanization().getConfigDirectory(), "recipes");
        if (!recipesDir.exists())
            Mechanization.mechanization().saveResource("recipes", true);
        parseRecipesDir("", recipesDir);
    }

    public static void parseRecipesDir(String prefix, File dir){
        if (!dir.isDirectory()) return;
        for (File file : dir.listFiles()){
            if (file.isDirectory())
                parseRecipesDir(file.getName() + ".", file);
            else {
                String name = prefix + FilenameUtils.removeExtension(file.getName());
                try {
                    Mechanization.mechanization().getConsole().info("> &e" + name + "&r...");
                    recipes.put(name, new Recipe(file));
                } catch (NullPointerException | FileNotFoundException | JsonParseException | IllegalStateException e) {
                    Mechanization.mechanization().getConsole().warn("Failed to parse recipe &e" + name);
                    Mechanization.mechanization().getConsole().warn(e.getMessage());
                }
            }
        }
    }

    public static Recipe recipe(String recipeID){
        return recipes.get(recipeID);
    }

}
