package me.roundaround.villagerconverting.config;

import me.roundaround.roundalib.config.manage.store.FileBackedConfigStore;
import me.roundaround.roundalib.config.manage.store.ReadOnlyFileStore;
import me.roundaround.villagerconverting.generated.Constants;
import me.roundaround.roundalib.config.ConfigPath;
import me.roundaround.roundalib.config.manage.ModConfigImpl;
import me.roundaround.roundalib.config.manage.store.GameScopedFileStore;
import me.roundaround.roundalib.config.manage.store.WorldScopedFileStore;
import me.roundaround.roundalib.config.option.BooleanConfigOption;

import java.util.Optional;

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
    super(Constants.MOD_ID);
  }

  @Override
  public void registerOptions() {
    this.modEnabled = this.register(BooleanConfigOption.builder(ConfigPath.of("modEnabled"))
        .setDefaultValue(true)
        .setComment(
            "Simple toggle for the mod! When set to false, the",
            "villagers will fall back to vanilla behavior/probability",
            "for zombie-conversion."
        )
        .build());
    this.requireName = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("requireName"))
        .setDefaultValue(false)
        .setComment(
            "When set to true, only villagers that have a custom",
            "name set (i.e. with a nametag) will be guaranteed to",
            "convert! Non-named villagers will fall back to vanilla",
            "behavior."
        )
        .build());
  }

  @Override
  public Optional<? extends FileBackedConfigStore> getLegacyStore() {
    return Optional.of(Legacy.getInstance());
  }

  private static class Legacy extends ModConfigImpl implements ReadOnlyFileStore, GameScopedFileStore {
    private static Legacy instance;

    public BooleanConfigOption modEnabled;
    public BooleanConfigOption requireName;

    public static Legacy getInstance() {
      if (instance == null) {
        instance = new Legacy();
      }
      return instance;
    }

    private Legacy() {
      super(Constants.MOD_ID);
    }

    @Override
    public void registerOptions() {
      this.modEnabled = this.register(BooleanConfigOption.builder(ConfigPath.of("modEnabled"))
          .setDefaultValue(true)
          .build());
      this.requireName = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("requireName"))
          .setDefaultValue(false)
          .build());
    }
  }
}
