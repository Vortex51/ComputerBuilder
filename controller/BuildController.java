package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Build;
import model.Part;

import java.text.DecimalFormat;

public class BuildController extends Controller<Build> {
    public final Build getBuild() {
        return model;
    }

    @FXML private TableView<Part> buildTv;
    @FXML private Button rmvBtn;
    @FXML private TableColumn<Part, String> price;
    @FXML private Text sumTxt;

    @FXML public void initialize() throws Exception{
        buildTv.setItems(model.getParts());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        buildTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        buildTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> rmvBtn.setDisable (newValue == null));

        getBuild().getParts().addListener(new ListChangeListener<Part>() {
            @Override
            public void onChanged(Change<? extends Part> c) {
                sumTxt.setText("Total: $" + formatted(getBuild().totalPrice()));
            }
        });
        setTotal();
    }
    @FXML public void setTotal() {
        ObservableList parts = buildTv.getSelectionModel().getSelectedItems();
        if (parts.size() >= 1) {
            sumTxt.setText("Total: $" + formatted(getBuild().totalPrice()));
        }
        if (parts.size() == 0){
            sumTxt.setText("Total: $0.00");
        }
    }

    @FXML private void handleCheckBuild(ActionEvent event) throws Exception {
        ViewLoader.showStage(getBuild(), "/view/buildcheck.fxml", "Build Validity Status", new Stage());
    }

    private String formatted(double price) {
        return new DecimalFormat("#####0.00").format(price);
    }

    @FXML private void handleDeleteBuild(ActionEvent event) throws Exception {
        ObservableList parts = buildTv.getSelectionModel().getSelectedItems();
        if (parts.size() >= 1) {
            getBuild().remove(parts);
        }
    }

    @FXML private void handleCloseWindow(ActionEvent event) throws Exception { stage.close(); }
}
