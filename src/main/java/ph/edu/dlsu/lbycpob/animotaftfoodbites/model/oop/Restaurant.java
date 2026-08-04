package ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop;

import java.util.List;

public class Restaurant extends FoodPlace implements RestaurantsCategorable {
    private String cuisineType;

    //Contructors
    public Restaurant(String id, String name, String category, String address,
                      String contactInfo, String openingHours, String logoPath,
                      String storeImagePath, List<String> menuImagePaths, String mapUrl,
                      String cuisineType) {
        super(id, name, category, address, contactInfo, openingHours, logoPath, storeImagePath, menuImagePaths, mapUrl);
        this.cuisineType = cuisineType;
    }

    public Restaurant(String name, String address, String contactNumber, String operationHours, String cuisineType) {
        super(name, address, contactNumber, operationHours);
        this.cuisineType = cuisineType;
    }

    @Override
    public String getCategoryName() {
        return "RESTAURANT";
    }
}

