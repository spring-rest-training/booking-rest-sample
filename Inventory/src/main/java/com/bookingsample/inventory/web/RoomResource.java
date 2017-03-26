package com.bookingsample.inventory.web;
import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
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
        String name = rDto.getName();
        long categoryId = rDto.getCategoryId();
        String description = rDto.getDescription();
        return addRoomSafe(name, categoryId, description);
    }

    @RequestMapping( method = RequestMethod.PUT , value = "/{roomId}")
    public ApiResponse updateRoom(@PathVariable(name = "roomId") long roomId, @RequestBody RoomDTO roomDTO)
    {
        return invokeSafe(o -> {
            Room r = inventoryService.updateRoom(roomId , roomDTO);
            return ApiResponse.success(r);
        });
    }

    private ApiResponse invokeSafe(Function<Void, ApiResponse> func)
    {
        try {
            return func.apply(null);
        }catch (RestException e)
        {
            return ApiResponse.error(e);
        }catch (Exception e)
        {
            return ApiResponse.error(e);
        }
    }

    private ApiResponse addRoomSafe(String name, long categoryId, String description) {
        try {
            Room room = createRoom(name , categoryId , description);
            return ApiResponse.success(room);
        }catch (RestException e)
        {
            return ApiResponse.error(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiResponse addRoom(String name , String description , long roomCategoryId)
    {
        return addRoomSafe(name ,roomCategoryId , description);
    }



    private Room createRoom(String name, long categoryId, String description) {
        Room room = new Room(inventoryService.newRoomID() , inventoryService.getCategory(categoryId) , name , description);
        inventoryService.addRoom(room);
        return room;
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/{roomId}")
    public ApiResponse deleteRoom(@PathVariable long roomId)
    {
        return invokeSafe(o->{
            inventoryService.deleteRoom(roomId);
            return ApiResponse.success();
        });
    }
}
