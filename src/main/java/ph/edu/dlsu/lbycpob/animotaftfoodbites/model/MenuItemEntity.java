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

}

