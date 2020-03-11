package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Catalogue;

public class ErrorController extends AddToCatalogueController {
    @FXML private void handleClose(ActionEvent event) throws Exception { stage.close(); }
}
