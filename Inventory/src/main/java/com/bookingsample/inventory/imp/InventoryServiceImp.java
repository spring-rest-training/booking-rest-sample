package com.bookingsample.inventory.imp;



import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomCategory;
import com.bookingsample.inventory.web.InventoryService;
import com.bookingsample.inventory.web.RestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by davut on 11.03.2017.
 */
@Component
public class InventoryServiceImp implements InventoryService {
    List<Room> rooms = new ArrayList<>();
    @Override
    public Room getRoom(long id) {
        return rooms.stream().filter(o->o.getId() == id).findFirst()
                .orElseThrow(()->new RestException(RestException.NOT_FOUND , "No room with given id" ));
    }

    @Override
    public List<Room> getRoomsByCategory(long categoryID) {
        return rooms.stream().filter(o->o.getRoomCategory().getId() == categoryID).collect(Collectors.toList());
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }
}
