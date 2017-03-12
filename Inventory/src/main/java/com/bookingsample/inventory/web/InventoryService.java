package com.bookingsample.inventory.web;

import com.bookingsample.inventory.data.Room;

import java.util.List;

/**
 * Created by davut on 11.03.2017.
 */
public interface InventoryService {

    Room getRoom(long id);

    List<Room> getRoomsByCategory(long categoryID);

    void addRoom(Room room);
}
