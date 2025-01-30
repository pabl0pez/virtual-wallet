module co.pablopez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.logging;

    opens co.pablopez to javafx.fxml;
    exports co.pablopez;

    opens co.pablopez.Controller;
    exports co.pablopez.Controller;

    opens co.pablopez.Model;
    exports co.pablopez.Model;
}
