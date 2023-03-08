import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.HashMap;
import java.io.IOException;

public class MainSceneController 
{
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label lbOutput;
    private HashMap<String, String> users;

    public MainSceneController() throws SQLException
    {
        users = new HashMap<>();
        parseData();
        for (String i : users.keySet()) 
        {
            System.out.println("key: " + i + " value: " + users.get(i));
        }
    }
    
    public void parseData() throws SQLException
    {
        DatabaseController dbc = new DatabaseController();
        ResultSet rs = dbc.exec("SELECT * FROM user");

        // iterate through the results and put to map
        while (rs.next()) 
        {
            users.put(rs.getString("username"), rs.getString("password"));
        }
        
        dbc.closeConn();
    }

    @FXML
    void btnLoginClicked(ActionEvent event) throws IOException 
    {
        checkUser(event);
    }

    public void checkUser(ActionEvent event) throws IOException
    {
        for (String username : users.keySet()) 
        {
            if (username.equals(tfUsername.getText()))
            {
                if (users.get(username).equals(tfPassword.getText()))
                {
                    XMLController c = new XMLController("resources/xml/User.xml");
                    c.updateElementName("username", username);
                    c.updateElementName("password", users.get(username));

                    (new UniversalSceneController()).switchToTarget(event, "SuccesfullLogin.fxml");
                }
                else
                {
                    lbOutput.setText("Incorrect Password!");
                }
            }
            else
            {
                lbOutput.setText("Incorrect Username!");
            }
        }
    }
}
