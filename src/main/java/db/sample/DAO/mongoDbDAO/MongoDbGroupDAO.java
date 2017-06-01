package db.sample.DAO.mongoDbDAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import db.sample.IDAO.IGroupDAO;
import db.sample.groups.Group;
import db.sample.listeners.GroupListener;
import db.sample.listeners.IGroupListener;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDbGroupDAO implements IGroupDAO{
    public static IGroupDAO dao;
    private MongoClient client;
    private MongoDatabase db;
    MongoCollection <Document> coll;

    static List <IGroupListener> listeners = new ArrayList<>();
    enum events {added, deleted, changed}

    public static IGroupDAO getInstance()
    {
        IGroupListener listener = new GroupListener();
        addGroupListener(listener);
        if(dao == null) {
            dao = new MongoDbGroupDAO();
        }
        return dao;
    }


    public Group[] displayGroups() {
        connect();
        MongoCursor <Document> cursor = coll.find().iterator();
        List<Group> groups = fillList(cursor);
        cursor.close();
        Group allGroups[] = groups.toArray(new Group[groups.size()]);
        client.close();
        return allGroups;
    }


    public Group[] sortedGroups(String sort){
        connect();
        MongoCursor <Document> cursor = coll.find().sort(new Document(sort, 1)).iterator();
        List<Group> groups = fillList(cursor);
        cursor.close();
        Group allGroups[] = groups.toArray(new Group[groups.size()]);
        client.close();
        return allGroups;
    }


    public Group[] findGroups(String searchOption, String searchOptionValue) {
        connect();
        MongoCursor <Document> cursor;
        if(searchOption == "level")
            cursor = coll.find(eq(searchOption,Double.parseDouble(searchOptionValue))).iterator();
        else if(searchOption == "last_name")
            cursor = coll.find(eq("coach_last_name",searchOptionValue)).iterator();
        else
            cursor = coll.find(eq(searchOption,searchOptionValue)).iterator();
        List<Group> groups = fillList(cursor);
        cursor.close();
        Group allGroups[] = groups.toArray(new Group[groups.size()]);
        client.close();
        return allGroups;
    }


    public boolean addGroup(String style, Double level, String coachName, String coachLastName, String schedule) throws ClassNotFoundException {
        connect();
        Document doc = new Document("style", style).append("level",level).append("coach_name",coachName).
                append("coach_last_name",coachLastName).append("schedule",schedule);
        coll.insertOne(doc);
        client.close();
        notifyListeners(events.added);
        return true;
    }


    public boolean updateSchedule(Group g) throws ClassNotFoundException, SQLException {
        connect();
        coll.updateOne(eq("_id", new ObjectId(g.getStringID())), new Document("$set", new Document("schedule", g.getSchedule())));
        client.close();
        notifyListeners(events.changed);
        return true;
    }


    public int getGroupId(Group g) {
        return 0;
    }


    public String getStringId(Group g){
        connect();
        String id = null;
        MongoCursor <Document> cursor = coll.find(and(eq("coach_last_name",g.getCoachLastName()),eq("schedule",g.getSchedule()))).iterator();
        if(cursor.hasNext())
            id = cursor.next().get("_id").toString();
        else System.out.println("Документ не был добавлен");
        return id;
    }


    public boolean deleteGroup(Group g) throws SQLException {
        connect();
        coll.deleteOne(new Document("_id", new ObjectId(g.getStringID())));
        client.close();
        notifyListeners(events.deleted);
        return true;
    }

    private void connect() {
        client = new MongoClient("Veronika");
        db = client.getDatabase("DanceTown");
        coll = db.getCollection("groups");
    }

    private List<Group> fillList(MongoCursor <Document> cursor){
        List<Group> groups = new ArrayList<Group>();
        Document doc;
        while(cursor.hasNext()){
            Group temp = new Group();
            doc = cursor.next();

            temp.setStringId(doc.get("_id").toString());
            temp.setStyle(doc.getString("style"));
            temp.setLevel(doc.getDouble("level"));
            temp.setCoachName(doc.getString("coach_name"));
            temp.setCoachLastName(doc.getString("coach_last_name"));
            temp.setSchedule(doc.getString("schedule"));

            groups.add(temp);
        }
        cursor.close();
        return groups;
    }

    private void notifyListeners(events event){
        if(event == events.added)
            listeners.get(1).onAdded();
        else if (event == events.changed)
            listeners.get(1).onChanged();
        else if (event == events.deleted)
            listeners.get(1).onDeleted();
    }

    public static void addGroupListener (IGroupListener event){
        listeners.add(event);
    }

    public void removeGroupListener (IGroupListener event){
        listeners.remove(event);
    }
}
