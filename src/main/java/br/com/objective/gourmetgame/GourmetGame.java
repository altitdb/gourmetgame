package br.com.objective.gourmetgame;

import javax.swing.JOptionPane;

public class GourmetGame implements Game {

    private String value;
    private Game positiveResponse = new NullGame();
    private Game negativeResponse = new NullGame();
    
    public GourmetGame(String value) {
        this.value = value;
    }

    public void configureQuestions(String positiveResponse, String negativeResponse) {
        this.positiveResponse = new GourmetGame(positiveResponse);
        this.negativeResponse = new GourmetGame(negativeResponse);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void ask() {
        String question = String.format("O prato que você pensou é %s?", this.value);
        int option = JOptionPane.showConfirmDialog(null, question, "Question", JOptionPane.YES_NO_OPTION);

        switch (option) {
        case JOptionPane.OK_OPTION:
            positiveResponse.confirmPositiveResponse();
            break;
        default:
            negativeResponse.confirmNegativeResponse(this);
            break;
        }
    }
    
    @Override
    public void addOption(String option, String value) {
        this.negativeResponse = new GourmetGame(this.value);
        this.positiveResponse = new GourmetGame(option);
        this.value = value;
    }

    @Override
    public void confirmPositiveResponse() {
        this.ask();
    }

    @Override
    public void confirmNegativeResponse(Game game) {
        this.ask();        
    }
    
    @Override
    public String toString() {
        return "GourmetGame [value=" + value + ", positiveResponse=" + positiveResponse + ", negativeResponse="
                + negativeResponse + "]";
    }
}
