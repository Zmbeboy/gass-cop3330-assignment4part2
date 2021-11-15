package ucf.assignments;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest
{
    @Test //TestCase 01
    void isList()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");
        for(int i = 0;i<=100;i++) {
            t.addItem(item);
        }
        assertEquals(t.list.size(),101);
    }

    @Test //TestCase 02
    void itemDescription()
    {
        Item item = new Item("hello world","2002-06-03","Complete");

        assertEquals(item.getDescription(),"hello world");
    }

    @Test //TestCase 03
    void dateDescription()
    {
        Item item = new Item("hello world","2002-06-03","Complete");

        assertEquals(item.getDate(),"2002-06-03");
    }

    @Test //TestCase 04
    void addItem()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");

        t.addItem(item);
        assertEquals(t.list.size(),1);
    }

    @Test //TestCase 05
    void removeItem()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");

        t.addItem(item);
        t.removeItem(item);
        assertTrue(!t.list.contains(item));
    }

    @Test //TestCase 06
    void clearList()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");

        for(int i = 0;i<=100;i++) {
            t.list.add(item);
        }

        assertEquals(t.list.size(),101);

        t.clearItem();

        assertEquals(t.list.size(),0);
    }

    @Test //TestCase 07,08,09
    void updateItem()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");

        t.addItem(item);

        assertEquals(t.list.get(0).getDescription(),"hello world");
        assertEquals(t.list.get(0).getDate(),"2002-06-03");
        assertEquals(t.list.get(0).getCompletion(),"Complete");
        t.updateItem(item,"goodbye world","2002-06-04","Incomplete");

        assertEquals(t.list.get(0).getDescription(),"goodbye world");
        assertEquals(t.list.get(0).getDate(),"2002-06-04");
        assertEquals(t.list.get(0).getCompletion(),"Incomplete");
    }

    @Test //TestCase 10
    void showAll()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");

        t.addItem(item);

        t.showAll();

        assertEquals(false,true);
    }

    @Test //TestCase 11
    void showIncomplete()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");
        t.addItem(item);
        Item item2 = new Item("hello world","2002-06-03","Incomplete");
        t.addItem(item2);

        assertEquals(t.showIncomplete().get(0),item2);
    }

    @Test //TestCase 12
    void showComplete()
    {
        ToDoController t = new ToDoController();
        Item item = new Item("hello world","2002-06-03","Complete");
        t.addItem(item);
        Item item2 = new Item("hello world","2002-06-03","Incomplete");
        t.addItem(item2);

        assertEquals(t.showComplete().get(0),item);
    }
}
