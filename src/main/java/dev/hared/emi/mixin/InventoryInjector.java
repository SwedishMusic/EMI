package dev.hared.emi.mixin;

import dev.hared.emi.EMI;
import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;
import dev.hared.emi.api.EMITextBox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(HandledScreen.class)
public abstract class InventoryInjector implements EMIGuiAPI {

    @Shadow
    protected int x;
    @Shadow
    protected int y;

    /*@Override
    public <T extends HandledScreen> T getGuiObj() {
        return (T)((Object)this);
    }*/

    @Override @Accessor("backgroundWidth")
    public abstract int getInventoryX();

    @Override @Accessor("backgroundHeight")
    public abstract int getInventoryY();

    @Override
    public int getWidth(){
        return this.getObj().width;
    }

    @Override
    public int getHeight(){
        return this.getObj().height;
    }

    @Override
    public int getX(){
        return 0 - this.x;
    }

    @Override
    public int getY(){
        return 0 - this.y;
    }

    public HandledScreen getObj(){
        return (HandledScreen)(Object)this;
    }

    @Override
    public void drawSquare(EMIMatrix matrices, int x1, int y1, int x2, int y2, int color) {
        DrawableHelper.fill(this.getMatrix(matrices), x1, y1, x2, y2, color);
    }

    public MatrixStack getMatrix(EMIMatrix matrix){
        return (MatrixStack)(Object)matrix;
    }

    public ItemStack getStack(EMIStack stack){
        return (ItemStack)(Object)stack;
    }

    @Override
    public void drawSlotHighlight(EMIMatrix matrices, int x, int y) {
        HandledScreen.drawSlotHighlight(this.getMatrix(matrices), x, y, this.getObj().getZOffset());
    }

    @Override
    public void getRenderTooltip(EMIMatrix matrices, EMIStack stack, int x, int y) {
        ((ScreenInjector)((Object)this)).getScreenRenderTooltipStack(this.getMatrix(matrices), this.getStack(stack), x, y);
    }

    @Override
    public EMITextBox createNewTextBox(int x, int y, int width, int height) {
        return (EMITextBox)(Object)new TextFieldWidget(MinecraftClient.getInstance().textRenderer, x, y, width, height, Text.literal(""));
    }

    @Override
    public void getRenderTooltipString(EMIMatrix matrices, List<String> tip, int x, int y) {
        ArrayList<Text> text = new ArrayList<Text>();
        for(String string : tip){
            text.add(Text.literal(string));
        }
        this.getObj().renderTooltip(this.getMatrix(matrices), text, x, y);
    }

    @Override
    public void getDrawItem(EMIStack stack, int x, int y, String amountText) {
        this.intDrawItem(this.getStack(stack), x, y, amountText);
    }

    @Invoker("drawItem")
    public abstract void intDrawItem(ItemStack stack, int x, int y, String amountText);

    @Override
    public int getTextWidth(String text) {
        return MinecraftClient.getInstance().textRenderer.getWidth(text);
    }

    @Override
    public int getTextHeight() {
        return MinecraftClient.getInstance().textRenderer.fontHeight;
    }

    @Override
    public EMIStack getItem(String id) {
        return EMI.getInstance().getItem(id);
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawForeground(Lnet/minecraft/client/util/math/MatrixStack;II)V"))
    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info){
        listener.forEach(listener -> {
            listener.getAPI(this);
            listener.render((EMIMatrix)(Object)matrices, mouseX + this.getX(), mouseY + this.getY()
                    , delta);
        });
    }

    @Inject(method = "keyPressed", at = @At("HEAD"))
    public boolean getKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable info) {
        listener.forEach(listener -> listener.keyPressed(keyCode, scanCode, modifiers));
        return false;
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    public boolean getMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable info) {
        listener.forEach(listener -> listener.mouseClicked(mouseX, mouseY, button));
        return false;
    }

    @Override
    public EMIStack[] getAllItems() {
        return EMI.getInstance().EMIItemList();
    }
}
