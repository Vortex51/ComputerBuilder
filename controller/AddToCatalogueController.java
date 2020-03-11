package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import model.*;
import au.edu.uts.ap.javafx.*;

public class AddToCatalogueController extends Controller<Catalogue>{
    public final Catalogue getCatalogue() { return model;}

    @FXML private TextField typeTf;
    @FXML private TextField nameTf;
    @FXML private TextField priceTf;

    private String getType() { return  typeTf.getText(); }

    private String getName() { return nameTf.getText(); }

    private String getPrice() { return priceTf.getText(); }

    @FXML private void handleAdd() throws Exception {
        try{
            getCatalogue().addPart(getName(), getType(), Double.parseDouble(getPrice()));
            stage.close();
        }catch(Exception e) {
            ViewLoader.showStage(getCatalogue(), "/view/error.fxml", "Incorrect Input", new Stage());
        }
    }
}
