import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController 
{
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfRepeat;
    @FXML
    private TextField tfUsername;
    private HashMap<String, String> users;

    public RegisterController() throws SQLException
    {
        users = new HashMap<>();
        parseData();
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

    public boolean validateUsername()
    {
        for (String user : users.keySet())
        {
            if (user.equalsIgnoreCase(tfUsername.getText()))
            {   
                System.out.println("Username taken!");
                return false;
            }
        }

        return true;
    }

    public boolean validatePassword()
    {
        String password = tfPassword.getText();
        
        if (password.isEmpty())
        {
            System.out.println("Password can not be empty!");
            return false;
        }
        if (!password.equals(tfRepeat.getText()))
        {
            System.out.println("Passwords must match!");
            return false;
        }

        return true;
    }

    public void insertToDB() throws SQLException
    {
        (new DatabaseController()).insert("INSERT INTO user (username, password) VALUES ('" + tfUsername.getText() + "', '" + tfPassword.getText() + "')");
    }

    @FXML
    void btnRegisterClicked(ActionEvent event) throws SQLException, IOException 
    {
        if (validateUsername() && validatePassword())
        {
            insertToDB();
            System.out.println("register completed");
            (new UniversalSceneController()).switchToTarget(event, "MainScene.fxml");
        }
        else
        {
            System.out.println("Registration denied!");
        }
    }

    @FXML
    void btnCancelClicked(ActionEvent event) throws IOException 
    {
        (new UniversalSceneController()).switchToTarget(event, "MainScene.fxml");
    }

}
