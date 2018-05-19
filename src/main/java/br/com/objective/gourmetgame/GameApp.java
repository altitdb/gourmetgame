package br.com.objective.gourmetgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameApp {

    private static final Logger LOG = LogManager.getLogger(GameApp.class);

    public static void main(String[] args) {
        GameApp game = new GameApp();
        game.start();
    }

    private void start() {
        LOG.debug("Setting up game...");
        GourmetGame game = new GourmetGame("massa");
        game.configureQuestions("Lazanha", "Bolo de Chocolate");
        LOG.debug("Configured {}", game);

        setupLayout(game);
    }

    private void setupLayout(GourmetGame game) {
        LOG.debug("Setting up layout...");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            LOG.error("Erro ao iniciarlizar Look And Feel.", ex);
            JOptionPane.showMessageDialog(null,
                    "Não foi possível inicializar corretamente a interface do jogo. Contate o suporte.");
        }

        JFrame screen = new JFrame("Jogo Gourmet");
        JLabel thinkLabel = new JLabel("Pense em um prato que gosta");
        JButton okButton = new JButton("OK");

        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLayout(null);
        screen.setSize(300, 110);
        screen.setLocationRelativeTo(null);
        screen.add(thinkLabel);
        thinkLabel.setBounds(55, 0, 250, 50);
        screen.add(okButton);
        okButton.setBounds(85, 40, 80, 20);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.ask();
            }
        });
        LOG.debug("Layout Configured.");
    }

}
