package db.sample.groups;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Group {
    protected int id;
    protected String stringID;
    protected String style;
    protected double level;
    protected String coachName;
    protected String coachLastName;
    protected String schedule;

    public Group (){};

    public int getId(){return id;}
    public void setId(int id) { this.id = id; }

    public String getStringID(){ return stringID; }
    public void setStringId(String id){this.stringID = id;}

    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

    public Double getLevel() {
        return level;
    }
    public void setLevel(Double level) {
        this.level = level;
    }

    public String getCoachName() {
        return coachName;
    }
    public void setCoachName(String name){this.coachName = name;}

    public String getCoachLastName() {
        return coachLastName;
    }
    public void setCoachLastName(String lastName){this.coachLastName = lastName;}

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Group(String style, Double level, String coachName, String coachLastName, String schedule)
    {
        this.style = style;
        this.level = level;
        this.coachName = coachName;
        this.coachLastName = coachLastName;
        this.schedule = schedule;
    }

    public Group(Group g)
    {
        this.id = g.id;
        this.style = g.style;
        this.level = g.level;
        this.coachName = g.coachName;
        this.coachLastName = g.coachLastName;
        this.schedule = g.schedule;
    }
}
