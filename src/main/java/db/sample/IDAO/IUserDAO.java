package db.sample.IDAO;
import db.sample.registration.Client;


public interface IUserDAO {

    boolean insertUser(String firstName, String lastName, String login, String pass) throws ClassNotFoundException;
    boolean insertUser(Client client);
    Client[] getClients();
    boolean migrate();
}
