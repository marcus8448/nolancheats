package io.github.marcus8448.mods.nolancheats.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OpenToLanScreen.class)
@Environment(EnvType.CLIENT)
public class OpenToLanScreenMixin {
    @Shadow @Final private static Text ALLOW_COMMANDS_TEXT;

    @Redirect(method = "init", at = @At(value = "NEW", target = "(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)Lnet/minecraft/client/gui/widget/ButtonWidget;"))
    private ButtonWidget createWidget(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress) {
        if (message == ALLOW_COMMANDS_TEXT) return new ButtonWidget(x, y, width, height, message, button -> {});
        return new ButtonWidget(x, y, width, height, message, onPress);
    }
}
