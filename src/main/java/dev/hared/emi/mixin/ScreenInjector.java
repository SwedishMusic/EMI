package dev.hared.emi.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(Screen.class)
public interface ScreenInjector {

    @Invoker("renderTooltipFromComponents")
    public void getScreenRenderTooltip(MatrixStack matrices, List<TooltipComponent> components, int x, int y);

    @Invoker("renderTooltip")
    public void getScreenRenderTooltipStack(MatrixStack matrices, ItemStack stack, int x, int y);

}
