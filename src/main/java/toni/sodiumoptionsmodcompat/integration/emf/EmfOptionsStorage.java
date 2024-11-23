package toni.sodiumoptionsmodcompat.integration.emf;

import traben.entity_model_features.EMF;
import traben.entity_model_features.config.EMFConfig;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class EmfOptionsStorage implements OptionStorage<EMFConfig> {

    public static final EmfOptionsStorage INSTANCE = new EmfOptionsStorage();

    @Override
    public EMFConfig getData() {
        return EMF.config().getConfig();
    }

    @Override
    public void save() {
        EMF.config().saveToFile();
    }

}
