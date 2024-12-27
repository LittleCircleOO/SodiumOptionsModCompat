package toni.sodiumoptionsmodcompat.integration.chunksfadein;

import com.google.common.collect.ImmutableList;

import com.koteinik.chunksfadein.config.Config;
#if AFTER_21_1
import com.koteinik.chunksfadein.core.Curve;
import com.koteinik.chunksfadein.core.FadeType;
#elif FABRIC
import com.koteinik.chunksfadein.core.Curves;
import com.koteinik.chunksfadein.core.FadeTypes;
#endif

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import toni.lib.utils.PlatformUtils;
import toni.lib.utils.VersionUtils;
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
#endif

import java.util.ArrayList;
import java.util.List;

public class ChunksFadeInOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(VersionUtils.resource("chunksfadein", "general"));

    public ChunksFadeInOptionPage() {
        super(Component.translatable("options.embyui.chunksfadein.animations"), create());
        ((IOptionGroupIdAccessor)this).sodiumOptionsAPI$setId(ID);
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "enabled"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.mod_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.mod_enabled"))
                                        .setEnabled(() -> !(Minecraft.getInstance().isSingleplayer() || Minecraft.getInstance().getSingleplayerServer() != null))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.MOD_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.MOD_ENABLED_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "update_notifier"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.update_notifier_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.update_notifier_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.UPDATE_NOTIFIER_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.UPDATE_NOTIFIER_ENABLED_KEY))
                                        .build()
                        )
                #if AFTER_21_1
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "mod_button_in_settings"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.mod_tab_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.mod_tab_enabled"))
                                        .setEnabled(() -> PlatformUtils.isModLoaded("modmenu"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.SHOW_MOD_TAB_IN_SETTINGS_KEY, value),
                                                (options) -> Config.getBoolean(Config.SHOW_MOD_TAB_IN_SETTINGS_KEY))
                                        .build()
                        )
                #elif FABRIC
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "mod_button_in_settings"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.mod_button_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.mod_button_enabled"))
                                        .setEnabled(() -> PlatformUtils.isModLoaded("modmenu"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.SHOW_MOD_BUTTON_IN_SETTINGS_KEY, value),
                                                (options) -> Config.getBoolean(Config.SHOW_MOD_BUTTON_IN_SETTINGS_KEY))
                                        .build()
                        )
                #endif
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()

                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "fade_enabled"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.fade_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.fade_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.FADE_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.FADE_ENABLED_KEY))
                                        .build()
                        )

                #if AFTER_21_1
                        .add(
                                OptionImpl.createBuilder(FadeType.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "fade_type"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setControl((opt) -> new CyclingControl<>(opt, FadeType.class, new Component[]{
                                                Component.translatable("options.embyui.chunksfadein.fade_type.full"),
                                                Component.translatable("options.embyui.chunksfadein.fade_type.lined")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.FADE_TYPE_KEY, value.ordinal()),
                                                (options) -> FadeType.values()[Config.getInteger(Config.FADE_TYPE_KEY)])
                                        .build()
                        )
                #elif FABRIC
                        .add(
                                OptionImpl.createBuilder(FadeTypes.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "fade_type"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setControl((opt) -> new CyclingControl<>(opt, FadeTypes.class, new Component[]{
                                                Component.translatable("options.embyui.chunksfadein.fade_type.full"),
                                                Component.translatable("options.embyui.chunksfadein.fade_type.lined")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.FADE_TYPE_KEY, value.ordinal()),
                                                (options) -> FadeTypes.values()[Config.getInteger(Config.FADE_TYPE_KEY)])
                                        .build()
                        )
                #endif
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "fade_time"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.fade_time"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.fade_time"))
                                        .setControl(option -> new SliderControl(option, 0, 1000, 1, value -> Component.translatable("options.embyui.chunksfadein.time_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.FADE_TIME_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.FADE_TIME_KEY) * 100), 1000))
                                        .build()
                        )
                        .build()
        );
        groups.add(
                OptionGroup.createBuilder()

                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animation_enabled"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animation_enabled"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animation_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.ANIMATION_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.ANIMATION_ENABLED_KEY))
                                        .build()
                        )
                #if AFTER_21_1
                        .add(
                                OptionImpl.createBuilder(Curve.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animation_curve"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setControl((opt) -> new CyclingControl<>(opt, Curve.class, new Component[]{
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.linear"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.ease_out"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.ease_circular"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.bounce")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.ANIMATION_CURVE_KEY, value.ordinal()),
                                                (options) -> Curve.values()[Config.getInteger(Config.ANIMATION_CURVE_KEY)])
                                        .build()
                        )
                #elif FABRIC
                        .add(
                                OptionImpl.createBuilder(Curves.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animation_curve"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setControl((opt) -> new CyclingControl<>(opt, Curves.class, new Component[]{
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.linear"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.ease_out"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.ease_circular"),
                                                Component.translatable("options.embyui.chunksfadein.animation_curve.bounce")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.ANIMATION_CURVE_KEY, value.ordinal()),
                                                (options) -> Curves.values()[Config.getInteger(Config.ANIMATION_CURVE_KEY)])
                                        .build()
                        )
                #endif
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animation_start"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animation_start"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animation_start"))
                                        .setControl(option -> new SliderControl(option, 100, 31900, 1, value -> Component.translatable("options.embyui.chunksfadein.animation_start_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.ANIMATION_OFFSET_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.ANIMATION_OFFSET_KEY) * 100), 31900))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animate_near_player"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animate_near_player"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animate_near_player"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.ANIMATE_NEAR_PLAYER_KEY, value),
                                                (options) -> Config.getBoolean(Config.ANIMATE_NEAR_PLAYER_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        //.setId(new Identifier("chunksfadein", "animation_time"))
                                        .setName(Component.translatable("options.embyui.chunksfadein.animation_time"))
                                        .setTooltip(Component.translatable("options.embyui.chunksfadein.animation_time"))
                                        .setControl(option -> new SliderControl(option, 0, 1000, 1, value -> Component.translatable("options.embyui.chunksfadein.time_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.ANIMATION_TIME_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.ANIMATION_TIME_KEY) * 100), 1000))
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}