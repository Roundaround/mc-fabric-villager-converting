package me.roundaround.villagerconverting.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.roundalib.client.gui.screen.ConfigScreen;
import me.roundaround.villagerconverting.VillagerConvertingMod;
import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;

public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
      if (!config.isReady()) {
        return new NotInWorldConfigScreen(screen);
      }
      return new ConfigScreen(screen, VillagerConvertingMod.MOD_ID, config);
    };
  }
}
