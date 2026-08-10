package ph.edu.dlsu.lbycpob.animotaftfoodbites.model;


import java.math.BigDecimal;
import java.util.UUID;


public class MenuItemEntity {
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private FoodPlaceEntity foodPlace;

    public MenuItemEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public FoodPlaceEntity getFoodPlace() { return foodPlace; }
    public void setFoodPlace(FoodPlaceEntity foodPlace) { this.foodPlace = foodPlace; }

}

