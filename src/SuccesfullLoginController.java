import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SuccesfullLoginController 
{
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    XMLController c;

    public void initialize() 
    {
        c = new XMLController();

        lb1.setText("Username: " + c.readElementName("username"));
        lb2.setText("Password: " + c.readElementName("password"));
    }

    @FXML
    public void btnLogoutClicked(ActionEvent event) throws IOException 
    {
        c.updateElementName("username", "");
        c.updateElementName("password", "");
        c.updateElementName("autologin", "false");
        (new UniversalSceneController()).switchToTarget(event, "MainScene.fxml");
    }

    @FXML
    public void exitApplication(ActionEvent event) 
    {
        Platform.exit();
    }
}
