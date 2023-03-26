package myProj;

public class Record
{
    String recordTitle;
    String recordText;
    int recordId;

    public Record(String title, String text, int id)
    {
        this.recordId = id;
        this.recordTitle = title;
        this.recordText = text;
    }

    public int getId() {
        return this.recordId;
    }

    public String getTitle() {
        return this.recordTitle;
    }

    public String getText() {
        return this.recordText;
    }
}