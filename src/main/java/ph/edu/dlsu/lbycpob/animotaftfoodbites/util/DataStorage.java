package ph.edu.dlsu.lbycpob.animotaftfoodbites.util;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.User;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.FoodPlace;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String FOOD_PLACES_FILE = "food_places.dat";
    private static final String USERS_FILE = "users.dat";

    @SuppressWarnings("unchecked")
    public static List<FoodPlace> loadFoodPlaces() {
        File file = new File(FOOD_PLACES_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<FoodPlace>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveFoodPlaces(List<FoodPlace> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FOOD_PLACES_FILE))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            List<User> defaultUsers = new ArrayList<>();
            defaultUsers.add(new User("admin", "admin123")); // Default user
            return defaultUsers;
        }

    }
