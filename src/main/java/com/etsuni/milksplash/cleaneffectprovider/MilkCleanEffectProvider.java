package com.etsuni.milksplash.cleaneffectprovider;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;

@ApiStatus.Experimental
public abstract class MilkCleanEffectProvider {
    
    public abstract Set<PotionEffectType> shouldBeCleanedFor(LivingEntity thrower, LivingEntity receiver);
    
    public void clean(LivingEntity thrower, LivingEntity receiver) {
        var toClean = shouldBeCleanedFor(thrower, receiver);
        receiver.getActivePotionEffects().stream()
            .map(PotionEffect::getType)
            .filter(toClean::contains)
            .forEach(receiver::removePotionEffect);
    }
    
}