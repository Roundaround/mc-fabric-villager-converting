package me.roundaround.villagerconverting;

import me.roundaround.villagerconverting.config.modmenu.VillagerConvertingConfig;
import net.fabricmc.api.ModInitializer;

public final class VillagerConvertingMod implements ModInitializer {
  public static final String MOD_ID = "villagerconverting";

  @Override
  public void onInitialize() {
    VillagerConvertingConfig.getInstance().init();
  }
}
