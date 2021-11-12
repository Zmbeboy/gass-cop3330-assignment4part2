/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Eric Gass
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;


public class ToDoController {

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,String> description;

    @FXML
    private TableColumn<Item, String> date;

    @FXML
    private TableColumn<Item,String> completion;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker dateField;

    @FXML
    private CheckBox completionField;

    @FXML
    private Button addItem;

    @FXML
    private Button removeItem;

    @FXML
    private Button clearItem;

    @FXML
    private Button updateItem;

    @FXML
    private Button showIncomplete;

    @FXML
    private Button showComplete;

    @FXML
    private Button showAll;

    ObservableList<Item> list = FXCollections.observableArrayList(

         new Item("ItemTest1","2002-06-03","Complete"),
         new Item("ItemTest2","2002-06-04","Complete"),
         new Item("ItemTest3","2002-06-28","Incomplete"),
         new Item("ItemTest4","2002-06-06","Complete")
    );


    @FXML
    protected void initialize()
    {
        //list.add();
        description.setCellValueFactory(new PropertyValueFactory<Item,String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Item,String>("date"));
        completion.setCellValueFactory(new PropertyValueFactory<Item,String>("completion"));

        table.setItems(list);
    }

    public void addItemClick(ActionEvent actionEvent)
    {
        String comp;
        String desc = descriptionField.getText();
        String date = dateField.getValue().toString();
        if(completionField.isSelected())
        {
            comp = "Complete";
        }
        else
        {
            comp = "Incomplete";
        }
        list.add(new Item(desc,date,comp));
    }

    public void removeItemClick(ActionEvent actionEvent)
    {
        Item item = table.getSelectionModel().getSelectedItem();
        list.remove(item);
    }

    public void clearItemClick(ActionEvent actionEvent)
    {
        table.setItems(list);
        list.clear();
        table.refresh();
    }

    public void updateItemClick(ActionEvent actionEvent)
    {
        String comp;
        if(completionField.isSelected())
        {
            comp = "Complete";
        }
        else
        {
            comp = "Incomplete";
        }
        Item item = table.getSelectionModel().getSelectedItem();
        item.setComplete(comp);
        item.setDate(dateField.getValue().toString());
        item.setDescription(descriptionField.getText());
        table.refresh();
    }

    public void showIncompleteClick(ActionEvent actionEvent)
    {
        ObservableList<Item> incompleteList = FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getCompletion().equals("Incomplete"))
            {
                incompleteList.add(list.get(i));
            }
        }
        table.setItems(incompleteList);
        table.refresh();
    }

    public void showCompleteClick(ActionEvent actionEvent)
    {
        ObservableList<Item> completeList = FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getCompletion().equals("Complete"))
            {
                completeList.add(list.get(i));
            }
        }
        table.setItems(completeList);
        table.refresh();
    }

    public void showAllClick(ActionEvent actionEvent)
    {
        table.setItems(list);
        table.refresh();
    }

    //updates the fields to the selected item
    public void displaySelected(MouseEvent event)
    {
        Item item = table.getSelectionModel().getSelectedItem();
        if(item != null)
        {
            descriptionField.setText(item.description);
            dateField.setValue(LocalDate.parse(item.date));
            if(item.completion.equals("Complete"))
            {
                completionField.setSelected(true);
            }
            else
            {
                completionField.setSelected(false);
            }
        }
    }
}