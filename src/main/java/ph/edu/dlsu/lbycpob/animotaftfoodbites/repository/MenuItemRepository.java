package ph.edu.dlsu.lbycpob.animotaftfoodbites.repository;

import ph.edu.dlsu.lbycpob.animotaftfoodbites.model.MenuItemEntity;
import java.util.*;

public class MenuItemRepository {
    private final Map<UUID, MenuItemEntity> database = new LinkedHashMap<>();
}
