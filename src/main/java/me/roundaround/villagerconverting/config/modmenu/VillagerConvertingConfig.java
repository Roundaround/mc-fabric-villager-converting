package me.roundaround.villagerconverting.config.modmenu;

import me.roundaround.roundalib.config.ModConfig;
import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.villagerconverting.VillagerConvertingMod;

public class VillagerConvertingConfig extends ModConfig {
  public BooleanConfigOption MOD_ENABLED;
  public BooleanConfigOption REQUIRE_NAME;

  public VillagerConvertingConfig() {
    super(VillagerConvertingMod.MOD_ID);

    MOD_ENABLED = registerConfigOption(
        BooleanConfigOption
            .builder("modEnabled", "villagerconverting.mod_enabled.label")
            .setComment(
                "Simple toggle for the mod! When set to false, the",
                "villagers will fall back to vanilla behavior/probability",
                "for zombie-conversion.")
            .build());
    REQUIRE_NAME = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder("requireName", "villagerconverting.require_name.label")
            .setDefaultValue(false)
            .setComment(
                "When set to true, only villagers that have a custom",
                "name set (i.e. with a nametag) will be guaranteed to",
                "convert! Non-named villagers will fall back to vanilla",
                "behavior.")
            .build());
  }
}
