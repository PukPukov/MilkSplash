package com.etsuni.milksplash.cleaneffectprovider;

import com.etsuni.milksplash.MilkSplash;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

@RequiredArgsConstructor
public class TweakedMilkCleanEffectProvider extends MilkCleanEffectProvider {
    
    private final boolean removeOnlyNegative;
    private final boolean removeOnlyOwn;
    
    // Lazy list
    private static Set<PotionEffectType> _readOnlyNegativeEffects = null; // Not supposed to be used directly
    public Set<PotionEffectType> negativeEffects(boolean recreateList) {
        if (_readOnlyNegativeEffects != null && !recreateList)
            return _readOnlyNegativeEffects;
        Set<PotionEffectType> negEffects = new HashSet<>();
        
        negEffects.add(PotionEffectType.SLOW_DIGGING);
        negEffects.add(PotionEffectType.CONFUSION);
        
        if(MilkSplash.VERSION.equals("1.19.2")) {
            negEffects.add(PotionEffectType.DARKNESS);
        }
        negEffects.add(PotionEffectType.HUNGER);
        negEffects.add(PotionEffectType.POISON);
        negEffects.add(PotionEffectType.SLOW);
        negEffects.add(PotionEffectType.WITHER);
        negEffects.add(PotionEffectType.WEAKNESS);
        negEffects.add(PotionEffectType.UNLUCK);
        negEffects.add(PotionEffectType.LEVITATION);
        
        _readOnlyNegativeEffects = Collections.unmodifiableSet(negEffects);
        return negEffects;
    }
    
    public Set<PotionEffectType> allEffects() {
        return Set.of(PotionEffectType.values());
    }
    
    @Override
    public Set<PotionEffectType> shouldBeCleanedFor(LivingEntity thrower, LivingEntity receiver) {
        if (this.removeOnlyOwn && (!thrower.equals(receiver))) return Set.of();
        if (this.removeOnlyNegative) return negativeEffects(false);
        else return allEffects();
    }
    
}