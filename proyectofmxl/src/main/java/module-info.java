module co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens co.edu.uniquindio.poo to javafx.fxml;
    exports co.edu.uniquindio.poo;
    exports co.edu.uniquindio.poo.viewController;
    exports co.edu.uniquindio.poo.model;
}
