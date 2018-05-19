package br.com.objective.gourmetgame;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JOptionPane.class)
public class NullGameTest {

    private Game game;
    
    @Before
    public void setup() {
        game = new NullGame();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnExceptionWhenCallMethodAsk() {
        game.ask();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnExceptionWhenCallMethodAddOption() {
        game.ask();
    }
    
    @Test
    public void shouldReturnUnknownValue() {
        String actual = game.getValue();
        Assert.assertEquals("unknown value", actual);
    }
    
    @Test
    public void shouldShowSuccessMessage() {
        PowerMock.mockStatic(JOptionPane.class);
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
        PowerMock.replayAll(JOptionPane.class);
        game.confirmPositiveResponse();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void shouldAskNewDish() {
        PowerMock.mockStatic(JOptionPane.class);
        EasyMock.expect(JOptionPane.showInputDialog("Qual prato você pensou?")).andReturn("Bolo de Morango");
        EasyMock.expect(JOptionPane.showInputDialog("Bolo de Morango é ______ mas unknown value não.")).andReturn("Feito de Frutas");
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
        PowerMock.replayAll(JOptionPane.class);
        Game newGame = new NullGame();
        game.confirmNegativeResponse(newGame);
    }
}
