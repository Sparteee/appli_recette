package miniprojet.miniprojetv2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import miniprojet.miniprojetv2.Repositories.RecepieRepo;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml")); // Fichier des spécifités graphiques
        Scene scene = new Scene(fxmlLoader.load(), 800, 540); // Création de la scene pour l'affichage graphique
        stage.setTitle("Affichage graphique"); // Titre de la fenêtre graphique
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        while(true){ // Boucle infinie
            System.out.println("--------- Menu ---------");
            System.out.println("1 - Interface textuel\n2 - Interface graphique\n3 - Quitter");
            System.out.println("------------------------");
            System.out.print("Votre choix : ");
            Scanner sf = new Scanner(System.in); // Scanner pour que l'utilisateur puisse entrer son choix dans le terminal
            int choix = sf.nextInt(); // Lecture du scanner

            switch (choix){ // Switch de vérifcation pour le menu
                case 1:
                    modeTextuel(); // Si l'utilisateur veut utiliser le mode Textuel --> Appelle de la fonction modeTeextuel()
                    break;
                case 2:
                    launch(); // Si l'utilisateur veut utiliser le mode graphique --> Appelle de la fonction launch() qui lance la fenêtre graphique
                case 3:
                    sf.close(); // Fermeture du scanner
                    System.out.println("Fermeture du service"); //
                    System.exit(0); // Sortie du systeme avec le code 0
                    break;
                default: // Si l'utilisateur entre autre chose que 1 - 2 ou 3
                    sf.close(); // Fermture du scanner
                    System.out.println("Choix non disponible");
                    System.exit(0); // Sortie du systeme avec le code
                    break;

            }
        }

    }

    public static void modeTextuel() throws Exception {
        while(true){
            System.out.println("--------- Mode Textuel ---------");
            System.out.println("Choisissez un nombre entre 1 et 19 - (20 pour quitter)");
            System.out.print("Votre choix : ");
            Scanner sc = new Scanner(System.in); // Scanner pour que l'utilisateur puisse entrer son choix dans le terminal
            int choix = sc.nextInt(); // Lecture du choix proposé par l'utilisateur
            RecepieRepo recepieRepo = new RecepieRepo(); // Création de l'objet RecepieRepo
            recepieRepo.init("recipes.xml"); // Récupération via la fonction init des données présents dans le XML
            System.out.println(" ");
            switch (choix){ // Switch des choix possibles avec appels des méthodes correspondant aux numéros
                case 1:
                    System.out.println("Liste des titres des recettes");
                    System.out.println(recepieRepo.listeTitreRecettes()+"\n");
                    break;
                case 2:
                    System.out.println("Nombre total d'oeufs utilisés");
                    System.out.println(recepieRepo.nbTotalOeufs()+"\n");
                    break;
                case 3:
                    System.out.println("Recettes utilisant l'huile d'olive");
                    System.out.println(recepieRepo.recettesHuileOlive()+"\n");
                    break;
                case 4:
                    System.out.println("Nombre d'oeufs par recette");
                    System.out.println(recepieRepo.nbOeufsParRecette()+"\n");
                    break;
                case 5:
                    System.out.println("Recettes moins de 500 calories : ");
                    System.out.println(recepieRepo.moins500calories()+"\n");
                    break;
                case 6:
                    System.out.println("Quantité de sucre - Recette Zuppa Inglese");
                    System.out.println(recepieRepo.quantiteSucreZuppa()+"\n");
                    break;
                case 7:
                    System.out.println("2 premières étapes - Zuppa Inglese");
                    System.out.println(recepieRepo.deuxetapesZuppa()+"\n");
                    break;
                case 8 :
                    System.out.println("Recettes qui nécessitent plus de 5 étapes");
                    System.out.println(recepieRepo.necessite5etapes()+"\n");
                    break;
                case 9:
                    System.out.println("Recettes sans beurre");
                    System.out.println(recepieRepo.recettesSansBeurre()+"\n");
                    break;
                case 10:
                    System.out.println("Ingrédients en communs - Zuppa Inglese");
                    System.out.println(recepieRepo.recettesCommunesZuppa()+"\n");
                    break;
                case 11:
                    System.out.println("Recette la plus calorique");
                    System.out.println(recepieRepo.recettePlusCalorique()+"\n");
                    break;
                case 12:
                    System.out.println("Unité la plus fréquente");
                    System.out.println(recepieRepo.UnitPlusFrequente()+"\n");
                    break;
                case 13:
                    System.out.println("Nombre d'ingrédients par recette");
                    System.out.println(recepieRepo.nbIngredientsParRecette()+"\n");
                    break;
                case 14:
                    System.out.println("Recette comportant le plus de fat");
                    System.out.println(recepieRepo.recettePlusFat()+"\n");
                    break;
                case 15 :
                    System.out.println("Ingrédient le plus utilisé");
                    System.out.println(recepieRepo.ingredientPlusUtilise()+"\n");
                    break;
                case 16 :
                    System.out.println("Recettes triées par nombre d'ingrédients");
                    System.out.println(recepieRepo.recetteTrieeNbIngredients()+"\n");
                    break;
                case 17:
                    System.out.println("Liste ingredients utilisés pour chaque recette");
                    System.out.println(recepieRepo.IngredientsUtilisesRecettes()+"\n");
                    break;
                case 18:
                    System.out.println("Répartition des recettes par étape de réalisation");
                    System.out.println(recepieRepo.repartitionRecetteEtape().entrySet().stream()
                            .map(repart -> repart.getKey() + " étapes : " + repart.getValue().stream().map(recepie -> recepie.getTitle())
                                    .collect(Collectors.joining(", ")))
                            .collect(Collectors.joining("\n"))+"\n");
                    break;
                case 19 :
                    System.out.println("Recette la plus facile");
                    System.out.println(recepieRepo.recettePlusFacile());
                    break;
                case 20:
                    sc.close(); // Fermeture du scanner
                    System.out.println("  ");
                    System.out.println("Fermeture du service");
                    System.exit(0); // Sortie du système grâce au code 0
                    break;
            }

        }
    }

}