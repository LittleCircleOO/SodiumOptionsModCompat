package toni.sodiumoptionsmodcompat.integration.lambdynamiclights;

import com.google.common.collect.ImmutableList;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpact;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.caffeinemc.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.caffeinemc.mods.sodium.client.gui.options.control.SliderControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
#endif

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import toni.lib.utils.VersionUtils;
import toni.sodiumoptionsapi.api.ExtendedOptionGroup;
import toni.sodiumoptionsapi.api.OptionIdentifier;
import toni.sodiumoptionsapi.util.IOptionGroupIdAccessor;
import dev.lambdaurora.lambdynlights.DynamicLightsMode;
import dev.lambdaurora.lambdynlights.ExplosiveLightingMode;
import dev.lambdaurora.lambdynlights.LambDynLights;

#if AFTER_21_1
import dev.lambdaurora.lambdynlights.LambDynLightsConstants;
#endif

import java.util.ArrayList;
import java.util.List;

public class LambDynamicLightsOptionPage extends OptionPage {

    #if AFTER_21_1
    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource(LambDynLightsConstants.NAMESPACE, "dynamic_lights"));
    #elif FABRIC
    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource(LambDynLights.NAMESPACE, "dynamic_lights"));
    #endif

    public LambDynamicLightsOptionPage() {
        super(Component.translatable("lambdynlights.option.mode"), create());
        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(ID);
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(DynamicLightsMode.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "dynamic_lights_mode"))
                                        .setName(Component.translatable("lambdynlights.option.mode"))
                                        .setTooltip(
                                                Component.translatable("lambdynlights.tooltip.mode.1")
                                                        .append(Component.literal("\n"))
                                                        .append(Component.translatable("lambdynlights.tooltip.mode.2", DynamicLightsMode.FASTEST.getTranslatedText(), DynamicLightsMode.FAST.getTranslatedText()))
                                                        .append(Component.literal("\n"))
                                                        .append(Component.translatable("lambdynlights.tooltip.mode.3", DynamicLightsMode.FANCY.getTranslatedText()))
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, DynamicLightsMode.class, new Component[]{
                                                DynamicLightsMode.OFF.getTranslatedText(),
                                                DynamicLightsMode.FASTEST.getTranslatedText(),
                                                DynamicLightsMode.FAST.getTranslatedText(),
                                                DynamicLightsMode.FANCY.getTranslatedText()
                                        }))
                                        .setBinding((options, value) -> LambDynLights.get().config.setDynamicLightsMode(value),
                                                (options) -> LambDynLights.get().config.getDynamicLightsMode())
                                        .setImpact(OptionImpact.MEDIUM)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_entities"))
                                        .setName(Component.translatable("lambdynlights.option.light_sources.entities"))
                                        .setTooltip(Component.translatable("lambdynlights.tooltip.entities"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> LambDynLights.get().config.getEntitiesLightSource().set(value),
                                                (options) -> LambDynLights.get().config.getEntitiesLightSource().get())
                                        .build()
                        )
                        #if AFTER_21_1
                                //removed feature
                        #elif FABRIC
                        .add(
                                OptionImpl.createBuilder(boolean.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_block_entities"))
                                        .setName(Component.translatable("lambdynlights.option.light_sources.block_entities"))
                                        .setTooltip(Component.translatable("lambdynlights.tooltip.block_entities"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> LambDynLights.get().config.getBlockEntitiesLightSource().set(value),
                                                (options) -> LambDynLights.get().config.getBlockEntitiesLightSource().get())
                                        .build()
                        )
                        #endif
                        .add(
                                OptionImpl.createBuilder(boolean.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_self"))
                                        .setName(Component.translatable("lambdynlights.option.light_sources.self"))
                                        .setTooltip(Component.translatable("lambdynlights.tooltip.self_light_source"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> LambDynLights.get().config.getSelfLightSource().set(value),
                                                (options) -> LambDynLights.get().config.getSelfLightSource().get())
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_water_sensitive_check"))
                                        .setName(Component.translatable("lambdynlights.option.light_sources.water_sensitive_check"))
                                        .setTooltip(Component.translatable("lambdynlights.tooltip.water_sensitive"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> LambDynLights.get().config.getWaterSensitiveCheck().set(value),
                                                (options) -> LambDynLights.get().config.getWaterSensitiveCheck().get())
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ExplosiveLightingMode.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_creeper"))
                                        .setName(Component.translatable("entity.minecraft.creeper"))
                                        .setTooltip(
                                                Component.translatable("lambdynlights.tooltip.creeper_lighting",
                                                        ExplosiveLightingMode.OFF.getTranslatedText(),
                                                        ExplosiveLightingMode.SIMPLE.getTranslatedText(),
                                                        ExplosiveLightingMode.FANCY.getTranslatedText()
                                                )
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, ExplosiveLightingMode.class, new Component[]{
                                                ExplosiveLightingMode.OFF.getTranslatedText(),
                                                ExplosiveLightingMode.SIMPLE.getTranslatedText(),
                                                ExplosiveLightingMode.FANCY.getTranslatedText()
                                        }))
                                        .setBinding((options, value) -> LambDynLights.get().config.setCreeperLightingMode(value),
                                                (options) -> LambDynLights.get().config.getCreeperLightingMode())
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ExplosiveLightingMode.class, LambDynamicLightsOptionsStorage.INSTANCE)
                                        //.setId(new Identifier(LambDynLights.NAMESPACE, "light_sources_tnt"))
                                        .setName(Component.translatable("entity.minecraft.tnt"))
                                        .setTooltip(
                                                Component.translatable("lambdynlights.tooltip.tnt_lighting",
                                                        ExplosiveLightingMode.OFF.getTranslatedText(),
                                                        ExplosiveLightingMode.SIMPLE.getTranslatedText(),
                                                        ExplosiveLightingMode.FANCY.getTranslatedText()
                                                )
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, ExplosiveLightingMode.class, new Component[]{
                                                ExplosiveLightingMode.OFF.getTranslatedText(),
                                                ExplosiveLightingMode.SIMPLE.getTranslatedText(),
                                                ExplosiveLightingMode.FANCY.getTranslatedText()
                                        }))
                                        .setBinding((options, value) -> LambDynLights.get().config.setTntLightingMode(value),
                                                (options) -> LambDynLights.get().config.getTntLightingMode())
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}