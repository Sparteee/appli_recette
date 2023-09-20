package miniprojet.miniprojetv2.Repositories;

import miniprojet.miniprojetv2.ParseData;
import miniprojet.miniprojetv2.Models.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecepieRepo {
       private List<Recepie> recepies = new ArrayList<>();

    public RecepieRepo() {
    }

    public List<Recepie> getRecepies() {
        return recepies;
    }

    @Override
    public String toString() {
        return "RecepieRepo [recepies=" + recepies + "]";
    }

    /*
    * Question 3
    * Cette methode permet de lire le XML grâce a la classe ParseData, et de stocker
    * les donnees dans l'attribut de classe recepies
    */
    public void init(String filepath) throws Exception{
        File file = new File(filepath);
        ParseData parse = new ParseData();
        parse.getData(file);

        this.recepies = parse.getListReciepe(); // Récupération de la liste crée via le parse
    }

    /*
    * Question 4
    * @return : Liste contenant les titres des recettes
    */
    public List<String> listeTitreRecettes(){
        return recepies.stream().map(title -> title.getTitle()).collect(Collectors.toList());
    }

    /*
    * Question 5
    * @return : Liste contenant le nombre total d'oeufs utilisés
    */
    public Float nbTotalOeufs(){
       return recepies.stream().flatMap(recipe -> recipe.getIngredients().stream())
               .filter(ingredient -> ingredient.getName().contains("egg")).map(Ingredient::getAmount)
               .reduce(0f, Float::sum);

    }

    /*
    * Question 6
    * @return : Liste contenant les recettes utilisant l'huile d'olive
    */
    public List<String> recettesHuileOlive(){
        return recepies.stream().filter(recipe -> recipe.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getName().equals("olive oil")))
                .map(recipe -> recipe.getTitle()).collect(Collectors.toList());

    }

    /*
    * Question 7
    * @return : Liste contenant le nombre d'oeufs par recette
    */
   public List<String> nbOeufsParRecette(){
        return recepies.stream().map(recipe -> recipe.getId() + ":" + recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getName().contains("egg"))
                .map(i -> i.getAmount()).reduce(0f, Float::sum))
                .collect(Collectors.toList());
    }

    /*
    * Question 8
    * @return : Liste contenant les recettes fournissant moins de 500 calories
    */
    public List<String> moins500calories(){
        return recepies.stream().filter(recipe -> Integer.parseInt(recipe.getNutrition().getCalories()) < 500)
                .map(recipe -> recipe.getTitle()).collect(Collectors.toList());
    }

    /*
    * Question 9
    * @return : Liste contenant la quantite de sucre utilisee pour la Zuppa Inglese
    */
    public List<String> quantiteSucreZuppa(){
        return recepies.stream().filter(recepie -> recepie.getTitle().equals("Zuppa Inglese")).flatMap(recepie -> recepie.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().equals("sugar"))
                .map(i -> i.getAmount() + "/" + i.getUnit()).collect(Collectors.toList());
    }

    /*
    * Question 10
    * @return : Liste contenant les 2 premières étapes de la Zuppa Inglese
    */
    public List<String> deuxetapesZuppa(){
        return recepies.stream().filter(recepie -> recepie.getTitle().equals("Zuppa Inglese"))
                .map(recipe -> recipe.getSteps().stream().limit(2).collect(Collectors.joining("\n"))).collect(Collectors.toList());
    }

    /*
    * Question 11
    * @return : Liste contenant les recettes qui necessitent plus de 5 etapes
    */
    public List<String> necessite5etapes(){
        return recepies.stream().filter(recepie -> recepie.getSteps().size() > 5 ).map(r -> r.getTitle()).collect(Collectors.toList());
    }

    /*
    * Question 12
    * @return : Liste contenant les recettes qui ne contiennent pas de beurre
    */
    public List<String> recettesSansBeurre(){
        return recepies.stream().filter(recepie -> recepie.getIngredients().stream().noneMatch(ingredient -> ingredient.getName().contains("butter")))
                .map(r -> r.getTitle()).collect(Collectors.toList());
    }

    /*
    * Question 13
    * @return : Liste contenant les recettes qui ont des ingredients en commun avec la Zuppa Inglese
    */
    public List<String> recettesCommunesZuppa() {

        return recepies.stream().filter(recepie -> recepie.getIngredients().stream().anyMatch(i -> recepies.stream().filter(recepie2 -> recepie2.getTitle().contains("Zuppa Inglese"))
                        .flatMap(recepie2 -> recepie2.getIngredients().stream())
                        .anyMatch(i2 -> i2.getName().equals(i.getName())))).map(Recepie::getTitle).collect(Collectors.toList());
    }

    /*
    * Question 14
    * @return : Liste contenant la recette la plus calorique
    */
    public String recettePlusCalorique(){
        return recepies.stream().max(Comparator.comparing(recepie -> Integer.parseInt(recepie.getNutrition().getCalories()))).map(recepie -> recepie.getTitle()).get();
    }

    /*
    * Question 15
    * @return : Liste contenant l'unite la plus frequente
    */
    public List<String> UnitPlusFrequente() {
        return recepies.stream().flatMap(recepie -> recepie.getIngredients().stream())
                .map(Ingredient::getUnit).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(val -> val.getKey() + " : " + val.getValue()).stream().collect(Collectors.toList());
    }

    /*
    * Question 16
    * @return : Liste contenant le nombre d'ingredient par recette
    */
    public List<String> nbIngredientsParRecette(){
        return recepies.stream().map(recipe -> recipe.getId() + " : " + recipe.getIngredients().size()).collect(Collectors.toList());
    }

    /*
    * Question 17
    * @return : Liste contenant la recette la plus grasse
    */
    public String recettePlusFat(){
        return recepies.stream().max(Comparator.comparing(recepie -> recepie.getNutrition().getFat())).map(recepie -> recepie.getTitle()).get();
    }

    /*
    * Question 18
    * @return : Liste contenant l'ingredient le plus utilise
    */
    public List<String> ingredientPlusUtilise(){
        return recepies.stream().flatMap(recepie -> recepie.getIngredients().stream()).map(Ingredient::getName)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue)).map(titre -> titre.getKey() + " : " + titre.getValue()).stream().collect(Collectors.toList());
    }

    /*
    * Question 19
    * @return : Liste contenant les recettes triees par nombre d'ingredient
    */
    public List<String> recetteTrieeNbIngredients(){
        return recepies.stream()
                .sorted(Comparator.comparing(recepie -> recepie.getIngredients().size()))
                .map(titre -> titre.getId() + " : " + titre.getIngredients().size()).collect(Collectors.toList());
    }

    /*
    * Question 20
    * @return : Liste contenant les recettes utilisees par chaque ingredient
    */
    public List<String> IngredientsUtilisesRecettes(){
        return recepies.stream().flatMap(recepie -> recepie.getIngredients().stream())
                .map(Ingredient::getName).distinct().map(ingredient -> ingredient  + " : " + recepies.stream()
                .filter(r2 -> r2.getIngredients().stream().anyMatch(ingredient2 -> ingredient2.getName().equals(ingredient))).map(Recepie::getTitle)
                .collect(Collectors.joining(", "))).collect(Collectors.toList());
    }

    /*
    * Question 21
    * @return : Liste contenant la repartition des recettes par etape de realisation
    */
    public Map<Integer, List<Recepie>> repartitionRecetteEtape() {
        return recepies.stream().collect(Collectors.groupingBy(
                        r -> r.getSteps().size(),
                        Collectors.toList()
                ));
    }

    /*
    * Question 22
    * @return : Liste contenant la recette qui possede le moinds d'etape
    */
    public List<String> recettePlusFacile(){
        return recepies.stream().min(Comparator.comparing(recipe -> recipe.getSteps().size()))
                .map(recepie -> recepie.getId() + " : " + recepie.getSteps().size()).stream().collect(Collectors.toList());
    }

}
