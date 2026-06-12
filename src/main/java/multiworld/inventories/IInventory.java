package multiworld.inventories;

import net.minecraft.class_10630;
import net.minecraft.class_1799;
import net.minecraft.class_2371;

public interface IInventory {
    void mw$setItems(class_2371<class_1799> items);

    class_2371<class_1799> mw$getItems();

    class_10630 mw$getEquipment();
}
