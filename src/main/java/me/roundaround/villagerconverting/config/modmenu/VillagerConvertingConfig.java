package me.roundaround.villagerconverting.config.modmenu;

import me.roundaround.roundalib.config.ConfigPath;
import me.roundaround.roundalib.config.manage.ModConfigImpl;
import me.roundaround.roundalib.config.manage.store.GameScopedFileStore;
import me.roundaround.roundalib.config.manage.store.WorldScopedFileStore;
import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.villagerconverting.VillagerConvertingMod;

public class VillagerConvertingConfig extends ModConfigImpl implements WorldScopedFileStore {
  private static VillagerConvertingConfig instance;

  public BooleanConfigOption modEnabled;
  public BooleanConfigOption requireName;

  public static VillagerConvertingConfig getInstance() {
    if (instance == null) {
      instance = new VillagerConvertingConfig();
    }
    return instance;
  }

  private VillagerConvertingConfig() {
    super(VillagerConvertingMod.MOD_ID);
  }

  @Override
  public void registerOptions() {
    modEnabled = this.register(BooleanConfigOption.builder(ConfigPath.of("modEnabled"))
        .setDefaultValue(true)
        .setComment("Simple toggle for the mod! When set to false, the",
            "villagers will fall back to vanilla behavior/probability", "for zombie-conversion."
        )
        .build());
    requireName = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("requireName"))
        .setDefaultValue(false)
        .setComment("When set to true, only villagers that have a custom",
            "name set (i.e. with a nametag) will be guaranteed to",
            "convert! Non-named villagers will fall back to vanilla", "behavior."
        )
        .build());
  }

  @Override
  public void readFromStore() {
    // Load from the legacy instance (game-scoped) first so we can
    // migrate old game-scoped config files.
    Legacy legacy = Legacy.load();
    this.modEnabled.setValue(legacy.modEnabled.getPendingValue());
    this.requireName.setValue(legacy.requireName.getPendingValue());

    WorldScopedFileStore.super.readFromStore();
  }

  private static class Legacy extends ModConfigImpl implements GameScopedFileStore {
    private static Legacy instance;

    public BooleanConfigOption modEnabled;
    public BooleanConfigOption requireName;

    public static Legacy load() {
      if (instance == null) {
        instance = new Legacy();
        instance.init();
      }
      return instance;
    }

    private Legacy() {
      super(VillagerConvertingMod.MOD_ID);
    }

    @Override
    public void registerOptions() {
      modEnabled = this.register(
          BooleanConfigOption.builder(ConfigPath.of("modEnabled")).setDefaultValue(true).build());
      requireName = this.register(
          BooleanConfigOption.yesNoBuilder(ConfigPath.of("requireName")).setDefaultValue(false).build());
    }

    @Override
    public void writeToStore() {
      // Don't ever create a config file - only read from it.
    }
  }
}
