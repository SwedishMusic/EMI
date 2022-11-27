package dev.hared.emi.api;

public interface EMIGuiComponent {

    public void onKey(int keyCode, int scanCode, int modifiers);

    public void onMouse(double mouseX, double mouseY, int button);

    public void draw(EMIMatrix matrices, int mouseX, int mouseY, float delta);

}
