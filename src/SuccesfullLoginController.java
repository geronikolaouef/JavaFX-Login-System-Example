import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SuccesfullLoginController 
{
    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

    public void initialize() 
    {
        XMLController c = new XMLController("resources/xml/User.xml");

        lb1.setText("Username: " + c.readElementName("username"));
        lb2.setText("Password: " + c.readElementName("password"));
    }
}
