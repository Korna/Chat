import javax.swing.*;

/**
 * Created by coma on 26.07.2016.
 */

public class Client extends JFrame {

    public JTextArea text_history;

    public JTextField text_port;
    public JTextField text_IP;
    public JTextField text_nickname;
    public JTextField field_send;

    public JLabel label_IP;
    private JLabel label_name;
    private JLabel label_connection;
    public JLabel label_port;
    public JLabel message;

    private JPanel panel_main;
    public JPanel panel_options;

    public JButton button_port;
    public JButton button_IP;
    public JButton button_send;

    public JCheckBox check_options;

    public JRadioButton flag_connection;


    public Client(){
        super("Client V2.");
        setContentPane(panel_main);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);

    }



}
