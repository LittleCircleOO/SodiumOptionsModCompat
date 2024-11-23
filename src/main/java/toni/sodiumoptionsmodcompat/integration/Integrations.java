package toni.sodiumoptionsmodcompat.integration;

import toni.lib.utils.PlatformUtils;
import toni.sodiumoptionsapi.api.OptionGUIConstruction;
import toni.sodiumoptionsmodcompat.integration.continuity.ContinuityOptionPage;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.pages.DynamicFpsGeneralPage;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.pages.DynamicFpsPowerPage;
import toni.sodiumoptionsmodcompat.integration.emf.EmfModelsOptionPage;
import toni.sodiumoptionsmodcompat.integration.etf.EtfMiscOptionPage;
import toni.sodiumoptionsmodcompat.integration.etf.EtfTexturesOptionPage;

import java.util.List;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
#endif

public class Integrations {

    public static void init() {
        OptionGUIConstruction.EVENT.register(Integrations::event);
    }

    private static void event(List<OptionPage> event) {
        if (PlatformUtils.isModLoaded("entity_texture_features")) {
            event.add(new EtfTexturesOptionPage());
            if (PlatformUtils.isModLoaded("entity_model_features")) {
                event.add(new EmfModelsOptionPage());
            }

            event.add(new EtfMiscOptionPage());
        }

        if (PlatformUtils.isModLoaded("dynamic_fps")) {
            event.add(new DynamicFpsGeneralPage());
            event.add(new DynamicFpsPowerPage("HOVERED"));
            event.add(new DynamicFpsPowerPage("UNFOCUSED"));
            event.add(new DynamicFpsPowerPage("INVISIBLE"));
            event.add(new DynamicFpsPowerPage("ABANDONED"));
        }

        if (PlatformUtils.isModLoaded("continuity")) {
            event.add(new ContinuityOptionPage());
        }
    }
}
