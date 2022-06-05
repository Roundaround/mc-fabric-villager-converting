package me.roundaround.villagerconverting;

import me.roundaround.villagerconverting.config.modmenu.VillagerConvertingConfig;
import net.fabricmc.api.ModInitializer;

public final class VillagerConvertingMod implements ModInitializer {
  public static final String MOD_ID = "villagerconverting";
  public static final VillagerConvertingConfig CONFIG = new VillagerConvertingConfig();

  @Override
  public void onInitialize() {
    CONFIG.init();
  }
}
