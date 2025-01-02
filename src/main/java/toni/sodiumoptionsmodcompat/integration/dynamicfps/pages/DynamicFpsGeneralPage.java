package toni.sodiumoptionsmodcompat.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.config.DynamicFPSConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import toni.sodiumoptionsmodcompat.utils.VersionUtils;
import toni.sodiumoptionsapi.api.ExtendedOptionGroup;
import toni.sodiumoptionsapi.api.OptionIdentifier;
import toni.sodiumoptionsapi.util.IOptionGroupIdAccessor;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.DynamicFpsGeneralStorage;

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

import java.util.ArrayList;
import java.util.List;

public class DynamicFpsGeneralPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource(Constants.MOD_ID, "general"));

    public DynamicFpsGeneralPage() {
        //super(ID, Component.translatable("config.dynamic_fps.category.general"), create());
        super(Component.translatable("config.dynamic_fps.category.general"), create());
        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(ID);
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                ExtendedOptionGroup.createBuilder(VersionUtils.resource(Constants.MOD_ID, "enabled_section"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        //.setId(VersionUtils.resource(Constants.MOD_ID, "enabled"))
                                        .setName(Component.translatable("config.dynamic_fps.enabled"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(DynamicFPSConfig::setEnabled,
                                                DynamicFPSConfig::enabled)
                                        .build()
                        )
                        .build()
        );
        groups.add(
                ExtendedOptionGroup.createBuilder(VersionUtils.resource(Constants.MOD_ID, "misc"))
//                        .add(
//                                OptionImpl.createBuilder(int.class, DynamicFpsGeneralStorage.INSTANCE)
//                                        //.setId(VersionUtils.resource(Constants.MOD_ID, "idle_timeout"))
//                                        .setName(Component.translatable("config.dynamic_fps.idle_time"))
//                                        .setTooltip(Component.translatable("config.dynamic_fps.idle_time_tooltip"))
//                                        .setControl(option -> new SliderControl(option, 0, 30, 1, IdleControlValueFormatter.INSTANCE))
//                                        .setBinding(DynamicFPSConfig::setIdleTime,
//                                                DynamicFPSConfig::idleTime)
//                                        .build()
//                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        //.setId(VersionUtils.resource(Constants.MOD_ID, "uncap_menu_fps"))
                                        .setName(Component.translatable("config.dynamic_fps.uncap_menu_frame_rate"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.uncap_menu_frame_rate_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(DynamicFPSConfig::setUncapMenuFrameRate,
                                                DynamicFPSConfig::uncapMenuFrameRate)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

    private static class IdleControlValueFormatter implements ControlValueFormatter {

        private final static IdleControlValueFormatter INSTANCE = new IdleControlValueFormatter();

        @Override
        public Component format(int value) {
            return value <= 0 ? Component.translatable("config.dynamic_fps.disabled") : Component.translatable("config.dynamic_fps.minutes", value);
        }

    }

}
