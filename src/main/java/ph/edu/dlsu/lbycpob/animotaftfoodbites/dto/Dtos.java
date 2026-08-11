package ph.edu.dlsu.lbycpob.animotaftfoodbites.dto;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.FoodPlaceEntity;
import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.MenuItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Dtos {

    public record CreateFoodPlaceRequest(
            String name,
            String address,
            String contactNumber,
            String operationHours,
            String category,
            String logoUrl,
            String frontViewUrl,
            String googleMapLink,
            String foodType,
            Boolean wifiAvailable
    ) {}



}
