package com.bookingsample.inventory;

import com.bookingsample.inventory.data.Pricing;
import com.bookingsample.inventory.data.PricingModel;
import com.bookingsample.inventory.data.Room;
import com.bookingsample.inventory.data.RoomCategory;
import com.bookingsample.inventory.web.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by davut on 11.03.2017.
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String ... args)
    {
         ConfigurableApplicationContext context =  SpringApplication.run(WebApplication.class);
         InventoryService service =  context.getBean(InventoryService.class);
        if(service == null)
            throw new RuntimeException("Test service is not initialized correctly..");
        addRooms(service);
    }

    /**
     * Just mock values in order to work without real database..
     * @param service
     */
    public static void addRooms(InventoryService service) {
        RoomCategory normal = new RoomCategory();
        normal.setDescription("Normal room");
        normal.setName("Normal");
        normal.setPricing( new Pricing(PricingModel.FIXED));
        normal.setId(1);
        service.addCategory(normal);
        RoomCategory sliding = new RoomCategory();
        sliding.setDescription("Sliding room");
        sliding.setName("Sliding room");
        sliding.setPricing(new Pricing(PricingModel.SLIDING));
        sliding.setId(2);
        service.addCategory(sliding);
        service.addRoom(new Room(1 , normal , "Standart room 1" , "nothing special"));
        service.addRoom(new Room(2 , normal , "Standart room 2" , "why not"));
        service.addRoom(new Room(3 , sliding , "Double Size" , "for couples"));
        service.addRoom(new Room(4 , sliding , "King Suite 1" , "legendary"));
        service.addRoom(new Room(5 , sliding , "King Suite 2" , "plus size + legendary"));
    }
}
