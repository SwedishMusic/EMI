package dev.hared.emi.api;

public interface EMITextBox {

    public String getText();

    public void setText(String text);

    public void onKey(int keyCode, int scanCode, int modifiers);

    public void onMouse(double mouseX, double mouseY, int button);

    public void drawBox(EMIMatrix matrices, int mouseX, int mouseY, float delta);

    public void setX(int x);

    public void setY(int y);

    public void setWidth(int width);

}
