package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIMatrix;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MatrixStack.class)
public class MatrixMixin implements EMIMatrix {
}
