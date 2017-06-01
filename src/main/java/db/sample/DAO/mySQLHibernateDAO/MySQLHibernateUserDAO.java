package db.sample.DAO.mySQLHibernateDAO;

import db.sample.IDAO.IUserDAO;
import db.sample.hibernateEntities.UsersEntity;
import db.sample.registration.Client;
import db.sample.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MySQLHibernateUserDAO implements IUserDAO {
    private static IUserDAO dao;
    //Session session;

    private MySQLHibernateUserDAO(){

    }

    public static IUserDAO getInstance()
    {
        if(dao == null)
            dao = new MySQLHibernateUserDAO();
        return dao;
    }

    public boolean insertUser(String firstName, String lastName, String login, String pass) throws ClassNotFoundException {
        return false;
    }


    public boolean insertUser(Client client) {
        Session session = null;
        //session = HibernateUtil.getSessionFactory().openSession();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            UsersEntity userEntity = new UsersEntity();
            userEntity.setFirstName(client.getName());
            userEntity.setLastName(client.getLast_name());
            userEntity.setLogin(client.getLogin());
            userEntity.setPassword(client.getPass());
            userEntity.setPhone(client.getPhone());
            userEntity.setEmail(client.getEmail());

            session.save(userEntity);
            tr.commit();
//            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("Ошибка при вставке");
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        //session.close();
        return true;
    }


    public Client[] getClients() {
        return new Client[0];
    }


    public boolean migrate() {
        return false;
    }
}
