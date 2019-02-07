package clientsocket;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StrikeBall {
    public JFrame frame;
    private JPanel rootPanel;
    private JButton inviaButton;
    private JTextField textField1;
    private JButton a9Button;
    private JButton a0Button;
    private JButton a8Button;
    private JButton a7Button;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    public JTextArea chat;
    private JLabel labelRisposta;
    private JLabel labelTitolo;
    private JButton confermaButton;
    private JButton cancellaButton;

    public StrikeBall() {
        /*
            Per tutti i bottoni da 0 fino a 9 inserisco il numero selezionato nella label indicante la risposta e
            disabilito il bottone.
         */
        a0Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "0");
                a0Button.setEnabled(false);
            }
        });
        a1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "1");
                a1Button.setEnabled(false);
            }
        });
        a2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "2");
                a2Button.setEnabled(false);
            }
        });
        a3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "3");
                a3Button.setEnabled(false);
            }
        });
        a4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "4");
                a4Button.setEnabled(false);
            }
        });
        a5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "5");
                a5Button.setEnabled(false);
            }
        });
        a6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "6");
                a6Button.setEnabled(false);
            }
        });
        a7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "7");
                a7Button.setEnabled(false);
            }
        });
        a8Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "8");
                a8Button.setEnabled(false);
            }
        });
        a9Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText(labelRisposta.getText() + "9");
                a9Button.setEnabled(false);
            }
        });
        /**
         * Al click sul bottone invia, questo metodo passa al metodo inviaMessaggio, della classe ClientSocket, la
         * stringa inserita dall'utente e provvederà poi a svuotarla.
         */
        inviaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ClientSocket.inviaMessaggioChat(textField1.getText());
                textField1.setText("");
            }
        });
        /**
         * Il metodo controlla la lunghezza della stringa da inviare.
         * Quando la stringa raggiunge la lunghezza di 4, tutti i bottoni dei numeri vengono disabilitati
         * e rimangono disponibili solo i bottoni conferma e cancella.
         */
        labelRisposta.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if (labelRisposta.getText().length() == 4) {
                    System.out.println(labelRisposta.getText());
                    confermaButton.setEnabled(true);
                    a0Button.setEnabled(false);
                    a1Button.setEnabled(false);
                    a2Button.setEnabled(false);
                    a3Button.setEnabled(false);
                    a4Button.setEnabled(false);
                    a5Button.setEnabled(false);
                    a6Button.setEnabled(false);
                    a7Button.setEnabled(false);
                    a8Button.setEnabled(false);
                    a9Button.setEnabled(false);
                }
            }
        });
        /**
         *Al click sul bottone conferma si attiva questo evento che riabilita tutti i bottoni dei numeri
         * e confronta il numero inserito dall'utente con quello ricevuto dal server, passandolo all'oggetto gioco.
         * A questo punto la routine all'interno dell'oggetto gioco restituirà i risultati ad una dialog che restituirà
         * i risultati all'utente.
         */
        confermaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Gioco gioco = new Gioco(ClientSocket.riceviMessaggio(), labelRisposta.getText());
                //Gioco.tentativo();
                Risultati dialog = new Risultati();
                dialog.pack();
                dialog.setRisultati(Gioco.tentativo());
                dialog.setVisible(true);
                labelRisposta.setText("");
                a0Button.setEnabled(true);
                a1Button.setEnabled(true);
                a2Button.setEnabled(true);
                a3Button.setEnabled(true);
                a4Button.setEnabled(true);
                a5Button.setEnabled(true);
                a6Button.setEnabled(true);
                a7Button.setEnabled(true);
                a8Button.setEnabled(true);
                a9Button.setEnabled(true);
                confermaButton.setEnabled(false);
            }
        });
        /**
         * Questo evento si attiva nel momento in cui si clicca sul bottone cancella.
         * Il metodo riabilita tutti i bottoni, in caso siano stati disabilitati e svuota la stringa con i numeri
         * inseriti dall'utente.
         */
        cancellaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                labelRisposta.setText("");
                a0Button.setEnabled(true);
                a1Button.setEnabled(true);
                a2Button.setEnabled(true);
                a3Button.setEnabled(true);
                a4Button.setEnabled(true);
                a5Button.setEnabled(true);
                a6Button.setEnabled(true);
                a7Button.setEnabled(true);
                a8Button.setEnabled(true);
                a9Button.setEnabled(true);
                confermaButton.setEnabled(false);
            }
        });
    }

    /**
     * Questo metodo contiene le routine per la creazione dell'interfaccia grafica.
     */
    public void creaInterfaccia() {
        this.frame = new JFrame("StrikeBall");
        this.frame.setContentPane(this.rootPanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setSize(new Dimension(800, 400));
        this.frame.setLocation(400, 100);
        this.frame.setVisible(true);
        this.confermaButton.setEnabled(false);
    }

    //CODICE GENERATO AUTOMATICAMENTE DALL'IDE PER IL DEISGN DELLA GUI. NON TOCCARE!!
    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(12, 4, new Insets(0, 0, 0, 0), -1, -1));
        inviaButton = new JButton();
        inviaButton.setText("Invia");
        rootPanel.add(inviaButton, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        rootPanel.add(textField1, new GridConstraints(11, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        a9Button = new JButton();
        a9Button.setText("9");
        rootPanel.add(a9Button, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a0Button = new JButton();
        a0Button.setText("0");
        rootPanel.add(a0Button, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a8Button = new JButton();
        a8Button.setText("8");
        rootPanel.add(a8Button, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a7Button = new JButton();
        a7Button.setText("7");
        rootPanel.add(a7Button, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a6Button = new JButton();
        a6Button.setText("6");
        rootPanel.add(a6Button, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a5Button = new JButton();
        a5Button.setText("5");
        rootPanel.add(a5Button, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a4Button = new JButton();
        a4Button.setText("4");
        rootPanel.add(a4Button, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a1Button = new JButton();
        a1Button.setText("1");
        rootPanel.add(a1Button, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a2Button = new JButton();
        a2Button.setText("2");
        rootPanel.add(a2Button, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a3Button = new JButton();
        a3Button.setText("3");
        rootPanel.add(a3Button, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelTitolo = new JLabel();
        Font labelTitoloFont = this.$$$getFont$$$(null, Font.BOLD | Font.ITALIC, 22, labelTitolo.getFont());
        if (labelTitoloFont != null) labelTitolo.setFont(labelTitoloFont);
        labelTitolo.setText("StrikeBall!");
        rootPanel.add(labelTitolo, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelRisposta = new JLabel();
        Font labelRispostaFont = this.$$$getFont$$$(null, Font.BOLD, 18, labelRisposta.getFont());
        if (labelRispostaFont != null) labelRisposta.setFont(labelRispostaFont);
        labelRisposta.setText("");
        rootPanel.add(labelRisposta, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        chat = new JTextArea();
        rootPanel.add(chat, new GridConstraints(7, 0, 4, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 1, false));
        confermaButton = new JButton();
        confermaButton.setText("Conferma");
        rootPanel.add(confermaButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        cancellaButton = new JButton();
        cancellaButton.setText("Cancella");
        rootPanel.add(cancellaButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
