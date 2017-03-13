package com.bookingsample.inventory.web;
import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by davut on 11.03.2017.
 */
@RestController
@RequestMapping("/rooms")
public class RoomResource {

    private InventoryService inventoryService;

    public RoomResource(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @RequestMapping(value = "{roomId}" , method = RequestMethod.GET)
    public ApiResponse getRoom(@PathVariable(name = "roomId") long roomId)
    {
        try {
             return ApiResponse.success(new RoomDTO(inventoryService.getRoom(roomId)));
        }catch (RestException e)
        {
            return ApiResponse.error(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RoomDTO> getRoomsInCategory(@RequestParam(name = "categoryId") long categoryID)
    {
        List<Room> roomList = inventoryService.getRoomsByCategory(categoryID);
        return roomList.stream().map(RoomDTO::new).collect(Collectors.toList());
    }
    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse addRoom(@RequestBody RoomDTO rDto)
    {
        try {
            Room room = createRoom(rDto);
            return ApiResponse.success(room);
        }catch (RestException e)
        {
            return ApiResponse.error(e);
        }
    }

    private Room createRoom(RoomDTO rDto) {
        Room room = new Room(inventoryService.newRoomID() , inventoryService.getCategory(rDto.getCategoryId()) , rDto.getName() , rDto.getDescription());
        inventoryService.addRoom(room);
        return room;
    }
}
