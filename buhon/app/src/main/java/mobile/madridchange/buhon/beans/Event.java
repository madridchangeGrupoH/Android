package mobile.madridchange.buhon.beans;

/**
 * Created by omar on 13/11/16.
 */

public class Event {
    private Long id;
    private Long creatorId;
    private String Name;
    private String Description;
    private String Location;
    private Long minCapacity;
    private Long maxCapacity;
    private Long currentUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Long getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(Long minCapacity) {
        this.minCapacity = minCapacity;
    }

    public Long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Long getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(Long currentUsers) {
        this.currentUsers = currentUsers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("id=").append(id);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Description='").append(Description).append('\'');
        sb.append(", Location='").append(Location).append('\'');
        sb.append(", minCapacity=").append(minCapacity);
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", currentUsers=").append(currentUsers);
        sb.append('}');
        return sb.toString();
    }
}
