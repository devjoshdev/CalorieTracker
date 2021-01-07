import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class Driver extends Application {
    static FoodsMap foodsMap; // holds a hashmap of days and their corresponding foods

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage s) {
        /*
            See if there is a foodmaps object already made to load data, if not (First time execution or error) then create one
         */
        try {
            FileInputStream file = new FileInputStream("FoodsMap.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            FoodsMap readInMap;
            readInMap = (FoodsMap) inStream.readObject();
            foodsMap = readInMap;

        } catch (FileNotFoundException fnfe) {
            System.out.println("File wasn't found-- New FoodsMap will be created");
            foodsMap = new FoodsMap();
        } catch (IOException ioe) {
            System.out.println("IO Exception");
            foodsMap = new FoodsMap();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class Not Found Exception");
            foodsMap = new FoodsMap();
        }
        /*
            Set Title and Size of window
         */
        s.setTitle("Calorie Tracker");
        s.setHeight(1080);
        s.setWidth(1920);

        /*
            Assign an overall vertical layout
         */
        VBox majorLayout = new VBox(50);

        /*
            Top level will include place to add food name and cals
         */
        HBox topInputs = new HBox(40);

        Label foodNameLabel = new Label("Food Name:");
        TextField foodNameInputField = new TextField();

        Label calorieLabel = new Label("Calories:");
        TextField calorieInputField = new TextField();

        Button addFoodButton = new Button("Add Food");

        topInputs.getChildren().addAll(foodNameLabel, foodNameInputField, calorieLabel, calorieInputField, addFoodButton);

        HBox DaySelectAndList = new HBox(50);
        Button goBackOneDay = new Button("<");
        Button goForwardOneDay = new Button(">");
        Text displayDate = new Text();
        LocalDate today = LocalDate.now();
        displayDate.setText(today.toString());

        //todo add list of the foods for the day 
        DaySelectAndList.getChildren().addAll(goBackOneDay, displayDate, goForwardOneDay);

        majorLayout.getChildren().addAll(topInputs, DaySelectAndList);
        Scene scene = new Scene(majorLayout, 1920, 1080);
        s.setScene(scene);



        s.show();

    }




}
