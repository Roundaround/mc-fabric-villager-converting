package me.roundaround.villagerconverting.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import me.roundaround.villagerconverting.generated.Constants;
import me.roundaround.villagerconverting.roundalib.client.gui.screen.ConfigScreen;

public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
      if (!config.isReady()) {
        return new NotInWorldConfigScreen(screen);
      }
      return new ConfigScreen(screen, Constants.MOD_ID, config);
    };
  }
}
