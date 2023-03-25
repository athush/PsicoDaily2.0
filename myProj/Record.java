package myProj;

import java.util.UUID;

public class Record
{
    String recordTitle;
    String recordText;
    UUID recordId;

    public Record(String title, String text)
    {
        this.recordId = UUID.randomUUID();
        this.recordTitle = title;
        this.recordText = text;
    }

    public UUID getId() {
        return this.recordId;
    }

    public String getTitle() {
        return this.recordTitle;
    }

    public String getText() {
        return this.recordText;
    }
}