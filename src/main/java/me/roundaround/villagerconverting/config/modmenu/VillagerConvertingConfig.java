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
            .setComment("villagerconverting.mod_enabled.comment")
            .build());
    REQUIRE_NAME = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder("requireName", "villagerconverting.require_name.label")
            .setDefaultValue(false)
            .setComment("villagerconverting.require_name.comment")
            .build());
  }
}
