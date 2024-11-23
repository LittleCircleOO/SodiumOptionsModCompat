package toni.sodiumoptionsmodcompat.integration.etf;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import toni.lib.utils.VersionUtils;
import toni.sodiumoptionsapi.api.ExtendedOptionGroup;
import toni.sodiumoptionsapi.api.OptionIdentifier;
import toni.sodiumoptionsapi.util.IOptionGroupIdAccessor;
import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

import java.util.ArrayList;
import java.util.List;

#if AFTER_21_1
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.caffeinemc.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.caffeinemc.mods.sodium.client.gui.options.control.SliderControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
#elif FABRIC
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
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

public class EtfMiscOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource(ETF.MOD_ID, "misc"));

    public EtfMiscOptionPage() {
        super(Component.translatable("options.sodiumoptionsmodcompat.etf.misc"), create());
        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(ID);
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                ExtendedOptionGroup.createBuilder(VersionUtils.resource(ETF.MOD_ID, "misc"))
                        .add(
                                OptionImpl.createBuilder(ETFConfig.IllegalPathMode.class, EtfOptionsStorage.INSTANCE)
                                        //.setId(VersionUtils.resource(ETF.MOD_ID, "allow_illegal_texture_paths"))
                                        .setName(Component.translatable("config.entity_texture_features.allow_illegal_texture_paths.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.allow_illegal_texture_paths.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.IllegalPathMode.class, new Component[] {
                                                Component.translatable("options.off"),
                                                Component.translatable("config.entity_texture_features.illegal_path_mode.entity"),
                                                Component.translatable("config.entity_texture_features.illegal_path_mode.all")
                                        }))
                                        .setBinding((options, value) -> options.illegalPathSupportMode = value,
                                                (options) -> options.illegalPathSupportMode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        //.setId(VersionUtils.resource(ETF.MOD_ID, "warden"))
                                        .setName(Component.translatable("config.entity_texture_features.warden.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.warden.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableFullBodyWardenTextures = value,
                                                (options) -> options.enableFullBodyWardenTextures)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        //.setId(VersionUtils.resource(ETF.MOD_ID, "warden"))
                                        .setName(Component.translatable("config.entity_features.hide_button"))
                                        .setTooltip(Component.translatable("config.entity_features.hide_button.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.hideConfigButton = value,
                                                (options) -> options.hideConfigButton)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
