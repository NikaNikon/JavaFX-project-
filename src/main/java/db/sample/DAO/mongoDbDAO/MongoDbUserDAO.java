package db.sample.DAO.mongoDbDAO;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import db.sample.DAO.mySQLDAO.MySQLUserDAO;
import db.sample.IDAO.IUserDAO;
import db.sample.listeners.GroupListener;
import db.sample.listeners.IGroupListener;
import db.sample.registration.Client;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbUserDAO implements IUserDAO {
    public static IUserDAO dao;
    private static MongoClient client;
    private static MongoDatabase db;
    private static MongoCollection<Document> coll;
    private MongoCollection<Document> oldVersion;
    Document doc;


    private MongoDbUserDAO(){
        client = new MongoClient("Veronika");
        db = client.getDatabase("DanceTown");
        coll = db.getCollection("students");
        oldVersion = db.getCollection("students");

    }

    public static IUserDAO getInstance()
    {
        if(dao == null)
            dao = new MongoDbUserDAO();
        return dao;
    }



    public boolean insertUser(String firstName, String lastName, String login, String pass) throws ClassNotFoundException {
        doc = new Document("first_name", firstName).append("last_name", lastName).append("login", login).append("password", pass);
        coll.insertOne(doc);
        return true;
    }


    public boolean insertUser(Client student) {
        Document doc = new Document("id", student.getId()).append("first_name", student.getName()).append("last_name",
                student.getLast_name()).append("login", student.getLogin()).append("password", student.getPass()).append("phone",
                student.getPhone()).append("email", student.getEmail());
        coll.insertOne(doc);
        return true;
    }


    public Client[] getClients() {
        MongoCursor<Document> cursor = coll.find().iterator();
        List<Client> clients = new ArrayList<Client>();
        Document doc;
        while (cursor.hasNext()){
            doc = cursor.next();
            clients.add(new Client.Builder(Integer.parseInt((doc.get("id").toString())), doc.getString("first_name"),
                    doc.getString("last_name"), doc.getString("login"), doc.getString("password"), doc.getString("phone"),
                    doc.getString("email")).build());
        }
        Client[] clientsArray = clients.toArray(new Client[clients.size()]);;
        return clientsArray;
    }



    public boolean migrate() {
        /*Client[] old = getClients();
        List<Document> clientsList= new ArrayList<>();
        for(Client cl : old){
            clientsList.add(new Document("id", cl.getId()).append("first_name", cl.getName()).append("last_name",
                    cl.getLast_name()).append("login", cl.getLogin()).append("password", cl.getPass()).append("phone",
                    cl.getPhone()).append("email", cl.getEmail()));
        }
        oldVersion.insertMany(clientsList);*/

        coll.drop();
        //db.createCollection("students");
        coll = db.getCollection("students");
        IUserDAO mysqlDao = MySQLUserDAO.getInstance();
        Client[] clients = mysqlDao.getClients();
        List<Document> clientsList= new ArrayList<Document>();
        for(Client cl : clients) {
            clientsList.add(new Document("id", String.valueOf(cl.getId())).append("first_name", String.valueOf(cl.getName())).
                    append("last_name", String.valueOf(cl.getLast_name())).append("login", String.valueOf(cl.getLogin())).
                    append("password", String.valueOf(cl.getPass())).append("phone", String.valueOf(cl.getPhone())).
                    append("email", String.valueOf(cl.getEmail())));
        }
        coll.insertMany(clientsList);
        System.out.println("Migrated successfully");
        return true;
    }




}
