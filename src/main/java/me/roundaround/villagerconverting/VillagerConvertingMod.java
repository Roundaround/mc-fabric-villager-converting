package me.roundaround.villagerconverting;

import me.roundaround.gradle.api.annotation.Entrypoint;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import net.fabricmc.api.ModInitializer;

@Entrypoint(Entrypoint.MAIN)
public final class VillagerConvertingMod implements ModInitializer {
  @Override
  public void onInitialize() {
    VillagerConvertingConfig.getInstance().init();
  }
}
