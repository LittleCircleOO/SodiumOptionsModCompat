package toni.sodiumoptionsmodcompat.integration.dynamicfps;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.DynamicFPSConfig;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class DynamicFpsGeneralStorage implements OptionStorage<DynamicFPSConfig> {

    public final static DynamicFpsGeneralStorage INSTANCE = new DynamicFpsGeneralStorage();

    @Override
    public DynamicFPSConfig getData() {
        return DynamicFPSConfig.INSTANCE;
    }

    @Override
    public void save() {
        DynamicFPSMod.onConfigChanged();
    }

}
