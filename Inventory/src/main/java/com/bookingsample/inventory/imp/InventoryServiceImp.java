package com.bookingsample.inventory.imp;



import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomCategory;
import com.bookingsample.inventory.data.RoomDTO;
import com.bookingsample.inventory.web.InventoryService;
import com.bookingsample.inventory.web.RestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by davut on 11.03.2017.
 */
@Component
public class InventoryServiceImp implements InventoryService {
    List<Room> rooms = new ArrayList<>();
    List<RoomCategory> categories = new ArrayList<>();

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

    @Override
    public long newRoomID() {
        return rooms.stream().map(o->o.getId()).max((a,b)->a.compareTo(b)).get()+1;
    }

    @Override
    public void addCategory(RoomCategory roomCategory) {
        if(categories.stream().anyMatch(o->o.getId() == roomCategory.getId()))
            throw new RestException(RestException.RECORD_EXIST , "Category with given id already exists");
        categories.add(roomCategory);
    }

    @Override
    public RoomCategory getCategory(long categoryID) {
        return categories.stream().filter(o->o.getId() == categoryID).findFirst().
                orElseThrow(()->new RestException(RestException.NOT_FOUND , "No category with given id:"+categoryID));
    }

    @Override
    public Room updateRoom(long roomId, RoomDTO roomDTO) {
        Room r = getRoom(roomId);
        r.setName(roomDTO.getName());
        r.setDescription(roomDTO.getDescription());
        return r;
    }

    @Override
    public void deleteRoom(long id) {
        rooms.removeIf(o->o.getId() == id);
    }
}
