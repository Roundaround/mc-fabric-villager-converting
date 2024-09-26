package me.roundaround.villagerconverting.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.roundalib.client.gui.screen.ConfigScreen;
import me.roundaround.villagerconverting.VillagerConvertingMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuScreen implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
      if (!config.isReady()) {
        return null;
      }
      return new ConfigScreen(screen, VillagerConvertingMod.MOD_ID, config);
    };
  }
}
