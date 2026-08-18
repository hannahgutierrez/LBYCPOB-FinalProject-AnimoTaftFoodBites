package ph.edu.dlsu.lbycpob.animotaftfoodbites.dto;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.FoodPlaceEntity;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.MenuItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Dtos {


    public record FoodPlaceDetail(
            UUID id,
            String name,
            String address,
            String contactNumber,
            String operationHours,
            String category,
            String logoUrl,
            String frontViewUrl,
            String googleMapLink,
            String foodType,
            Boolean wifiAvailable,
            List<MenuItemDto> menuItems
    ) {
        public static FoodPlaceDetail of(FoodPlaceEntity entity) {
            List<MenuItemDto> items = entity.getMenuItems().stream()
                    .map(MenuItemDto::of)
                    .toList();

            return new FoodPlaceDetail(
                    entity.getId(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContactNumber(),
                    entity.getOperationHours(),
                    entity.getCategory(),
                    entity.getLogoUrl(),
                    entity.getFrontViewUrl(),
                    entity.getGoogleMapLink(),
                    entity.getFoodType(),
                    entity.getWifiAvailable(),
                    items
            );
        }
    }


}
