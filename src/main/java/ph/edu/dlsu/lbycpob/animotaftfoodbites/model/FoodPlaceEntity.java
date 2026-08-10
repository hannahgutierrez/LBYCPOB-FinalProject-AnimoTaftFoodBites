package ph.edu.dlsu.lbycpob.animotaftfoodbites.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FoodPlaceEntity {
    private UUID id = UUID.randomUUID();
    private String name;
    private String address;
    private String contactNumber;
    private String operationHours;
    private String category;
    private String logoUrl;
    private String frontViewUrl;
    private String googleMapLink;
    private String foodType;
    private Boolean wifiAvailable = false;
    private List<MenuItemEntity> menuItems = new ArrayList<>();

    public FoodPlaceEntity() {}

}
