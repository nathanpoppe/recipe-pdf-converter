import java.util.ArrayList;

public abstract class RecipeBook {

    static ArrayList<Recipe> recipeBook = new ArrayList<Recipe>();
    static String[] recipeOrder = new String[]{"Breakfasts", "Appetizers", "Sides", "Dinners", "Treats"};

    public static void addRecipeToBook(Recipe recipe){
        insertRecipe(recipe);
    }

    private static void insertRecipe(Recipe recipe){
        int count = 0;
        while (count < recipeBook.size() && returnFirst(recipe, recipeBook.get(count)) != recipe) {
            count++;
        }
        recipeBook.add(count, recipe);
    }

    private static Recipe returnFirst(Recipe recipe1, Recipe recipe2){
        if(getTypeInt(recipe2) < getTypeInt(recipe1)){
            return recipe2;
        }
        else if(getTypeInt(recipe2) > getTypeInt(recipe1)){
            return recipe1;
        }
        else{
            return alphabetize(recipe1, recipe2);
        }
    }


    public static void printRecipeNames(){
        for(int i = 0; i < recipeBook.size(); i++){
            System.out.println(recipeBook.get(i).getName());
        }
    }

    public static void printRecipe(String name){
        for(int i = 0; i < recipeBook.size(); i++){
            if(recipeBook.get(i).getName().equals(name)){
                System.out.println(recipeBook.get(i));
            }
        }
    }

    private static int getTypeInt(Recipe recipe1){
        int returnType = -1;
        for(int i = 0; i < recipeOrder.length; i++){
            if(recipeOrder[i].equals(recipe1.getType())){
                returnType = i;
            }
        }
        return returnType;
    }

    // returns recipe that's name comes first in alphabetical order
    public static Recipe alphabetize(Recipe recipe1, Recipe recipe2){
        if(recipe1.getName().compareToIgnoreCase(recipe2.getName()) < 0)
            return recipe1;
        else
            return recipe2;
    }

}

