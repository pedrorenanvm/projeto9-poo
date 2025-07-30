module projeto9poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires jakarta.persistence;

    requires org.hibernate.orm.core;
    requires java.desktop;
    requires jakarta.transaction;

    opens br.edu.ufersa.projeto9poo.models.entities;

    //opens br.edu.ufersa.projeto9poo.app to javafx.fxml;
    opens br.edu.ufersa.projeto9poo.view to javafx.fxml;
    opens br.edu.ufersa.projeto9poo.controller to javafx.fxml;

    //exports br.edu.ufersa.projeto9poo.app;
    exports br.edu.ufersa.projeto9poo.view;
    exports br.edu.ufersa.projeto9poo.models.entities;
}