package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    String fxmlMain = "sample.fxml";
    private Stage primaryStage;
    private Parent Mainfxml;

    private Controller controller;
    private FXMLLoader fxmlLoader;

    private VBox currentRoot;


    @Override
    public void start(Stage primaryStage) throws Exception{

//        System.runFinalizersOnExit(true);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Мои книги");
        primaryStage.setMinWidth(685);
        primaryStage.setMinHeight(550);
        primaryStage.setScene(new Scene(root, 715, 550));
        primaryStage.show();

        //testData();
    }

    private void testData(){
        CollectionBooks booklist = new CollectionBooks();
        booklist.fillTestData();
        booklist.print();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private VBox loadFXML() {
        fxmlLoader = new FXMLLoader();

//        try {
//            Parent root = fxmlLoader.load(getClass().getResource(fxmlMain));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        VBox node = null;

        try {
            node = (VBox) fxmlLoader.load(getClass().getResource(fxmlMain));


            controller = fxmlLoader.getController();
            primaryStage.setTitle("Мои книги");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
    }

    private void createGUI() {
        currentRoot = loadFXML();
        Scene scene = new Scene(currentRoot, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.show();
    }
}
