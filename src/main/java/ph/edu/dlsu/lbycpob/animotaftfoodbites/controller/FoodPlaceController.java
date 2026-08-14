package ph.edu.dlsu.lbycpob.animotaftfoodbites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.User;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Cafe;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.FoodPlace;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Restaurant;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.oop.Stall;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.util.DataStorage;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class    FoodPlaceController {


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

    // Register Controls
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;
    @FXML private PasswordField regConfirmPasswordField;
    @FXML private Label regErrorLabel;

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

    // Form Controls
    @FXML private TextField inputName, inputCategory, inputAddress, inputHours, inputContact, inputMapUrl;
    private String uploadedLogoPath, uploadedStorePath, uploadedMenuPath;

    private List<FoodPlace> foodPlaces = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private FoodPlace currentSelectedPlace;
    private int currentMenuIndex = 0;

    // Auth State
    private boolean isLoggedIn = false;
    private User currentUser = null;




    @FXML
    public void initialize() {
        if (categoryFilter != null) {
            categoryFilter.getItems().addAll("ALL", "RESTAURANT", "CAFE", "STALL");
            categoryFilter.setOnAction(e -> filterCategory());
        }

        List<String> zusMenus = new ArrayList<>();
        zusMenus.add("/images/zus/ZUSMenu.jpg");



    private void updateAdminButtonUI() {
        if (isAdminLoggedIn) {
            adminAuthButton.setText("+ Add Restaurant");
        } else {
            adminAuthButton.setText("🔑 Admin Login");
        }
    }

    //  Remove / delete food place
    @FXML
    public void deleteCurrentPlace() {
        if (currentSelectedPlace != null && isLoggedIn) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure this place has closed and should be removed?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    foodPlaces.remove(currentSelectedPlace);
                    DataStorage.saveFoodPlaces(foodPlaces);
                    renderRestaurantGrid(foodPlaces);
                    showMainScreen();
                }
            });
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

}