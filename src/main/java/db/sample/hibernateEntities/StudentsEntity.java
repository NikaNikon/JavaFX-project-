package db.sample.hibernateEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students", schema = "ua21db")
@IdClass(StudentsEntityPK.class)
public class StudentsEntity {

    private int id;
    private int userId;
    private String currentCardName;
    private Date beginingDate;
    private Date endDate;
    private Integer trainingsLeft;

    public void setBeginingDate(java.sql.Date beginingDate) {
        this.beginingDate = beginingDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "current_card_name")
    public String getCurrentCardName() {
        return currentCardName;
    }

    public void setCurrentCardName(String currentCardName) {
        this.currentCardName = currentCardName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "begining_date")
    public Date getBeginingDate() {
        return beginingDate;
    }
    public void setBeginingDate(Date beginingDate) {
        this.beginingDate = beginingDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "trainings_left")
    public Integer getTrainingsLeft() {
        return trainingsLeft;
    }

    public void setTrainingsLeft(Integer trainingsLeft) {
        this.trainingsLeft = trainingsLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (currentCardName != null ? !currentCardName.equals(that.currentCardName) : that.currentCardName != null)
            return false;
        if (beginingDate != null ? !beginingDate.equals(that.beginingDate) : that.beginingDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (trainingsLeft != null ? !trainingsLeft.equals(that.trainingsLeft) : that.trainingsLeft != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (currentCardName != null ? currentCardName.hashCode() : 0);
        result = 31 * result + (beginingDate != null ? beginingDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (trainingsLeft != null ? trainingsLeft.hashCode() : 0);
        return result;
    }
}
