import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.Scanner;

public class UserInput {

    private static boolean createRecipes = true;

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure(); // initializes the PDFWriter

        RecipeBookReader bookReader = new RecipeBookReader();
        PDFWriter pdfWriter = new PDFWriter();
        RecipeBookWriter bookWriter = new RecipeBookWriter();

        bookReader.readRecipeBook(); // read in saved recipes from text file
        pdfWriter.createPdf(RecipeBook.recipeBook); // create Recipe Book pdf file

        Scanner scanner = new Scanner(System.in);
        while(createRecipes) {
            createRecipe(scanner);
            pdfWriter.createPdf(RecipeBook.recipeBook);
            bookWriter.writeRecipeBook(RecipeBook.recipeBook);
        }
        scanner.close();


        //new RecipeBookWriter().writeRecipeBook(RecipeBook.recipeBook);
    }

    private static String getInput(String print, Scanner scanner){
        System.out.print(print);
        String input = scanner.nextLine();
        return input;
    }

    private static void createRecipe(Scanner scanner) {

        String name = "";
        String type = "";
        String supplier = "";
        String[] ingredients = new String[0];
        String[] instructions = new String[0];
        int prepTime = 0;
        int cookTime = 0;
        String servings = "";

        name = getInput("Recipe Name: ", scanner);
        type = getInput("Recipe Type: ", scanner);
        supplier = getInput("Recipe Supplier: ", scanner);

        String input = "";
        int num = 1;
        while(!input.equals("next")) {
            input = getInput("Ingredient " + num + ":", scanner);
            if(!input.contentEquals("next"))
                ingredients = addString(ingredients, input);
            num++;
        }
        input = "";
        num = 1;
        while(!input.equals("next")) {
            input = getInput("Instruction " + num + ":", scanner);
            if(!input.contentEquals("next"))
                instructions = addString(instructions, input);
            num++;
        }
        prepTime = Integer.parseInt(getInput("Prep Time: ", scanner));
        cookTime = Integer.parseInt(getInput("Cook Time: ", scanner));
        servings = getInput("Servings: ", scanner);

        new Recipe(name, type, supplier, ingredients, instructions, prepTime, cookTime, servings);
        System.out.println("New Recipe: " + name + " created!\n");

    }

    private static String[] addString(String[] array, String element) {
        String[] newArray = new String[array.length + 1];
        for(int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[newArray.length - 1] = element;
        return newArray;
    }

}

