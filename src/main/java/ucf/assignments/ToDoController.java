/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Eric Gass
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class ToDoController {

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,String> description;

    @FXML
    private TableColumn<Item, String> date;

    @FXML
    private TableColumn<Item,String> completion;

    ObservableList<Item> list = FXCollections.observableArrayList(

         new Item("IanGavernEYSSSYEYSYEYSYEYSYEYSEYSYEYS","2002-06-03","Complete"),
         new Item("ItemTest2","2002-06-04","Complete"),
         new Item("ItemTest3","2002-06-28","Incomplete"),
         new Item("ItemTest4","2002-06-06","Complete")
    );

    @FXML
    protected void initialize()
    {
        description.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Item,String>("date"));
        completion.setCellValueFactory(new PropertyValueFactory<Item,String>("completion"));

        table.setItems(list);
    }
}