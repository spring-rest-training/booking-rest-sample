package com.bookingsample.inventory.web;

import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomCategory;
import com.bookingsample.inventory.data.RoomDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by davut on 26.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomResourceTest {
    @Mock
    InventoryService service;
    private RoomResource roomResource;


    @Before
    public void initTest()
    {
        this.roomResource = new RoomResource(service);
    }

    @Test
    public void shouldUpdateRoom()
    {
        Room room = new Room(1, new RoomCategory() , "test" , "test");
        when(service.getRoom(anyLong())).thenReturn(room);
        RoomDTO rdto = new RoomDTO();
        roomResource.updateRoom(1 ,rdto );
        verify(service).updateRoom(1 , rdto);
    }
    
    @Test public void shouldReturnExceptionIfMethodFails()
    {
        when(service.updateRoom(anyLong() , any())).thenThrow(new RestException(99 , "test"));
        ApiResponse resp = roomResource.updateRoom(1, new RoomDTO());
        assertThat(resp.getError().errorCode , is(99));
    }
    
    @Test public void shouldReturnUndefinedErrorOnRuntimeExceptions()
    {
        when(service.updateRoom(anyLong() , any())).thenThrow(new RuntimeException());
        ApiResponse resp = roomResource.updateRoom(1, new RoomDTO());
        assertThat(resp.getError().errorCode , is(RestException.UNEXPECTED_ERROR));
    }
    
    @Test public void shouldDeleteGivenRoom()
    {
        long id = 1;
        roomResource.deleteRoom(id);
        verify(service).deleteRoom(id);
    }
    
}