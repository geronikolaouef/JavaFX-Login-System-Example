import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UniversalSceneController 
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public UniversalSceneController() 
    {

    }   
    
    public void switchToTarget(ActionEvent event, String targetScene) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource(targetScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
