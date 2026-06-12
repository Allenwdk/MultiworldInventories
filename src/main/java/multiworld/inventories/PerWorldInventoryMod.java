package multiworld.inventories;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1937;
import net.minecraft.class_5321;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

public class PerWorldInventoryMod implements ModInitializer {
    public static final String MOD_ID = "multiworld-inventories";
    public static final Logger LOGGER = LoggerFactory.getLogger("MultiworldInventories");
    private static boolean netherKeepsInventory = true;
    private static boolean theendKeepsInventory = true;

    @Override
    public void onInitialize() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            class_5321<class_1937> originDim = this.getInventoryDim(origin.method_27983());
            class_5321<class_1937> destDim = this.getInventoryDim(destination.method_27983());

            PerWorldInventoryComponent comp = ModComponents.PER_WORLD_INV.get(player);

            // 1. 保存原世界的物品栏
            comp.saveInventory(originDim, player.method_31548());

            // 2. 只有当目标维度与原维度不同时，才加载目标维度的物品栏
            // 如果相同（如主世界↔下界共享），物品栏应保持不变
            if (originDim != destDim) {
                comp.loadInventory(destDim, player.method_31548());
            }
        });
        this.loadConfig();
        LOGGER.info("Multiworld Inventories initialized!");
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
        File cdir = FabricLoader.getInstance().getConfigDir().toFile();
        File dir = new File(cdir, "multiworld-inventories");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!(config = new File(dir, "config.yml")).exists()) {
            try {
                config.createNewFile();
                this.saveDefaultConfig(config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveDefaultConfig(File config) throws IOException {
        String str = """
                # Multiworld Inventories Mod Config
                netherSharesInventory: true
                theendSharesInventory: true
                """;
        Files.write(config.toPath(), str.getBytes());
    }

    public void readConfig(File config) throws IOException {
        List<String> lines = Files.readAllLines(config.toPath());
        for (String line : lines) {
            String[] spl;
            if (line.startsWith("# ") || line.indexOf(':') == -1 || (spl = line.split(Pattern.quote(":"))).length <= 1)
                continue;
            String key = spl[0].trim();
            String val = spl[1].trim();
            if (key.equalsIgnoreCase("netherSharesInventory")) {
                netherKeepsInventory = Boolean.parseBoolean(val);
                continue;
            }
            if (key.equalsIgnoreCase("theendSharesInventory")) {
                theendKeepsInventory = Boolean.parseBoolean(val);
            }
        }
    }
}
