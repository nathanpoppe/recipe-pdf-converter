public class Recipe {

    private String name;
    private String type;
    private String supplier;
    private String[] ingredients;
    private String[] instructions;
    private int prepTime; // minutes
    private int cookTime; // minutes
    private int totalTime; // minutes
    private String servings;

    public Recipe(String name, String type, String supplier, String[] ingredients, String[] instructions, int prepTime, int cookTime, String servings){
        this.name = name;
        this.type = type;
        this.supplier = supplier;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = prepTime + cookTime;
        this.servings = servings;

        RecipeBook.addRecipeToBook(this);
    }

    public String saveString() {
        String recipe = name + "\n" + type + "\n" + supplier + "\n" + prepTime + "\n" + cookTime + "\n" + servings +"\n";
        for(int i = 0; i < ingredients.length; i++)
            recipe += ingredients[i] + "\n";
        recipe += "/next\n";
        for(int i = 0; i < instructions.length; i++)
            recipe += instructions[i] + "\n";
        recipe += "/next\n";

        return recipe;
    }

    public String toString(){
        String recipe = "";
        recipe += name + "\n";
        recipe += "Ingredients:\n";
        for(int i = 0; i < ingredients.length; i++){
            recipe += ingredients[i] + "\n";
        }
        recipe += "Instructions:\n";
        for(int i = 0; i < instructions.length; i++){
            recipe += (i+1) + ". " + instructions[i] + "\n";
        }
        recipe += "Prep Time: " + prepTime + " minutes\n";
        recipe += "Cook Time: " + cookTime + " minutes\n";
        recipe += "Total Time: " + totalTime + " minutes\n";
        recipe += "Servings: " + servings + "\n";
        recipe += "Supplied by " + supplier + "\n";
        return recipe;
    }

    public String getName(){ return name; }
    public String getType(){ return type; }
    public String getSupplier(){ return supplier; }
    public String[] getIngredients(){ return ingredients; }
    public String[] getInstructions(){ return instructions; }
    public int getPrepTime(){ return prepTime; }
    public int getCookTime(){ return cookTime; }
    public String getServings(){ return servings; }
}
