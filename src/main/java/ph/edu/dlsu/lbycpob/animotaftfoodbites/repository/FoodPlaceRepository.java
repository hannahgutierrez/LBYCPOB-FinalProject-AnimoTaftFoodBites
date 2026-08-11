package ph.edu.dlsu.lbycpob.animotaftfoodbites.repository;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.FoodPlaceEntity;

import java.util.*;
import java.util.stream.Collectors;

public class FoodPlaceRepository {
    private final Map<UUID, FoodPlaceEntity> database = new LinkedHashMap<>();
}
