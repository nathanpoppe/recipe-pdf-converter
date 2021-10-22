import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeBookWriter {

    public void writeRecipeBook(ArrayList<Recipe> recipeBook){

        try{
            FileWriter writer = new FileWriter("saved_recipes.txt");
            for(int i = 0; i < recipeBook.size(); i++){
                writer.write(recipeBook.get(i).saveString());
            }
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
