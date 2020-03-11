package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Build;

public class CheckBuildController extends Controller<Build> {

    public final Build getBuild() { return model; }

    @FXML private Text checkTxt;

    @FXML public void initialize() {
        if (getBuild().isValid()) {
            checkTxt.setText("The build is functional");
        } else {
            String message = "";
            if (!(getBuild().hasPartOfType("CPU"))){ message = message + "The build is missing a CPU.\n"; }
            if (!(getBuild().hasPartOfType("MOTHERBOARD"))){ message = message + "The build is missing a motherboard.\n"; }
            if (!(getBuild().hasPartOfType("MEMORY"))){ message = message + "The build is missing RAM.\n"; }
            if (!(getBuild().hasPartOfType("CASE"))){ message = message + "The build is missing a case.\n"; }
            if (!(getBuild().hasPartOfType("STORAGE"))){ message = message + "The build is missing a storage.\n"; }
            checkTxt.setText(message);
        }
    }

    @FXML
    private void handleClose(ActionEvent event) throws Exception { stage.close(); }
}
