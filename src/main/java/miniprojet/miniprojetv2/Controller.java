package miniprojet.miniprojetv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import miniprojet.miniprojetv2.Repositories.RecepieRepo;

import java.util.stream.Collectors;

public class Controller {
    public Label explainMethod; // Label FXML - Permet d'expliquer les résultats
    @FXML
    private Button button; // Button FMXL - Bouton de validation
    @FXML
    private TextField textField; // TextField FMXL - Emplacement d'entrée des choix des utilisateurs
    @FXML private TextArea textArea; // TextArea FMXL - Emplacement d'affichage des résultats des méthodes
    @FXML
    protected void click(ActionEvent event) throws Exception {
        RecepieRepo recepieRepo = new RecepieRepo(); // Création de l'objet RecepieRepo
        recepieRepo.init("recipes.xml"); // Récupération des données présent dans le XML
        int nbQuestion = Integer.parseInt(textField.getText()); // Récupération du choix de l'utilisateur et passage en Integer pour simplifier le switch
        String resultat;
        textArea.setVisible(true); // Rendre visible l'affichage des données visible
        switch (nbQuestion){ // Switch par rapport aux choix de l'utilisateur
            case 1:
                explainMethod.setText("Liste des titres des recettes\n"); // Label - SetText
                resultat = String.join("\n",recepieRepo.listeTitreRecettes()); // Mise en string avec un retour à la ligne dans la variable resultat
                textArea.setText(resultat); // Affichage du resultat dans le textArea
                break;
            case 2:
                explainMethod.setText("Nombre total d'oeufs utilisés");
                resultat = String.valueOf(recepieRepo.nbTotalOeufs());
                textArea.setText(resultat);
                break;
            case 3 :
                explainMethod.setText("Liste des recettes utilisant de l'huile d'olive");
                resultat = String.join("\n", recepieRepo.recettesHuileOlive());
                textArea.setText(resultat);
                break;
            case 4 :
                explainMethod.setText("Nombre d'oeufs par recette");
                resultat = String.join("\n",  recepieRepo.nbIngredientsParRecette());
                textArea.setText(resultat);
                break;
            case 5 :
                explainMethod.setText("Recettes moins de 500 calories");
                resultat = String.join("\n", recepieRepo.moins500calories());
                textArea.setText(resultat);
                break;
            case 6 :
                explainMethod.setText("Quantité de sucre - Recette Zuppa Inglese");
                resultat = String.join("\n" , recepieRepo.quantiteSucreZuppa());
                textArea.setText(resultat);
                break;
            case 7 :
                explainMethod.setText("2 premières étapes - Zuppa Inglese");
                resultat = String.join("\n" ,recepieRepo.deuxetapesZuppa());
                textArea.setWrapText(true);
                textArea.setText(resultat);
                break;
            case 8 :
                explainMethod.setText("Recettes nécessitant plus de 5 étapes");
                resultat = String.join("\n" ,recepieRepo.necessite5etapes());
                textArea.setText(resultat);
                break;
            case 9 :
                explainMethod.setText("Recettes sans beurre");
                resultat = String.join("\n" ,recepieRepo.recettesSansBeurre());
                textArea.setText(resultat);
                break;
            case 10 :
                explainMethod.setText("Recettes ingrédients communs Zuppa Inglese");
                resultat = String.join("\n" ,recepieRepo.recettesCommunesZuppa());
                textArea.setText(resultat);
                break;
            case 11:
                explainMethod.setText("Recette la plus calorique");
                resultat = String.join("\n" ,recepieRepo.recettePlusCalorique());
                textArea.setText(resultat);
                break;
            case 12:
                explainMethod.setText("L'unité la plus fréquente");
                resultat = String.join("\n" ,recepieRepo.UnitPlusFrequente());
                textArea.setText(resultat);
                break;
            case 13 :
                explainMethod.setText("Nombre d'ingrédients par recette");
                resultat = String.join("\n" ,recepieRepo.nbIngredientsParRecette());
                textArea.setText(resultat);
                break;
            case 14 :
                explainMethod.setText("Recette comportant le plus de fat");
                resultat = String.join("\n" ,recepieRepo.recettePlusFat());
                textArea.setText(resultat);
                break;
            case 15 :
                explainMethod.setText("Ingrédient le plus utilisé");
                resultat = String.join("\n" ,recepieRepo.ingredientPlusUtilise());
                textArea.setText(resultat);
                break;
            case 16 :
                explainMethod.setText("Recettes triées par nombre d'ingrédients");
                resultat = String.join("\n" ,recepieRepo.recetteTrieeNbIngredients());
                textArea.setText(resultat);
                break;
            case 17 :
                explainMethod.setText("Liste ingredients utilisés pour chaque recette");
                resultat = String.join("\n" ,recepieRepo.IngredientsUtilisesRecettes());
                textArea.setText(resultat);
                break;
            case 18:
                explainMethod.setText("Répartition des recettes par étape de réalisation");
                resultat = recepieRepo.repartitionRecetteEtape().entrySet().stream()
                        .map(repart -> repart.getKey() + " étapes : " + repart.getValue().stream().map(recepie -> recepie.getTitle())
                                .collect(Collectors.joining(", ")))
                        .collect(Collectors.joining("\n")); // Amélioration du résultat de la fonction pour un meilleur affichage
                textArea.setText(resultat);
                break;
            case 19:
                explainMethod.setText("Recette la plus facile");
                resultat = String.join("\n" ,recepieRepo.recettePlusFacile());
                textArea.setText(resultat);
                break;
        }


    }

}