import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.util.ArrayList;

public class Driver extends Application {
    private static FoodsMap foodsMap = new FoodsMap(); // holds a hashmap of days and their corresponding foods
    private static ListView<Food> foodsOfDay = new ListView<>(); // will store and display the foods for the day
    private static LocalDate displayDate;       // will be the day in question

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
        Text displayDateText = new Text();
        displayDate = LocalDate.now();
        displayDateText.setText(displayDate.toString());


        ArrayList<Food> ArrayListofFoodsOfDay = foodsMap.getAllDays().get(displayDate);

        if (ArrayListofFoodsOfDay == null) {
            ArrayListofFoodsOfDay = new ArrayList<>();
        }


        foodsOfDay.setPrefWidth(300);
        foodsOfDay.setPrefHeight(450);
        ObservableList<Food> test = FXCollections.observableArrayList(ArrayListofFoodsOfDay);
        foodsOfDay.setItems(test);
        DaySelectAndList.getChildren().addAll(goBackOneDay, displayDateText, goForwardOneDay, foodsOfDay);

        /*
            Set the total layout and then display everything
         */
        majorLayout.getChildren().addAll(topInputs, DaySelectAndList);
        Scene scene = new Scene(majorLayout, 1920, 1080);
        s.setScene(scene);
        s.show();

        //todo- add the functionality for 'add' (event handler) function and displaying the day with right foods - static var for it?


    }




}
