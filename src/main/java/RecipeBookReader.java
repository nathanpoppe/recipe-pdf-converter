import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecipeBookReader {

    private final static String SAVED_RECIPES_FILE = "src/main/resources/saved_recipes.txt";

    private String name = "";
    private String type = "";
    private String supplier = "";
    private String[] ingredients = new String[0];
    private String[] instructions = new String[0];
    private int prepTime = 0;
    private int cookTime = 0;
    private String servings = "";

    public void readRecipeBook() {
        try {
            Scanner scanner = new Scanner(new File(SAVED_RECIPES_FILE));
            int lineNum = 0;
            boolean onIngredients = true;
            while(scanner.hasNextLine()) {
                lineNum++;
                String nextLine = scanner.nextLine();
                switch(lineNum) {
                    case(1):
                        name = nextLine;
                        break;
                    case(2):
                        type = nextLine;
                        break;
                    case(3):
                        supplier = nextLine;
                        break;
                    case(4):
                        prepTime = Integer.parseInt(nextLine);
                        break;
                    case(5):
                        cookTime = Integer.parseInt(nextLine);
                        break;
                    case(6):
                        servings = nextLine;
                        break;
                    default:
                        if(nextLine.equals("/next")) {
                            if(onIngredients) {
                                onIngredients = false;
                            }
                            else {
                                lineNum = 0;
                                onIngredients = true;
                                new Recipe(name, type, supplier, ingredients, instructions, prepTime, cookTime, servings);
                                resetArrays();
                            }
                        }
                        else if(onIngredients) {
                            ingredients = addString(ingredients, nextLine);
                        }
                        else {
                            instructions = addString(instructions, nextLine);
                        }
                        break;
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void resetArrays() {
        ingredients = new String[0];
        instructions = new String[0];
    }

    private String[] addString(String[] array, String element) {
        String[] newArray = new String[array.length + 1];
        for(int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[newArray.length - 1] = element;
        return newArray;
    }



}

