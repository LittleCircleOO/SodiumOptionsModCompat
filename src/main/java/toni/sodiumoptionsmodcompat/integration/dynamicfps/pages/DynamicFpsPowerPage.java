package toni.sodiumoptionsmodcompat.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.PowerState;
import dynamic_fps.impl.config.Config;
import dynamic_fps.impl.config.option.GraphicsState;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;

import toni.lib.utils.VersionUtils;
import toni.sodiumoptionsapi.api.ExtendedOptionGroup;
import toni.sodiumoptionsapi.api.OptionIdentifier;
import toni.sodiumoptionsapi.util.IOptionGroupIdAccessor;
import toni.sodiumoptionsmodcompat.integration.dynamicfps.DynamicFpsPowerStorage;
import toni.sodiumoptionsmodcompat.mixin.DynamicFPSConfigAccessor;


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

public class DynamicFpsPowerPage extends OptionPage {

    public DynamicFpsPowerPage(String powerStateName) {
        super(Component.translatable("config.dynamic_fps.category." + PowerState.valueOf(powerStateName).name().toLowerCase()),
            create(PowerState.valueOf(powerStateName).name().toLowerCase(), new DynamicFpsPowerStorage(DynamicFPSConfigAccessor.getConfig())));

        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(OptionIdentifier.create(VersionUtils.resource(Constants.MOD_ID, PowerState.valueOf(powerStateName).name().toLowerCase())));
    }

    private static ImmutableList<OptionGroup> create(String powerState, DynamicFpsPowerStorage storage) {
        final List<OptionGroup> groups = new ArrayList<>();


        groups.add(
                ExtendedOptionGroup.createBuilder(VersionUtils.resource(Constants.MOD_ID, powerState + "_fps"))
                        .add(
                                OptionImpl.createBuilder(int.class, storage)
                                        .setName(Component.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setControl(option -> new SliderControl(option, 0, 61, 1, FrameRateControlValueFormatter.INSTANCE))
                                        .setBinding(Config::setFrameRateTarget,
                                                Config::frameRateTarget)
                                        .build()
                        )
                        .build()
        );

        OptionGroup.Builder volume = ExtendedOptionGroup.createBuilder(VersionUtils.resource(Constants.MOD_ID, powerState + "_sound"));
        for (SoundSource source : SoundSource.values()) {
            String name = source.getName();

            volume.add(
                    OptionImpl.createBuilder(int.class, storage)
                            //.setId(VersionUtils.resource(Constants.MOD_ID, powerState + "_volume_" + name))
                            .setName(Component.translatable("soundCategory." + name))
                            .setTooltip(Component.translatable("soundCategory." + name))
                            .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                            .setBinding((option, value) -> option.setVolumeMultiplier(source, value / 100f),
                                    option -> (int) (option.volumeMultiplier(source) * 100))
                            .build()
            );
        }
        groups.add(volume.build());

        groups.add(
                ExtendedOptionGroup.createBuilder(VersionUtils.resource(Constants.MOD_ID, powerState + "_options"))
                        .add(
                                OptionImpl.createBuilder(GraphicsState.class, storage)
                                        //.setId(VersionUtils.resource(Constants.MOD_ID, powerState + "_graphics_option"))
                                        .setName(Component.translatable("config.dynamic_fps.graphics_state"))
                                        .setTooltip(
                                                Component.translatable("config.dynamic_fps.graphics_state_minimal_tooltip")
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, GraphicsState.class, new Component[]{
                                                Component.translatable("config.dynamic_fps.graphics_state_default"),
                                                Component.translatable("config.dynamic_fps.graphics_state_reduced"),
                                                Component.translatable("config.dynamic_fps.graphics_state_minimal")
                                        }))
                                        .setBinding(Config::setGraphicsState, Config::graphicsState)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        //.setId(VersionUtils.resource(Constants.MOD_ID, powerState + "_show_toasts"))
                                        .setName(Component.translatable("config.dynamic_fps.show_toasts"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.show_toasts_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(Config::setShowToasts, Config::showToasts)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        //.setId(VersionUtils.resource(Constants.MOD_ID, powerState + "_invoke_garbage_collector"))
                                        .setName(Component.translatable("config.dynamic_fps.run_garbage_collector"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.run_garbage_collector_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(Config::setRunGarbageCollector, Config::runGarbageCollector)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

    private static class FrameRateControlValueFormatter implements ControlValueFormatter {

        private final static FrameRateControlValueFormatter INSTANCE = new FrameRateControlValueFormatter();

        @Override
        public Component format(int value) {
            return value > 60 ? Component.translatable("options.framerateLimit.max") : Component.translatable("options.framerate", value);
        }

    }

}
