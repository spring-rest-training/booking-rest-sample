package com.bookingsample.inventory.data;

/**
 * Created by davut on 11.03.2017.
 */
public class RoomDTO {
    long id;
    String name;
    String description;
    long categoryID;
    public RoomDTO(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.description = room.getDescription();
        this.categoryID = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
