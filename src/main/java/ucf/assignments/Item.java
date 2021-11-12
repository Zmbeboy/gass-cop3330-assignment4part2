package ucf.assignments;



public class Item
{
    String description;
    String date;
    String completion;

    public Item(String description, String date, String completion)
    {
        this.description = description;
        this.date = date;
        this.completion = completion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompletion() {
        return completion;
    }

    public void setComplete(String completion) {
        this.completion = completion;
    }
}
