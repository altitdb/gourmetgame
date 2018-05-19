package br.com.objective.gourmetgame;

import java.lang.reflect.Field;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JOptionPane.class)
public class GourmetGameTest {

    private GourmetGame game;

    @Before
    public void setup() {
        game = new GourmetGame("massa");
    }

    public void shouldReturnValue() {
        String actual = game.getValue();
        Assert.assertEquals("massa", actual);
    }

    @Test
    public void shouldAskAndAcceptPositiveResponse() throws Exception {
        PowerMock.mockStatic(JOptionPane.class);
        String question = "O prato que você pensou é massa?";
        EasyMock.expect(JOptionPane.showConfirmDialog(null, question, "Question", JOptionPane.YES_NO_OPTION))
                .andReturn(JOptionPane.YES_OPTION);
        PowerMock.replayAll(JOptionPane.class);
        
        Field field = MemberModifier.field(GourmetGame.class, "positiveResponse");
        field.set(game, new GameStub());

        game.ask();
    }
    
    @Test
    public void shouldAskAndAcceptNegativeResponse() throws Exception {
        PowerMock.mockStatic(JOptionPane.class);
        String question = "O prato que você pensou é massa?";
        EasyMock.expect(JOptionPane.showConfirmDialog(null, question, "Question", JOptionPane.YES_NO_OPTION))
                .andReturn(JOptionPane.NO_OPTION);
        PowerMock.replayAll(JOptionPane.class);
        
        Field field = MemberModifier.field(GourmetGame.class, "negativeResponse");
        field.set(game, new GameStub());

        game.ask();
    }

}
