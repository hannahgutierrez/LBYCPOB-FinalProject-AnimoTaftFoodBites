package ph.edu.dlsu.lbycpob.animotaftfoodbites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Cafe;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.FoodPlace;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Restaurant;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Stall;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FoodPlaceController {


    //Main Directory Controls
    @FXML
    private ComboBox<String> categoryFilter;
    @FXML
    private FlowPane restaurantGrid;
    @FXML
    private Button adminAuthButton;

    //Admin Controls
    @FXML
    private TextField adminUsernameField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private Label loginErrorLabel;

    //Details Controls
    @FXML
    private Label detailNameLabel, detailHoursLabel, detailContactLabel, detailAddressLabel;
    @FXML
    private ImageView detailLogoImageView, detailStoreImageView;

    //Menu Screen Controls
    @FXML
    private Label menuTitleLabel, menuPageLabel;
    @FXML
    private ImageView menuImageView;

    //Form Controls
    @FXML
    private TextField inputName, inputCategory, inputAddress, inputHours, inputContact, inputMapUrl;
    private String uploadedLogoPath, uploadedStorePath, uploadedMenuPath;
    private final List<FoodPlace> foodPlaces = new ArrayList<>();
    private FoodPlace currentSelectedPlace;
    private int currentMenuIndex = 0;
    private boolean isAdminLoggedIn = false;

    @FXML
    public void initialize() {
        if (categoryFilter != null) {
            categoryFilter.getItems().addAll("ALL", "RESTAURANT", "CAFE", "STALL");
            categoryFilter.setOnAction(e -> filterCategory());
        }
        String mapsUrl = "https://maps.app.goo.gl/mqQ9qBPdVtFTUbFL6";
        List<String> mcdoMenus = new ArrayList<>();
        for (int i = 1; i <= 19; i++) {
            mcdoMenus.add("/images/mcdo/McdoMenu" + i + ".png");
        }
        foodPlaces.add(new Restaurant(
                "1",
                "McDonald's Taft",
                "Fast Food",
                "2399 Taft Avenue, Malate, Manila",
                "(02) 8888 6236",
                "Operation Hours: 24-hours",
                "/images/mcdo/McdoLogo.jpg",
                "/images/mcdo/McdoPlacePhoto.jpg",
                mcdoMenus,
                mapsUrl,
                "Fast Food"
        ));

        // Chowking
        List<String> chowkingMenus = new ArrayList<>();
        for (int i = 1; i <= 33; i++) {
            chowkingMenus.add("/images/chowking/ChowkingMenu" + i + ".png");
        }

        foodPlaces.add(new Restaurant(
                "2",
                "Chowking",
                "Fast Food",
                "Level 1, D'Student's Place, Commercial Center, 2488 Taft Ave, Malate, Manila, 1004 Metro Manila",
                "0939 722 0052",
                "Operation Hours: Open 24 hours",
                "/images/chowking/ChowkingLogo.jpg",
                "/images/chowking/ChowkingPlacePhoto.png",
                chowkingMenus,
                "https://maps.app.goo.gl/Qhm4JWukNyJCkAV37",
                "Fast Food"
        ));

        List<String> kfcMenus = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            kfcMenus.add("/images/kfc/KFCMenu" + i + ".png");
        }

        foodPlaces.add(new Restaurant(
                "3",
                "KFC",
                "Fast Food",
                "2507 Taft Ave, Malate, Manila, 1004 Metro Manila",
                "(02) 8256 8377",
                "Operation Hours: Open - Closes 9 PM",
                "/images/kfc/KFCLogo.jpg",
                "/images/kfc/KFCPlacePhoto.jpg",
                kfcMenus,
                "https://maps.app.goo.gl/7uWYHqMw8MhrpAm1A",
                "Fast Food"
        ));

        List<String> dominosMenus = new ArrayList<>();
        // Adjust loop count based on your actual menu image count
        for (int i = 1; i <= 10; i++) {
            dominosMenus.add("/images/Dominos/DominosMenu" + i + ".png");
        }

        foodPlaces.add(new Restaurant(
                "4",
                "Domino's Pizza",
                "Pizza",
                "G/F, E.A. Fernandez Bldg, 2510 Taft Ave, Malate, Manila, 1004 Metro Manila",
                "997-3030",
                "Operation Hours: 10:00 AM - 12:00 AM",
                "/images/Dominos/DominosLogo.jpg",
                "/images/Dominos/DominosPlacePhoto.png",
                dominosMenus,
                "https://maps.app.goo.gl/AoJh955hxrtgbfxu9",
                "Pizza"
        ));

        List<String> zusMenus = new ArrayList<>();
        zusMenus.add("/images/zus/ZUSMenu.jpg");

        foodPlaces.add(new Cafe(
                "4",
                "ZUS Coffee",
                "CAFE",
                "Unit No. 01-B Ground Floor, Taft, Taft Ave, Malate, 2507 Metro Manila",
                "N/A",
                "Operation Hours: 6 AM–10 PM",
                "/images/zus/ZUSLogo.png",
                "/images/zus/ZUSPlacePhoto.jpg",
                zusMenus,
                "https://maps.app.goo.gl/cVojKAWJbxAmR5uy7",
                true // wifiAvailable
        ));
        renderRestaurantGrid(foodPlaces);
        updateAdminButtonUI();
    }

    //Admin log in
    @FXML
    public void handleAdminButtonClick() {
        if (isAdminLoggedIn) {
            // If already logged in as Admin, show Add Restaurant Form directly
            showAddRestaurantForm();
        } else {
            // Show Auth Modal
            adminUsernameField.clear();
            adminPasswordField.clear();
            loginErrorLabel.setVisible(false);
            loginModal.setVisible(true);
        }
    }

    @FXML
    public void loginAdmin() {
        String username = adminUsernameField.getText();
        String password = adminPasswordField.getText();


        // Admin Credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
            isAdminLoggedIn = true;
            loginModal.setVisible(false);
            updateAdminButtonUI();
            showAddRestaurantForm();
        } else {
            loginErrorLabel.setText("Invalid username or password!");
            loginErrorLabel.setVisible(true);
        }
    }

    @FXML
    public void closeLoginModal() {
        loginModal.setVisible(false);
    }

    private void updateAdminButtonUI() {
        if (isAdminLoggedIn) {
            adminAuthButton.setText("+ Add Restaurant");
        } else {
            adminAuthButton.setText("🔑 Admin Login");
        }
    }

    //Category
    private void filterCategory() {
        String selected = categoryFilter.getValue();
        if (selected == null || selected.equals("ALL")) {
            renderRestaurantGrid(foodPlaces);
            return;
        }
        List<FoodPlace> filtered = foodPlaces.stream()
                .filter(p -> p.getCategoryName().equalsIgnoreCase(selected))
                .toList();
        renderRestaurantGrid(filtered);
    }

    private void renderRestaurantGrid(List<FoodPlace> list) {
        restaurantGrid.getChildren().clear();
        for (FoodPlace place : list) {
            VBox card = new VBox(8);
            card.getStyleClass().add("food-card");

            ImageView logo = new ImageView();
            Image img = loadImage(place.getLogoPath());
            if (img != null) {
                logo.setImage(img);
            }

            logo.setFitWidth(100);
            logo.setFitHeight(100);
            logo.setPreserveRatio(true);

            Label name = new Label(place.getName());
            name.getStyleClass().add("card-title");

            card.getChildren().addAll(logo, name);
            card.setOnMouseClicked(e -> showDetailsScreen(place));
            restaurantGrid.getChildren().add(card);
        }
    }

    //Place Details
    private void showDetailsScreen(FoodPlace place) {
        this.currentSelectedPlace = place;
        detailNameLabel.setText(place.getName());
        detailHoursLabel.setText("• " + place.getOpeningHours());
        detailContactLabel.setText("• Contact Number: " + place.getContactInfo());
        detailAddressLabel.setText("• Address: " + place.getAddress());

        Image logoImg = loadImage(place.getLogoPath());
        if (logoImg != null) detailLogoImageView.setImage(logoImg);

        Image storeImg = loadImage(place.getStoreImagePath());
        if (storeImg != null) detailStoreImageView.setImage(storeImg);

        switchScreen(detailsScreen);
    }

    @FXML
    public void showMenuScreen() {
        if (currentSelectedPlace != null) {
            menuTitleLabel.setText(currentSelectedPlace.getName() + " Menu");
            currentMenuIndex = 0;
            updateMenuDisplay();
            switchScreen(menuScreen);
        }
    }

    @FXML
    public void prevMenuImage() {
        if (currentSelectedPlace != null && !currentSelectedPlace.getMenuImagePaths().isEmpty()) {
            if (currentMenuIndex > 0) {
                currentMenuIndex--;
                updateMenuDisplay();
            }
        }
    }

    @FXML
    public void nextMenuImage() {
        if (currentSelectedPlace != null && !currentSelectedPlace.getMenuImagePaths().isEmpty()) {
            if (currentMenuIndex < currentSelectedPlace.getMenuImagePaths().size() - 1) {
                currentMenuIndex++;
                updateMenuDisplay();
            }
        }
    }

    private void updateMenuDisplay() {
        List<String> menus = currentSelectedPlace.getMenuImagePaths();
        if (menus != null && !menus.isEmpty()) {
            Image menuImg = loadImage(menus.get(currentMenuIndex));
            if (menuImg != null) {
                menuImageView.setImage(menuImg);
            }
            menuPageLabel.setText("Page " + (currentMenuIndex + 1) + "/" + menus.size());
        } else {
            menuImageView.setImage(null);
            menuPageLabel.setText("Page 0/0");
        }
    }

    @FXML
    public void openGoogleMaps() {
        if (currentSelectedPlace != null && currentSelectedPlace.getMapUrl() != null && !currentSelectedPlace.getMapUrl().isBlank()) {
            try {
                Desktop.getDesktop().browse(new URI(currentSelectedPlace.getMapUrl()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showMainScreen() {
        switchScreen(mainScreen);
    }

    @FXML
    public void showDetailsScreen() {
        switchScreen(detailsScreen);
    }

    @FXML
    public void showAddRestaurantForm() {
        if (!isAdminLoggedIn) {
            handleAdminButtonClick();
            return;
        }
        switchScreen(addFormScreen);
    }

    @FXML
    public void uploadLogo() {
        uploadedLogoPath = selectFile();
    }

    @FXML public void uploadStoreImage() {
        uploadedStorePath = selectFile();
    }

    @FXML public void uploadMenuImage() {
        uploadedMenuPath = selectFile();
    }

    private String selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(mainScreen.getScene().getWindow());
        return (file != null) ? file.toURI().toString() : "";
    }

    @FXML
    public void saveRestaurant() {
        if (!isAdminLoggedIn) return;

        String cat = inputCategory.getText() != null ? inputCategory.getText().toUpperCase() : "";
        List<String> menuList = new ArrayList<>();
        if (uploadedMenuPath != null && !uploadedMenuPath.isBlank()) {
            menuList.add(uploadedMenuPath);
        }

        FoodPlace newPlace;
        if (cat.contains("CAFE")) {
            newPlace = new Cafe(String.valueOf(System.currentTimeMillis()), inputName.getText(), "CAFE",
                    inputAddress.getText(), inputContact.getText(), inputHours.getText(),
                    uploadedLogoPath, uploadedStorePath, menuList, inputMapUrl.getText(), true);
        } else if (cat.contains("STALL")) {
            newPlace = new Stall(String.valueOf(System.currentTimeMillis()), inputName.getText(), "STALL",
                    inputAddress.getText(), inputContact.getText(), inputHours.getText(),
                    uploadedLogoPath, uploadedStorePath, menuList, inputMapUrl.getText());
        } else {
            newPlace = new Restaurant(String.valueOf(System.currentTimeMillis()), inputName.getText(), "RESTAURANT",
                    inputAddress.getText(), inputContact.getText(), inputHours.getText(),
                    uploadedLogoPath, uploadedStorePath, menuList, inputMapUrl.getText(), cat);
        }

        foodPlaces.add(newPlace);
        renderRestaurantGrid(foodPlaces);
        showMainScreen();
    }

    private Image loadImage(String path) {
        if (path == null || path.isBlank()) return null;
        try {
            if (path.startsWith("/")) {
                InputStream is = getClass().getResourceAsStream(path);
                if (is != null) return new Image(is);
            }
            return new Image(path);
        } catch (Exception e) {
            return null;
        }
    }

    private void switchScreen(VBox targetScreen) {
        mainScreen.setVisible(false);
        detailsScreen.setVisible(false);
        menuScreen.setVisible(false);
        addFormScreen.setVisible(false);
        loginModal.setVisible(false);
        targetScreen.setVisible(true);
    }
}