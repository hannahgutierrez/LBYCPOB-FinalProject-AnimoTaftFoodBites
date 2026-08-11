package ph.edu.dlsu.lbycpob.animotaftfoodbites.repository;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.FoodPlaceEntity;

import java.util.*;
import java.util.stream.Collectors;

public class FoodPlaceRepository {
    private final Map<UUID, FoodPlaceEntity> database = new LinkedHashMap<>();

    public List<FoodPlaceEntity> findAllByOrderByNameAsc() {
        return database.values().stream()
                .sorted(Comparator.comparing(
                        FoodPlaceEntity::getName,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .collect(Collectors.toList());
    }
}
