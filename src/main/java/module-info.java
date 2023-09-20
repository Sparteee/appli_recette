module miniprojet.miniprojetv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens miniprojet.miniprojetv2 to javafx.fxml;
    exports miniprojet.miniprojetv2;
}