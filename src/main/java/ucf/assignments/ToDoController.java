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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


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

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem loadButton;

    ObservableList<Item> list = FXCollections.observableArrayList();


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
        addItem(new Item(desc,date,comp));
    }

    public void addItem(Item item)
    {
        list.add(item);
    }

    public void removeItemClick(ActionEvent actionEvent)
    {
        Item item = table.getSelectionModel().getSelectedItem();
        removeItem(item);
    }

    public void removeItem(Item item)
    {
        list.remove(item);
    }

    public void clearItemClick(ActionEvent actionEvent)
    {
        table.setItems(list);
        clearItem();
        table.refresh();
    }

    public void clearItem()
    {
        list.clear();
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

        updateItem(item,descriptionField.getText(),dateField.getValue().toString(),comp);

        table.refresh();
    }

    public void updateItem(Item item, String desc, String date, String comp)
    {
        item.setDescription(desc);
        item.setDate(date);
        item.setComplete(comp);
    }

    public void showIncompleteClick(ActionEvent actionEvent)
    {
        table.setItems(showIncomplete());
        table.refresh();
    }

    public ObservableList<Item> showIncomplete()
    {
        ObservableList<Item> incompleteList = FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getCompletion().equals("Incomplete"))
            {
                incompleteList.add(list.get(i));
            }
        }
        return incompleteList;
    }

    public void showCompleteClick(ActionEvent actionEvent)
    {
        table.setItems(showComplete());
        table.refresh();
    }

    public ObservableList<Item> showComplete()
    {
        ObservableList<Item> completeList = FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getCompletion().equals("Complete"))
            {
                completeList.add(list.get(i));
            }
        }
        return completeList;
    }

    public void showAllClick(ActionEvent actionEvent)
    {
        showAll();
    }

    public void showAll()
    {
        table.setItems(list);
        table.refresh();
    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        final Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(primaryStage);
        if(file != null)
        {
            saveList(file);
        }
    }

    public void saveList(File file) throws IOException
    {
        FileWriter fileWriter = new FileWriter(file);
        for(int i = 0; i<list.size();i++)
        {
            String result = list.get(i).getDescription()+";"+list.get(i).getDate()+";"+list.get(i).getCompletion()+"\n";
            fileWriter.write(result);
        }
        fileWriter.close();
    }

    public void loadButtonClick(ActionEvent actionEvent) throws IOException {
        final Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(primaryStage);
        loadList(file);
    }

    public void loadList(File file) throws IOException
    {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String itemLine = scan.nextLine();
            String Desc = itemLine.substring(0, itemLine.indexOf(";"));
            itemLine = itemLine.substring(itemLine.indexOf(";") + 1);
            String Date = itemLine.substring(0, itemLine.indexOf(";"));
            itemLine = itemLine.substring(itemLine.indexOf(";") + 1);
            String Comp = itemLine;

            Item item = new Item(Desc, Date, Comp);
            list.add(item);
        }
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