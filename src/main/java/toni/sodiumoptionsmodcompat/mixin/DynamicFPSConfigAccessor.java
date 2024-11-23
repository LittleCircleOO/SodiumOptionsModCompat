package toni.sodiumoptionsmodcompat.mixin;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(DynamicFPSMod.class)
public interface DynamicFPSConfigAccessor {
    @Accessor("config")
    public static Config getConfig(){
        throw new AssertionError();
    }
}