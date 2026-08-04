package ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop;

import java.util.List;

public class Cafe extends FoodPlace implements CafeCategorable {
    private boolean hasWifi;

    public Cafe(String id, String name, String category, String address,
                String contactInfo, String openingHours, String logoPath,
                String storeImagePath, List<String> menuImagePaths, String mapUrl,
                boolean hasWifi) {
        super(id, name, category, address, contactInfo, openingHours, logoPath, storeImagePath, menuImagePaths, mapUrl);
        this.hasWifi = hasWifi;
    }

    public Cafe(String name, String address, String contactNumber, String operationHours, boolean hasWifi) {
        super(name, address, contactNumber, operationHours);
        this.hasWifi = hasWifi;
    }

    @Override
    public String getCategoryName() {
        return "CAFE";
    }

    @Override
    public boolean hasWifi() {
        return false;
    }
}

