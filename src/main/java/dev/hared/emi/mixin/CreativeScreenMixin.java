package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.IEMIListener;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeScreenMixin extends CreativeInventoryScreen {

    @Shadow
    private TextFieldWidget searchBox;

    public CreativeScreenMixin(PlayerEntity player) {
        super(player);
    }

    @Inject(method = "charTyped", at = @At("HEAD"))
    public boolean charTyped(char chr, int modifiers, CallbackInfoReturnable info){
        EMIGuiAPI.listener.forEach(listener -> listener.charInput(chr, modifiers));
        return false;
    }

}
