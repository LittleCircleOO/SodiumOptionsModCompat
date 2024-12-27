package toni.sodiumoptionsmodcompat.integration.chunksfadein;

import com.koteinik.chunksfadein.config.Config;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
#endif

public class ChunksFadeInOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new ChunksFadeInOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        Config.save();
    }

}