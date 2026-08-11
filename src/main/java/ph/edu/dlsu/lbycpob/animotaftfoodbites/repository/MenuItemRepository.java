package ph.edu.dlsu.lbycpob.animotaftfoodbites.repository;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.MenuItemEntity;
import java.util.*;

public class MenuItemRepository {
    private final Map<UUID, MenuItemEntity> database = new LinkedHashMap<>();

    public MenuItemEntity save(MenuItemEntity item) {
        database.put(item.getId(), item);

        if (item.getFoodPlace() != null) {
            item.getFoodPlace().getMenuItems().add(item);
        }

        return item;
    }
}
