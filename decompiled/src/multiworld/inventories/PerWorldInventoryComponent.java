/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_11368
 *  net.minecraft.class_11372
 *  net.minecraft.class_1304
 *  net.minecraft.class_1661
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  org.ladysnake.cca.api.v3.component.ComponentV3
 *  org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent
 */
package multiworld.inventories;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import multiworld.inventories.IInventory;
import net.minecraft.class_11368;
import net.minecraft.class_11372;
import net.minecraft.class_1304;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PerWorldInventoryComponent
implements ComponentV3,
AutoSyncedComponent {
    private static final String KEY = "inventories";
    private final Map<class_2960, PlayerInventorySnapshot> inventories = new HashMap<class_2960, PlayerInventorySnapshot>();
    private static final Codec<class_1799> STACK_CODEC = class_1799.field_24671.optionalFieldOf("item").xmap(opt -> opt.orElse(class_1799.field_8037), stack -> stack.method_7960() ? Optional.empty() : Optional.of(stack)).codec();
    private static final Codec<List<class_1799>> STACK_LIST_CODEC = STACK_CODEC.listOf();

    public void saveInventory(class_5321<class_1937> levelKey, class_1661 inv) {
        IInventory acc = (IInventory)inv;
        this.inventories.put(levelKey.method_29177(), new PlayerInventorySnapshot(List.copyOf(acc.mw$getItems()), List.of(acc.mw$getEquipment().method_66659(class_1304.field_6169), acc.mw$getEquipment().method_66659(class_1304.field_6174), acc.mw$getEquipment().method_66659(class_1304.field_6172), acc.mw$getEquipment().method_66659(class_1304.field_6166)), List.of(acc.mw$getEquipment().method_66659(class_1304.field_6171))));
    }

    public void loadInventory(class_5321<class_1937> levelKey, class_1661 inv) {
        PlayerInventorySnapshot snap = this.inventories.get(levelKey.method_29177());
        IInventory acc = (IInventory)inv;
        if (snap != null) {
            for (int i = 0; i < snap.items().size(); ++i) {
                inv.method_5447(i, snap.items().get(i));
            }
            acc.mw$getEquipment().method_66660(class_1304.field_6169, snap.armor().get(0));
            acc.mw$getEquipment().method_66660(class_1304.field_6174, snap.armor().get(1));
            acc.mw$getEquipment().method_66660(class_1304.field_6172, snap.armor().get(2));
            acc.mw$getEquipment().method_66660(class_1304.field_6166, snap.armor().get(3));
            acc.mw$getEquipment().method_66660(class_1304.field_6171, snap.offhand().get(0));
        } else {
            inv.method_5448();
        }
    }

    public void writeData(class_11372 out) {
        HashMap raw = new HashMap();
        this.inventories.forEach((id, snap) -> raw.put(id.toString(), snap));
        out.method_71468(KEY, PlayerInventorySnapshot.INVENTORY_MAP_CODEC, raw);
    }

    public void readData(class_11368 in) {
        this.inventories.clear();
        Map raw = in.method_71426(KEY, PlayerInventorySnapshot.INVENTORY_MAP_CODEC).orElseGet(Map::of);
        raw.forEach((key, snap) -> {
            class_2960 id = class_2960.method_60654((String)key);
            this.inventories.put(id, (PlayerInventorySnapshot)snap);
        });
    }

    public void copyFrom(PerWorldInventoryComponent other) {
        this.inventories.clear();
        this.inventories.putAll(other.inventories);
    }

    public boolean isRequiredOnClient() {
        return false;
    }

    public record PlayerInventorySnapshot(List<class_1799> items, List<class_1799> armor, List<class_1799> offhand) {
        public static final Codec<PlayerInventorySnapshot> SNAPSHOT_CODEC = RecordCodecBuilder.create(instance -> instance.group((App)STACK_LIST_CODEC.fieldOf("items").forGetter(PlayerInventorySnapshot::items), (App)STACK_LIST_CODEC.fieldOf("armor").forGetter(PlayerInventorySnapshot::armor), (App)STACK_LIST_CODEC.fieldOf("offhand").forGetter(PlayerInventorySnapshot::offhand)).apply((Applicative)instance, PlayerInventorySnapshot::new));
        private static final Codec<Map<String, PlayerInventorySnapshot>> INVENTORY_MAP_CODEC = Codec.unboundedMap((Codec)Codec.STRING, SNAPSHOT_CODEC);
    }
}
