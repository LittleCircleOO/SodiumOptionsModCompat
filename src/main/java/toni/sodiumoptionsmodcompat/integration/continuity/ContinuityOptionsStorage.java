package toni.sodiumoptionsmodcompat.integration.continuity;

import me.pepperbell.continuity.client.config.ContinuityConfig;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class ContinuityOptionsStorage implements OptionStorage<ContinuityConfig> {
    public static final ContinuityOptionsStorage  INSTANCE = new ContinuityOptionsStorage();

    @Override
    public ContinuityConfig getData() {
        return ContinuityConfig.INSTANCE;
    }

    @Override
    public void save() {
        ContinuityConfig.INSTANCE.save();
    }
}
