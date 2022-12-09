package dev.hared.emi.api;

public interface IEMIListener {

    void render(EMIMatrix matrices, int mouseX, int mouseY, float delta);

    void keyPressed(int keyCode, int scanCode, int modifiers);

    boolean charInput(char chr, int modifiers);

    void mouseClicked(double mouseX, double mouseY, int button);

    void getAPI(EMIGuiAPI api);

}
