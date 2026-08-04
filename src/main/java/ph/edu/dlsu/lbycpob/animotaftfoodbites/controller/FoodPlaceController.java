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
    //Screens & Modals
    @FXML
    private VBox mainScreen, detailsScreen, menuScreen, addFormScreen, loginModal;

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
        String mapsUrl = "https://www.google.com/maps/dir/?api=1&origin=De+La+Salle+University+South+Gate,+Taft+Ave,+Malate,+Manila&destination=McDonald's+Taft+Avenue+Manila";
        List<String> mcdoMenus = new ArrayList<>();
        for (int i = 1; i <= 19; i++) {
            mcdoMenus.add("/images/Mcdo/McdoMenu" + i + ".png");
        }
        foodPlaces.add(new Restaurant(
                "1",
                "McDonald's Taft",
                "Fast Food",
                "2399 Taft Avenue, Malate, Manila",
                "(02) 8888 6236",
                "Operation Hours: 24-hours",
                "/images/Mcdo/McdoLogo.jpg",
                "/images/Mcdo/McdoPlacePhoto.jpg",
                mcdoMenus,
                mapsUrl,
                "Fast Food"
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

    private void switchScreen(VBox targetScreen) {
        mainScreen.setVisible(false);
        detailsScreen.setVisible(false);
        menuScreen.setVisible(false);
        addFormScreen.setVisible(false);
        loginModal.setVisible(false);
        targetScreen.setVisible(true);
    }
}