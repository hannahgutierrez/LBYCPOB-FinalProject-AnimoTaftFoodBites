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

    public List<FoodPlaceEntity> findByCategoryIgnoreCaseOrderByNameAsc(String category) {
        return database.values().stream()
                .filter(p -> p.getCategory() != null &&
                        p.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(
                        FoodPlaceEntity::getName,
                        String.CASE_INSENSITIVE_ORDER
                ))
                .collect(Collectors.toList());
    }

    public Optional<FoodPlaceEntity> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    public Optional<FoodPlaceEntity> findByNameIgnoreCase(String name) {
        return database.values().stream()
                .filter(p -> p.getName() != null &&
                        p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public FoodPlaceEntity save(FoodPlaceEntity entity) {
        database.put(entity.getId(), entity);
        return entity;
    }
}
