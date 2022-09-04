package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.IKeyboardInput;
import dev.hared.emi.api.IMouseInput;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Mixin(HandledScreen.class)
public abstract class InventoryInjector implements EMIGuiAPI {

    @Shadow
    protected int x;
    @Shadow
    protected int y;

    @Override
    public <T extends HandledScreen> T getGuiObj() {
        return (T)((Object)this);
    }

    @Override @Accessor("backgroundWidth")
    public abstract int getInventoryX();

    @Override @Accessor("backgroundHeight")
    public abstract int getInventoryY();

    @Override
    public int getX(){
        return 0 - this.x;
    }

    @Override
    public int getY(){
        return 0 - this.y;
    }

    @Override
    public void getRenderTooltip(MatrixStack matrices, ItemStack stack, int x, int y) {
        ((ScreenInjector)((Object)this)).getScreenRenderTooltip(matrices, stack, x, y);
    }

    @Override @Invoker("drawItem")
    public abstract void getDrawItem(ItemStack stack, int x, int y, String amountText);

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawForeground(Lnet/minecraft/client/util/math/MatrixStack;II)V"))
    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info){
        ir.forEach(iEmiRender -> iEmiRender.render(matrices, mouseX, mouseY, delta));
    }

    @Inject(method = "keyPressed", at = @At("HEAD"))
    public boolean getKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable info) {
        ki.forEach(iKeyboardInput -> iKeyboardInput.keyPressed(keyCode, scanCode, modifiers));
        return false;
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    public boolean getMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable info) {
        mi.forEach(iMouseInput -> iMouseInput.mouseClicked(mouseX, mouseY, button));
        return false;
    }


}
