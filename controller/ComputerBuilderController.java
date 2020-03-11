package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import java.io.*;
import java.text.*;
import model.*;
import au.edu.uts.ap.javafx.*;

public class ComputerBuilderController extends Controller<ComputerBuilder>{
    Build build = new Build();
    Catalogue catalogue = new Catalogue(build);


    @FXML private void handleViewCatalogue(ActionEvent event) throws Exception {
        ViewLoader.showStage(catalogue, "/view/catalogue.fxml", "Catalogue", new Stage());
    }

    @FXML private void handleViewBuild(ActionEvent event) throws Exception {
        ViewLoader.showStage(build, "/view/build.fxml", "Build", new Stage());
    }

    @FXML private void handleCloseApp(ActionEvent event) throws Exception { System.exit(0); }
}