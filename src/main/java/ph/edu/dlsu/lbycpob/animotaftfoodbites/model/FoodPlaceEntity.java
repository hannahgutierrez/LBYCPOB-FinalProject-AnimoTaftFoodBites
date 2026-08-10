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
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getOperationHours() { return operationHours; }
    public void setOperationHours(String operationHours) { this.operationHours = operationHours; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getFrontViewUrl() { return frontViewUrl; }
    public void setFrontViewUrl(String frontViewUrl) { this.frontViewUrl = frontViewUrl; }
    public String getGoogleMapLink() { return googleMapLink; }
    public void setGoogleMapLink(String googleMapLink) { this.googleMapLink = googleMapLink; }
    public String getFoodType() { return foodType; }
    public void setFoodType(String foodType) { this.foodType = foodType; }
    public Boolean getWifiAvailable() { return wifiAvailable; }
    public void setWifiAvailable(Boolean wifiAvailable) { this.wifiAvailable = wifiAvailable; }
    public List<MenuItemEntity> getMenuItems() { return menuItems; }
    public void setMenuItems(List<MenuItemEntity> menuItems) { this.menuItems = menuItems; }


}
