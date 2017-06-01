package db.sample.hibernateEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class StudentsEntityPK implements Serializable {
    private int id;
    private int userId;
    private String currentCardName;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "current_card_name")
    @Id
    public String getCurrentCardName() {
        return currentCardName;
    }

    public void setCurrentCardName(String currentCardName) {
        this.currentCardName = currentCardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntityPK that = (StudentsEntityPK) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (currentCardName != null ? !currentCardName.equals(that.currentCardName) : that.currentCardName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (currentCardName != null ? currentCardName.hashCode() : 0);
        return result;
    }
}
