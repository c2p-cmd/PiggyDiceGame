module org.morons.piggydicegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.morons.piggydicegame to javafx.fxml;
    exports org.morons.piggydicegame;
}