package ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodPlace {
    private String id;
    private String name;
    private String category;
    private String address;
    private String contactInfo;
    private String openingHours;
    private String logoPath;
    private String storeImagePath;
    private List<String> menuImagePaths;
    private String mapUrl;

    //Constructor
    public FoodPlace(String id, String name, String category, String address,
                     String contactInfo, String openingHours, String logoPath,
                     String storeImagePath, List<String> menuImagePaths, String mapUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
        this.contactInfo = contactInfo;
        this.openingHours = openingHours;
        this.logoPath = logoPath;
        this.storeImagePath = storeImagePath;
        this.menuImagePaths = menuImagePaths != null ? menuImagePaths : new ArrayList<>();
        this.mapUrl = mapUrl;
    }

    public FoodPlace(String name, String address, String contactNumber, String operationHours) {
        this(String.valueOf(System.currentTimeMillis()), name, "RESTAURANT", address,
                contactNumber, operationHours, "", "", new ArrayList<>(), "");
    }

    //Id getter
    public String getId() {
        return id;
    }

    //name getter
    public String getName() {
        return name;
    }

    //category getter
    public String getCategory() {
        return category;
    }

    //address getter
    public String getAddress() {
        return address;
    }
}

