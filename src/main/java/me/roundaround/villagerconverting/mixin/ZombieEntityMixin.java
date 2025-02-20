package me.roundaround.villagerconverting.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin {
  @ModifyExpressionValue(
      method = "onKilledOther", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;",
      ordinal = 0
  )
  )
  private Difficulty replaceDifficulty(Difficulty original, @Local(argsOnly = true) LivingEntity other) {
    // Bypass not converting in easy difficulty.

    VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
    if (!config.modEnabled.getValue()) {
      // Mod is disabled, fall back to vanilla behavior.
      return original;
    }

    if (config.requireName.getValue() && !other.hasCustomName()) {
      // If the villager does not have a custom name, fall back to vanilla behavior.
      return original;
    }

    return Difficulty.NORMAL;
  }

  @ModifyExpressionValue(
      method = "onKilledOther",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextBoolean()Z")
  )
  private boolean replaceRandomChance(boolean original, @Local VillagerEntity villagerEntity) {
    // Bypass 50-50 chance of converting in normal difficulty.

    VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
    if (!config.modEnabled.getValue()) {
      // Mod is disabled, fall back to vanilla behavior.
      return original;
    }

    if (config.requireName.getValue() && !villagerEntity.hasCustomName()) {
      // If the villager does not have a custom name, fall back to vanilla behavior.
      return original;
    }

    // False means we don't want to return early.
    return false;
  }
}
