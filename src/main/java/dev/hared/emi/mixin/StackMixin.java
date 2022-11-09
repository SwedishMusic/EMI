package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public class StackMixin implements EMIStack {
}
