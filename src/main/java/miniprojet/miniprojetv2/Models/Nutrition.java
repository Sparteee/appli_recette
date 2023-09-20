package miniprojet.miniprojetv2.Models;

public class Nutrition {
    private String calories;
    private String fat;
    private String carbohydrates;
    private String protein;
    private String alcohol;

    public Nutrition(String calories, String fat, String carbohydrates, String protein) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return
                "calories='" + calories + '\'' +
                ", fat='" + fat + '\'' +
                ", carbohydrates='" + carbohydrates + '\'' +
                ", protein='" + protein + '\'' +
                ", alcohol='" + alcohol + '\'';
    }
}