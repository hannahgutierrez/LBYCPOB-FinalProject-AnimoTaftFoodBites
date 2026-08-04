package ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop;

import java.util.List;

public class Stall extends FoodPlace {
    //Constructors
    public Stall(String id, String name, String category, String address,
                 String contactInfo, String openingHours, String logoPath,
                 String storeImagePath, List<String> menuImagePaths, String mapUrl) {
        super(id, name, category, address, contactInfo, openingHours, logoPath, storeImagePath, menuImagePaths, mapUrl);
    }

    public Stall(String name, String address, String contactNumber, String operationHours) {
        super(name, address, contactNumber, operationHours);
    }

    @Override
    public String getCategoryName() {
        return "STALL";
    }
}

