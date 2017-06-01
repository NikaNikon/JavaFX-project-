package db.sample.hibernateEntities;

import javax.persistence.*;

@Entity
@Table(name = "cards", schema = "ua21db")
public class CardsEntity {
    private String name;
    private String description;
    private Integer groupsAmount;
    private Integer trainingsAmount;
    private int price;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "groups_amount")
    public Integer getGroupsAmount() {
        return groupsAmount;
    }

    public void setGroupsAmount(Integer groupsAmount) {
        this.groupsAmount = groupsAmount;
    }

    @Basic
    @Column(name = "trainings_amount")
    public Integer getTrainingsAmount() {
        return trainingsAmount;
    }

    public void setTrainingsAmount(Integer trainingsAmount) {
        this.trainingsAmount = trainingsAmount;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardsEntity that = (CardsEntity) o;

        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (groupsAmount != null ? !groupsAmount.equals(that.groupsAmount) : that.groupsAmount != null) return false;
        if (trainingsAmount != null ? !trainingsAmount.equals(that.trainingsAmount) : that.trainingsAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (groupsAmount != null ? groupsAmount.hashCode() : 0);
        result = 31 * result + (trainingsAmount != null ? trainingsAmount.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
