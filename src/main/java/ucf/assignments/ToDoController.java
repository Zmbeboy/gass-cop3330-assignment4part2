/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Eric Gass
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ToDoController {

    @FXML
    private ListView ToDoLists;

    @FXML
    private ListView ItemLists;

    @FXML
    protected void initialize()
    {
        ObservableList<String> todoList = FXCollections.observableArrayList("List1","List2","List3");
        ToDoLists.setItems(todoList);

        ObservableList<String> Lists = FXCollections.observableArrayList("Item1","Item2","Item3");
        ItemLists.setItems(Lists);
    }
}