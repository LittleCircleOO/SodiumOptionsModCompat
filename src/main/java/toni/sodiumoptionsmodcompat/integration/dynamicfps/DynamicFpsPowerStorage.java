package toni.sodiumoptionsmodcompat.integration.dynamicfps;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.Config;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class DynamicFpsPowerStorage implements OptionStorage<Config> {

    private final Config config;

    public DynamicFpsPowerStorage(Config config) {
        this.config = config;
    }

    @Override
    public Config getData() {
        return this.config;
    }

    @Override
    public void save() {
        DynamicFPSMod.onConfigChanged();
    }

}
