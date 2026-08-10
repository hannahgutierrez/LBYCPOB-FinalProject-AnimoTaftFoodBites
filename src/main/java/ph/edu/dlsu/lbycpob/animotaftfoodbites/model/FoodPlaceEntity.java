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

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
