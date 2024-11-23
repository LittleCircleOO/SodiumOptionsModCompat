package toni.sodiumoptionsmodcompat.integration;

import dynamic_fps.impl.PowerState;
import toni.sodiumoptionsmodcompat.integration.cullleaves.CullLeavesOptionPage;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.pages.DynamicFpsGeneralPage;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.pages.DynamicFpsPowerPage;
import toni.sodiumoptionsmodcompat.integration.emf.EmfModelsOptionPage;
import toni.sodiumoptionsmodcompat.integration.esf.EsfSoundsOptionPage;
import toni.sodiumoptionsmodcompat.integration.etf.EtfMiscOptionPage;
import toni.sodiumoptionsmodcompat.integration.etf.EtfTexturesOptionPage;
import toni.sodiumoptionsmodcompat.integration.ryoamiclights.RyoamicLightsOptionPage;
import net.neoforged.fml.ModList;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;

public class Integrations {

    public static void init() {
        OptionGUIConstructionEvent.BUS.addListener(Integrations::event);
    }

    private static void event(OptionGUIConstructionEvent event) {
        if (isModLoaded("entity_texture_features")) {
            event.addPage(new EtfTexturesOptionPage());
            if (isModLoaded("entity_model_features")) {
                event.addPage(new EmfModelsOptionPage());
            }
            if (isModLoaded("entity_sound_features")) {
                event.addPage(new EsfSoundsOptionPage());
            }
            event.addPage(new EtfMiscOptionPage());
        }
        if (isModLoaded("cullleaves")) {
            event.addPage(new CullLeavesOptionPage());
        }
        if (isModLoaded("ryoamiclights")) {
            event.addPage(new RyoamicLightsOptionPage());
        }
        if (isModLoaded("dynamic_fps")) {
            event.addPage(new DynamicFpsGeneralPage());
             event.addPage(new DynamicFpsPowerPage(PowerState.HOVERED));
             event.addPage(new DynamicFpsPowerPage(PowerState.UNFOCUSED));
             event.addPage(new DynamicFpsPowerPage(PowerState.INVISIBLE));
             event.addPage(new DynamicFpsPowerPage(PowerState.ABANDONED));
        }
    }

    private static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

}
