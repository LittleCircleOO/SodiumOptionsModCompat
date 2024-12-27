package toni.sodiumoptionsmodcompat.integration.lambdynamiclights;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

import dev.lambdaurora.lambdynlights.LambDynLights;

public class LambDynamicLightsOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new LambDynamicLightsOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        LambDynLights.get().config.save();
    }

}