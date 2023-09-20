package miniprojet.miniprojetv2;

import java.io.File;
import java.util.*;

import miniprojet.miniprojetv2.Models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseData  extends DefaultHandler {
// Utilisation de SAXParser pour lire le XML
    private List<Recepie> listReciepe = null; // Liste qui va accueillir les recettes présents dans le fichier XML
    private StringBuilder data = null;

    private Recepie recipe = null;
    private Nutrition nutrition = null;
    private Ingredient ingredient = null;

    public ParseData(){

    }

    /* Décalaration de tous les booleans de vérifications pour chaque élément de Recette */
    private boolean btitle= false;
    private boolean bdate= false;
    private boolean bComment= false;
    private boolean bIngredient= false;
    private boolean bStep= false;
    private boolean bNutri= false;
    private boolean bRelated = false;

    public List<Recepie> getListReciepe(){
        return listReciepe;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("rcp:recipe")){ // Lecture ligne par ligne - Vérification du nom de balise
            recipe = new Recepie();
            String id = attributes.getValue("id"); // Récupération de l'id de la recette
            recipe.setId(id); // Attribution de l'id à la recette qui est entrain d'être lu
            if(listReciepe == null){
                listReciepe = new ArrayList<>(); // Création de la liste
            }
        } else if (qName.equalsIgnoreCase("rcp:title")) {
            btitle = true;
        } else if (qName.equalsIgnoreCase("rcp:date")) {
            bdate = true;
        } else if (qName.equalsIgnoreCase("rcp:ingredient")) {
            String name = attributes.getValue("name"); // Recupération de l'attribut nom de l'ingrédient
            String amount = attributes.getValue("amount"); // Récupération de l'attribut amount de l'ingrédient
            float Vamount = 0;
            if(amount != null) { // Vérification du montant pour pouvoir le passer en Float car il y a des possibilités d'avoir des caractères comme *
                if (!amount.isEmpty()) {
                    if (amount.equals("*")) {
                        Vamount = 0; // Changement si amount = *
                    } else {
                        Vamount = Float.parseFloat(amount); // Parse en float du montant
                    }
                }
            }
            String unit = attributes.getValue("unit"); // récupération de l'attribut Unit sur l'ingredient
            String vunit;
            if(unit == null){ // Meme vérification car des unités peuvent être null donc remplacement par None
                vunit = "None";
            }
            else{
                vunit = unit;
            }
            ingredient = new Ingredient(name,Vamount,vunit); // Création de l'objet Ingrédient
            bIngredient = true;
        } else if (qName.equalsIgnoreCase("rcp:step")) {
            bStep = true;
        } else if (qName.equalsIgnoreCase("rcp:comment")) {
            bComment = true;
        } else if (qName.equalsIgnoreCase("rcp:nutrition")) {
            String calories = attributes.getValue("calories");
            String fat = attributes.getValue("fat");
            String carbo = attributes.getValue("carbohydrates");
            String protein = attributes.getValue("protein");
            nutrition = new Nutrition(calories,fat,carbo,protein); // Création de l'objet Nutrition avec tous les attributs récupérés précédemment
            bNutri = true;
        } else if (qName.equalsIgnoreCase("rcp:related")) {
            bRelated = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Ajout de tous les éléments récupérés à l'objet recette grâce aux Setters de Recipe
        if(qName.equalsIgnoreCase("rcp:recipe")){
            listReciepe.add(recipe); // Ajout de la recette à la liste
        }
        else if(btitle){
            recipe.setTitle(data.toString());
            btitle = false;
        } else if (bdate) {
            recipe.setDate(data.toString());
            bdate = false;
        } else if (bIngredient) {
            recipe.setIngredients(ingredient);
            bIngredient = false;
        } else if (bStep) {
            recipe.setStep(data.toString());
            bStep = false;
        } else if (bComment) {
            recipe.setComment(data.toString());
            bComment = false;
        } else if (bNutri) {
            recipe.setNutrition(nutrition);
            bNutri = false;
        } else if (bRelated) {
            recipe.setRelated(data.toString());
            bRelated = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch,start,length));
    }

    // Fonction getData qui appelle le parse SAXparser sur le fichier passé en argument
    public void getData(File file) throws Exception{
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(file, this);
    }
}
