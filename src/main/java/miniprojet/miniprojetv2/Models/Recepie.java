package miniprojet.miniprojetv2.Models;

import java.util.ArrayList;
import java.util.List;

public class Recepie {
    private String id;
    private String title;
    private String date;

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> steps = new ArrayList<>();

    private String comment;
    private Nutrition nutrition;
    private String related;


    /* #--------- CONSTRUCTOR ---------# */

    public Recepie() {

    }


    /* #--------- GETTERS ---------# */

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public String getComment() {
        return comment;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public String getRelated() {
        return related;
    }



    /* #--------- SETTERS ---------# */

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStep(String newSteps) {
        this.steps.add(newSteps);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public void setNutrition(Nutrition nutrition){
        this.nutrition = nutrition;
    }

    public void setIngredients(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /* #--------- TOSTRING ---------# */

    public void affichageRecette()
    {
        System.out.println("Id : " + this.id +
                            "\nTitle : " + this.title +
                           "\nDate : " + this.date +
                           "\nIngredient : " + this.ingredients +
                           "\nStep : " + this.steps +
                           "\nComment : " + this.comment +
                           "\nNutrition : " + this.nutrition +
                           "\nRelated : " + this.related);
    }

}