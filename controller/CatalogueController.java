package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Catalogue;
import model.Part;

public class CatalogueController extends Controller<Catalogue> {
    public final Catalogue getCatalogue() { return model; }

    @FXML private TableView<Part> catalogueTv;
    @FXML private TableColumn<Part, String> price;
    @FXML private TableColumn<Part, String> name;
    @FXML private TableColumn<Part, String> type;
    @FXML private Button addBtn;
    @FXML private Button rmvBtn;
    @FXML private TextField typeTf;
    @FXML private TextField lowRangeTf;
    @FXML private TextField highRangeTf;


    @FXML public void initialize() {
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        catalogueTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        catalogueTv.getSelectionModel().selectedItemProperty().addListener((o, oldPart, newPart) -> addBtn.setDisable (newPart == null));
        catalogueTv.getSelectionModel().selectedItemProperty().addListener((o, oldPart, newPart) -> rmvBtn.setDisable (newPart == null));
        

        typeTf.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!(oldValue.equals(typeTf.toString()))) {
                getCatalogue().filterList(newValue, lowRangeTf.getText(), highRangeTf.getText());
            }
        }));

        lowRangeTf.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!(oldValue.equals(lowRangeTf.toString()))) {
                getCatalogue().filterList(typeTf.getText(),newValue, highRangeTf.getText());
            }
        }));

        highRangeTf.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!(oldValue.equals(highRangeTf.toString()))) {
                getCatalogue().filterList(typeTf.getText(), lowRangeTf.getText(), newValue);
            }
        }));
    }

    @FXML private void handleAddCatalogue(ActionEvent event) throws Exception {
        ViewLoader.showStage(getCatalogue(), "/view/addtocatalogue.fxml", "Add New Part to Catalogue", new Stage());

    }

    @FXML private void handleDeleteCatalogue(ActionEvent event) throws Exception {
        ObservableList parts = catalogueTv.getSelectionModel().getSelectedItems();
        if (parts.size() >= 1) {
            getCatalogue().remove(parts);
        }
    }

    @FXML private void handleAddBuild(ActionEvent event) throws Exception {
        ObservableList parts = catalogueTv.getSelectionModel().getSelectedItems();
        if (parts.size() >= 1) {
            getCatalogue().addToBuild(parts);
        }
    }
    @FXML private void handleCloseWindow(ActionEvent event) throws Exception { stage.close(); }
}
