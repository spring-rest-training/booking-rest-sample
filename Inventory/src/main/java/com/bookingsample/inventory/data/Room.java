package com.bookingsample.inventory.data;

import javax.persistence.*;

/**
 * Created by davut on 11.03.2017.
 */
@Entity(name = "books")
public class Room {
    long id;
    RoomCategory roomCategory;

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String name;
    String description;

    public Room(long id, RoomCategory roomCategory, String name, String description) {
        this.id = id;
        this.roomCategory = roomCategory;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.REFRESH} , fetch = FetchType.EAGER)
    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    @Column(name = "name" , nullable = false , unique = true , length = 128)
    public String getName() {
        return name;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
}
