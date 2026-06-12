/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.class_1937
 *  net.minecraft.class_5321
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package multiworld.inventories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.regex.Pattern;
import multiworld.inventories.ModComponents;
import multiworld.inventories.PerWorldInventoryComponent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1937;
import net.minecraft.class_5321;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerWorldInventoryMod
implements ModInitializer {
    public static final String MOD_ID = "MultiworldInventories";
    public static final Logger LOGGER = LoggerFactory.getLogger((String)"MultiworldInventories");
    private static boolean netherKeepsInventory = true;
    private static boolean theendKeepsInventory = true;

    public void onInitialize() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            PerWorldInventoryComponent comp = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)player);
            comp.saveInventory(this.getInventoryDim((class_5321<class_1937>)origin.method_27983()), player.method_31548());
            PerWorldInventoryComponent comp1 = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)player);
            comp1.loadInventory(this.getInventoryDim((class_5321<class_1937>)destination.method_27983()), player.method_31548());
        });
        this.loadConfig();
        LOGGER.info("Hello Fabric world!");
    }

    public class_5321<class_1937> getInventoryDim(class_5321<class_1937> level) {
        if (netherKeepsInventory && level == class_1937.field_25180) {
            return class_1937.field_25179;
        }
        if (theendKeepsInventory && level == class_1937.field_25181) {
            return class_1937.field_25179;
        }
        return level;
    }

    public void loadConfig() {
        File config;
        File cdir = FabricLoader.getInstance().getConfigDirectory();
        File dir = new File(cdir, "multiworld-inventories");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!(config = new File(dir, "config.yml")).exists()) {
            try {
                config.createNewFile();
                this.saveDefaultConfig(config);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDefaultConfig(File config) throws IOException {
        String str = "# Multiworld Inventories Mod Config\nnetherSharesInventory: true\ntheendSharesInventory: true\n";
        Files.write(config.toPath(), str.getBytes(), new OpenOption[0]);
    }

    public void readConfig(File config) throws IOException {
        List<String> lines = Files.readAllLines(config.toPath());
        for (String line : lines) {
            String[] spl;
            if (line.startsWith("# ") || line.indexOf(58) == -1 || (spl = line.split(Pattern.quote(":"))).length <= 1) continue;
            String key = spl[0].trim();
            String val = spl[1].trim();
            if (key.equalsIgnoreCase("netherSharesInventory")) {
                netherKeepsInventory = Boolean.valueOf(val);
                continue;
            }
            if (!key.equalsIgnoreCase("theendSharesInventory")) continue;
            theendKeepsInventory = Boolean.valueOf(val);
        }
    }
}
