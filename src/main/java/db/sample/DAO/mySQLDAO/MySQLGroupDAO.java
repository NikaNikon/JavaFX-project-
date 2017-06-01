package db.sample.DAO.mySQLDAO;

import db.sample.IDAO.IGroupDAO;
import db.sample.groups.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLGroupDAO implements IGroupDAO {
    private static IGroupDAO dao;
    private static final String url = "jdbc:mysql://localhost:3306/ua21db";
    private static final String user = "root";
    private static final String password = "ybrfybrfybrf";
    private static Connection con;
    private static PreparedStatement ps;
    private static Statement stm;
    private static ResultSet rs;

    private MySQLGroupDAO()
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


    public Group[] sortedGroups(String sort){return null;}

    public static IGroupDAO getInstance()
    {
        if(dao == null)
            dao = new MySQLGroupDAO();
        return dao;
    }

    public Group[] displayGroups() {
        List<Group> groupList = new ArrayList<Group>();
        try {
            //con = DriverManager.getConnection(url, user, password);
                stm = con.createStatement();
                rs = stm.executeQuery("select group_id, style, level, first_name, last_name, schedule\n" +
                        "from groups, coaches where coach_id = id;");
                while (rs.next()) {
                    Group group = new Group(rs.getString(2), (double)rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    group.setId(rs.getInt(1));
                    groupList.add(group);
                }
                Group[] groups = new Group[groupList.size()];
                int i = 0;
                for(Group g : groupList)
                {
                  groups[i] = g;
                    i++;
                }
                return groups;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException se) {
            }
        }
        return null;
    }

    public Group [] findGroups(String searchOption, String searchOptionValue) {
        List<Group> groupList = new ArrayList<Group>();
        try {
            //con = DriverManager.getConnection(url, user, password);
            StringBuilder builder = new StringBuilder("select group_id, style, level, first_name, last_name, schedule\n" +
                    "from groups, coaches where coach_id = id and ");
            builder.append(searchOption + " LIKE '%" + searchOptionValue + "%';");
            ps = con.prepareStatement(builder.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group(rs.getString(2), (double)rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                group.setId(rs.getInt(1));
                groupList.add(group);
            }
            Group[] groups = new Group[groupList.size()];
            int i = 0;
            for (Group g : groupList) {
                groups[i] = g;
                i++;
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addGroup(String style, Double level, String coachName, String coachLastName, String schedule) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(url, user, password);

            ps = con.prepareStatement("insert into groups (style, level, coach_id, schedule) values " +
                    "(?, ?, (select id from coaches where first_name like ? and last_name like ?), ?);");
            ps.setString(1, style);
            ps.setString(2, level.toString());
            ps.setString(3, coachName);
            ps.setString(4, coachLastName);
            ps.setString(5, schedule);
            if(ps.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException se) {
            }
        }
        return false;
    }

    public boolean updateSchedule(Group g) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            //con = DriverManager.getConnection(url, user, password);
            ps = con.prepareStatement("UPDATE groups SET schedule = ? where group_id like ? ;");
            ps.setString(1, g.getSchedule());
            ps.setInt(2, g.getId());

            if(ps.executeUpdate() == 1) return true;
            else return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ps.close();
            con.close();
        }
        return false;
    }

    public int getGroupId(Group g)
    {
        int id = -1;
        try {
            //con = DriverManager.getConnection(url, user, password);
            ps = con.prepareStatement("select group_id from groups where coach_id like " +
                    "(select id from coaches where first_name like ? and last_name like ?) " +
                    "and schedule like ?;");
            ps.setString(1, g.getCoachName());
            ps.setString(2, g.getCoachLastName());
            ps.setString(3, g.getSchedule());
            rs = ps.executeQuery();
            if(rs.next())
                id = rs.getInt(1);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean deleteGroup(Group g) throws SQLException {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            if(stm.execute("delete from groups where group_id = " + g.getId() + ";") == true)
                return true;
            else return false;

        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/ catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            stm.close();
            con.close();
        }
        return false;
    }

    public String getStringId(Group g){return null;}
}
