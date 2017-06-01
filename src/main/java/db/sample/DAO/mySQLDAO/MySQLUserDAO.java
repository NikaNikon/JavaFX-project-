package db.sample.DAO.mySQLDAO;
import db.sample.DAO.mongoDbDAO.MongoDbUserDAO;
import db.sample.IDAO.IUserDAO;
import db.sample.registration.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements IUserDAO{
    private static IUserDAO dao;
    private static final String url = "jdbc:mysql://localhost:3306/ua21db";
    private static final String user = "root";
    private static final String password = "ybrfybrfybrf";
    private static Connection con;
    private static PreparedStatement ps;
    private static Statement stm;
    private static ResultSet rs;

    private MySQLUserDAO()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static IUserDAO getInstance()
    {
        if(dao == null)
            dao = new MySQLUserDAO();
        return dao;
    }
    public boolean insertUser(String firstName, String lastName, String login, String pass) throws ClassNotFoundException {
        try {

            ps = con.prepareStatement("insert into users (login, password, first_name, last_name) values (?, ?, ?, ?);");
            ps.setString(1, login);
            ps.setString(2, pass);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            if(ps.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }

    public boolean insertUser(Client client) {
        boolean hasPhone = (client.getPhone() != null);
        boolean hasEmail = (client.getEmail() != null);
        StringBuilder builder = new StringBuilder("insert into users (login, password, first_name, last_name");
        if(hasPhone)
            builder.append(", phone");
        if(hasEmail)
            builder.append(", email");
        builder.append(") values (?, ?, ?, ?");
        if(hasPhone)
            builder.append(", ?");
        if(hasEmail)
            builder.append(", ?");
        builder.append(");");
        try{
            ps = con.prepareStatement(builder.toString());
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getPass());
            ps.setString(3, client.getName());
            ps.setString(4, client.getLast_name());
            if(hasPhone) {
                ps.setString(5, client.getPhone());
                if(hasEmail)
                    ps.setString(6, client.getEmail());
            }
            else{
                if(hasEmail)
                    ps.setString(5, client.getEmail());
            }
            if(ps.executeUpdate() == 1)
                return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public Client[] getClients() {
        List<Client> clients = new ArrayList<Client>();
        try {
            stm = con.createStatement();
            rs = stm.executeQuery("select * from users;");
            while(rs.next()){
                clients.add(new Client.Builder(rs.getInt(1), rs.getString(4), rs.getString(5), rs.getString(2),
                        rs.getString(3), String.valueOf(rs.getString(6)), String.valueOf(rs.getString(7))).build());
            }
                Client [] clientsArray = new Client[clients.size()];
                int i = 0;
                for(Client cl : clients) {
                    clientsArray[i] = cl;
                    i++;
                }
                return clientsArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public boolean migrate() {
        IUserDAO mongoDao = MongoDbUserDAO.getInstance();
        Client[] clients = mongoDao.getClients();

        int i = 0;
        try {
            stm = con.createStatement();
            stm.execute("delete FROM ua21db.usersformigration where user_id > 1 or user_id = 1;");
            for(Client temp: clients) {
                ps = con.prepareStatement("insert into usersformigration (user_id, login, password, first_name, last_name, phone, " +
                        "email) values (?, ?, ?, ?, ?, ?, ?);");
                ps.setInt(1, temp.getId());
                ps.setString(2, temp.getLogin());
                ps.setString(3, temp.getPass());
                ps.setString(4, temp.getName());
                ps.setString(5, temp.getLast_name());
                ps.setString(6, temp.getEmail());
                ps.setString(7, temp.getPhone());
                ps.executeUpdate();
                i++;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println("Migrated successfully");
        System.out.println(i);
        return true;
    }
}
