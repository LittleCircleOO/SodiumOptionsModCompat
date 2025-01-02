package toni.sodiumoptionsmodcompat.integration.continuity;

import com.google.common.collect.ImmutableList;

import me.pepperbell.continuity.client.ContinuityClient;
import me.pepperbell.continuity.client.config.ContinuityConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import toni.sodiumoptionsmodcompat.utils.VersionUtils;
import toni.sodiumoptionsapi.api.ExtendedOptionGroup;
import toni.sodiumoptionsapi.api.OptionIdentifier;
import toni.sodiumoptionsapi.util.IOptionGroupIdAccessor;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.caffeinemc.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.caffeinemc.mods.sodium.client.gui.options.control.SliderControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.caffeinemc.mods.sodium.client.gui.options.OptionFlag;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
#elif FORGE
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
#endif

import java.util.ArrayList;
import java.util.List;

public class ContinuityOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource(ContinuityClient.ID, "general"));

    public ContinuityOptionPage() {
        //super(ID, Component.translatable("config.dynamic_fps.category.general"), create());
        super(Component.literal("General"), create());
        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(ID);
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
            ExtendedOptionGroup.createBuilder(VersionUtils.resource(ContinuityClient.ID, "connected_textures"))
                .add(
                    OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                        .setName(Component.translatable("options.continuity.connected_textures"))
                        .setTooltip(Component.translatable("options.continuity.connected_textures.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setBinding(
                            (config, val) -> config.connectedTextures.set(val),
                            (config) -> config.connectedTextures.get())
                        .build()
                )
                .add(
                    OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                        .setName(Component.translatable("options.continuity.emissive_textures"))
                        .setTooltip(Component.translatable("options.continuity.emissive_textures.tooltip.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setBinding(
                            (config, val) -> config.emissiveTextures.set(val),
                            (config) -> config.emissiveTextures.get())
                        .build()
                )
                .add(
                    OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                        .setName(Component.translatable("options.continuity.custom_block_layers"))
                        .setTooltip(Component.translatable("options.continuity.custom_block_layers.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setBinding(
                            (config, val) -> config.customBlockLayers.set(val),
                            (config) -> config.customBlockLayers.get())
                        .build()
                )
                .build()
        );

        return ImmutableList.copyOf(groups);
    }
}