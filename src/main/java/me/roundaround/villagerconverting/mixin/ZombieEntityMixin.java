package me.roundaround.villagerconverting.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onKilledOther(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;)V", at = @At("HEAD"), cancellable = true)
    private void onKilledOther(ServerWorld world, LivingEntity other, CallbackInfo callbackInfo) {
        if (!(other instanceof VillagerEntity villagerEntity)) {
            // Not killing a villager, fall back to vanilla behavior.
            return;
        }

        if (world.getDifficulty() == Difficulty.PEACEFUL) {
            // If we're somehow on peaceful difficulty (and cannot spawn zombie villagers),
            // fall back to vanilla behavior.
            return;
        }

        // TODO: Depend on RoundaLib and add config that lets us change the behavior (require name or not).
        if (!villagerEntity.hasCustomName()) {
            // If the villager does not have a custom name, fall back to vanilla behavior.
            return;
        }

        // Recreate zombie villager creation code.
        ZombieVillagerEntity zombieVillagerEntity = villagerEntity.convertTo(EntityType.ZOMBIE_VILLAGER, false);
        zombieVillagerEntity.initialize(world, world.getLocalDifficulty(zombieVillagerEntity.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true), null);
        zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
        zombieVillagerEntity.setGossipData(villagerEntity.getGossip().serialize(NbtOps.INSTANCE).getValue());
        zombieVillagerEntity.setOfferData(villagerEntity.getOffers().toNbt());
        zombieVillagerEntity.setXp(villagerEntity.getExperience());
        if (!this.isSilent()) {
            world.syncWorldEvent(null, WorldEvents.ZOMBIE_INFECTS_VILLAGER, this.getBlockPos(), 0);
        }

        // Cancel the execution of the rest of the method.
        callbackInfo.cancel();
    }
}
