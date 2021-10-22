import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;


public class PDFWriter {

    private static final String PDF_DEST = "RecipeBook.pdf";

    private static final String ROBOTO_BOLD = "src/main/resources/fonts/Roboto-Bold.ttf";
    private static final String ROBOTO_MEDIUM = "src/main/resources/fonts/Roboto-Medium.ttf";
    private static final String ROBOTO_LIGHT = "src/main/resources/fonts/Roboto-Light.ttf";
    private static final String BASKERVILLE_REG = "src/main/resources/fonts/LibreBaskerville-Regular.otf";

    
    public void createPdf(ArrayList<Recipe> recipeList) throws IOException {
        if(recipeList.size() > 0) {
            PdfWriter writer = new PdfWriter(PDF_DEST);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for(int i = 0; i < recipeList.size(); i++) {
                createPDFRecipe(recipeList.get(i), document);
                if(i != recipeList.size() - 1) {
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                }
            }
            document.close();
        }
    }

    private void createPDFRecipe(Recipe recipe, Document document) throws IOException {

        document.add(setRecipeName(recipe.getName()));
        document.add(setRecipeSupplier(recipe.getSupplier()));
        document.add(space(2));
        document.add(setRecipeTimes(recipe.getPrepTime(), recipe.getCookTime(), document));
        document.add(space(6));
        document.add(setLabel("ingredients"));
        document.add(setIngredients(recipe.getIngredients()));
        document.add(space(6));
        document.add(setLabel("instructions"));
        for(int i = 0; i < recipe.getInstructions().length; i++) {
            document.add(setInstruction(recipe.getInstructions()[i], i + 1));
        }
        document.add(space(1));
        document.add(setServings(recipe.getServings()));

    }

    private Paragraph setServings(String servings) throws IOException {

        Paragraph paragraph = new Paragraph("Servings: " + servings)
                .setFont(createFont(BASKERVILLE_REG))
                .setFontSize(10);

        return paragraph;
    }

    private Paragraph space(int space) {
        return new Paragraph("\n").setFontSize(space);
    }

    private Paragraph setInstruction(String instruction, int num) throws IOException{
        PdfFont baskerville = createFont(BASKERVILLE_REG);

        Paragraph paragraph = new Paragraph(num + ". " + instruction)
                .setFont(baskerville)
                .setFontSize(10);


        return paragraph;
    }


    private Table setIngredients(String[] ingredients) throws IOException{
        PdfFont baskerville = createFont(BASKERVILLE_REG);

        Table table = new Table(2);
        for(int i = 0; i < ingredients.length; i++) {
            int ingredientNum = i % 2 * ((ingredients.length + 1) / 2) + (i / 2);
            Cell cell = new Cell()
                    .add(new Paragraph(ingredients[ingredientNum]))
                    .setFont(baskerville)
                    .setFontSize(11)
                    .setBorder(Border.NO_BORDER);
            table.addCell(cell);
        }

        return table;
    }

    private Paragraph setRecipeName(String recipeName) throws IOException {

        PdfFont robotoBold = createFont(ROBOTO_BOLD);


        Paragraph paragraph = new Paragraph(recipeName.toUpperCase())
                .setFont(robotoBold)
                .setFontSize(40)
                .setCharacterSpacing(5)
                .setTextAlignment(TextAlignment.CENTER);

        return paragraph;
    }

    private Paragraph setRecipeSupplier(String recipeSupplier) throws IOException {

        PdfFont robotoLight = createFont(ROBOTO_LIGHT);

        Paragraph paragraph = new Paragraph("RECIPE SUPPLIED BY " + recipeSupplier.toUpperCase());
        if(recipeSupplier.charAt(0) == '*') {
            paragraph = new Paragraph(recipeSupplier.substring(1).toUpperCase() + "'S SPECIALTY");
        }
        paragraph.setFixedLeading(0)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(robotoLight)
                .setFontSize(12)
                .setCharacterSpacing(5);

        return paragraph;


    }

    private Table setRecipeTimes(int prepTime, int cookTime, Document document) throws IOException {

        PdfFont robotoMedium = createFont(ROBOTO_MEDIUM);

        Table table = new Table(3)
                .setWidth(624)
                .setTextAlignment(TextAlignment.CENTER);

        Cell prepCell = new Cell(0, 0)
                .add(new Paragraph("").setFixedLeading(8))
                .add(new Paragraph("PREP: " + prepTime + " MIN"))
                .add(new Paragraph("").setFixedLeading(8))
                .setCharacterSpacing(1)
                .setFont(robotoMedium)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new SolidBorder(0.5f))
                .setBorderBottom(new SolidBorder(0.5f));

        Cell cookCell = new Cell(1, 0)
                .add(new Paragraph("").setFixedLeading(8))
                .add(new Paragraph("COOK: " + cookTime + " MIN"))
                .add(new Paragraph("").setFixedLeading(8))
                .setCharacterSpacing(1)
                .setFont(robotoMedium)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);


        Cell totalCell = new Cell(2, 0)
                .add(new Paragraph("").setFixedLeading(8))
                .add(new Paragraph("TOTAL: " + (prepTime + cookTime) + " MIN"))
                .add(new Paragraph("").setFixedLeading(8))
                .setCharacterSpacing(1)
                .setFont(robotoMedium)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
                .setBorderTop(new SolidBorder(0.5f))
                .setBorderBottom(new SolidBorder(0.5f));

        table.addCell(prepCell);
        table.addCell(cookCell);
        table.addCell(totalCell);

        return table;
    }

    private Paragraph setLabel(String label) throws IOException {
        Paragraph paragraph = new Paragraph(label.toUpperCase())
                .setFont(createFont(ROBOTO_MEDIUM))
                .setFontSize(15)
                .setCharacterSpacing(3);

        return paragraph;
    }

    private PdfFont createFont(String fontName) throws IOException {
        FontProgram fontProgram = FontProgramFactory.createFont(fontName);
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);
        return font;
    }
}
