package br.com.objective.gourmetgame;

import javax.swing.JOptionPane;

public class NullGame implements Game {

    @Override
    public void ask() {
        throw new UnsupportedOperationException("This method cannot be called");
    }

    @Override
    public void addOption(String a, String b) {
        throw new UnsupportedOperationException("This method cannot be called");
    }

    @Override
    public String getValue() {
        return "unknown value";
    }

    @Override
    public void confirmPositiveResponse() {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
    }

    @Override
    public void confirmNegativeResponse(Game game) {
        String dish = JOptionPane.showInputDialog("Qual prato você pensou?");
        String compare = String.format("%s é ______ mas %s não.", dish, game.getValue());
        String difference = JOptionPane.showInputDialog(compare);
        game.addOption(dish, difference);
    }

}
