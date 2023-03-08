import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Parent root;
        try 
        {
            if ((new XMLController()).readElementName("autologin").equalsIgnoreCase("true"))
            {
                root = FXMLLoader.load(getClass().getResource("SuccesfullLogin.fxml"));
            }
            else
            {
                root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            }
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login System Example");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stop()
    {
        (new XMLController()).setDeafault();
        System.out.println("Stage is closing");
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}