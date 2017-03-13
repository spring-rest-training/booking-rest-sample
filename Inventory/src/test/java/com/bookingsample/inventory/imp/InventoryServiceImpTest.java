package com.bookingsample.inventory.imp;

import com.bookingsample.inventory.WebApplication;
import com.bookingsample.inventory.data.Room;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by davut on 14.03.2017.
 */
public class InventoryServiceImpTest {

    InventoryServiceImp serviceImp = new InventoryServiceImp();

    @Before
    public void init()
    {
        WebApplication.addRooms(serviceImp);
    }

    @Test
    public void shouldReturnCategory()
    {
        assertThat(serviceImp.getCategory(1) , notNullValue());
    }

    @Test
    public void shouldGenerateNewIdWhenNewRoomAdded()
    {
        long generatedID = serviceImp.newRoomID();
        serviceImp.addRoom(new Room(generatedID ,serviceImp.getCategory(1)  ,"test","test" ));
        long idAfterAddition = serviceImp.newRoomID();
        assertThat(idAfterAddition , greaterThan(generatedID));
    }
}