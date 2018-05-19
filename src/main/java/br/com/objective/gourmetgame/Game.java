package br.com.objective.gourmetgame;

public interface Game {

    void confirmPositiveResponse();
    
    void confirmNegativeResponse(Game game);
    
    void ask();
    
    void addOption(String a, String b);

    String getValue();

}
