package dev.hared.emi.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Screen.class)
public interface ScreenInjector {

    @Invoker("renderTooltip")
    public void getScreenRenderTooltip(MatrixStack matrices, ItemStack stack, int x, int y);

}
