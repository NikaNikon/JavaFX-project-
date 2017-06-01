package db.sample.hibernateEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class GroupsEntityPK implements Serializable {
    private int groupId;
    private int coachId;

    @Column(name = "group_id")
    @Id
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "coach_id")
    @Id
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntityPK that = (GroupsEntityPK) o;

        if (groupId != that.groupId) return false;
        if (coachId != that.coachId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + coachId;
        return result;
    }
}
