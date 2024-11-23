package toni.sodiumoptionsmodcompat.integration.etf;

import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class EtfOptionsStorage implements OptionStorage<ETFConfig> {

    public static final EtfOptionsStorage INSTANCE = new EtfOptionsStorage();

    @Override
    public ETFConfig getData() {
        return ETF.config().getConfig();
    }

    @Override
    public void save() {
        ETF.config().saveToFile();
    }

}
