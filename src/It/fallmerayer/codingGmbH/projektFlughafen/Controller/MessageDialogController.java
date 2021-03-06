package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by gabriel on 25.04.17.
 */
//Finished
public class MessageDialogController extends AbstractController {
    Stage dialogStage;
    String message;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @FXML Label MessageLabel;

    @FXML
    private void handleSchliesen(){
        dialogStage.close();
    }
    
    @Override
    public void startController() {
        MessageLabel.setText(message);
    }
}
