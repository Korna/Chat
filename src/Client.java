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
    public JLabel label_connection;
    public JLabel label_port;
    public JLabel message;
    public JPanel panel_options;
    public JButton button_port;
    public JButton button_IP;
    public JButton button_send;
    public JCheckBox check_options;
    public JRadioButton flag_connection;
    private JLabel label_name;
    private JPanel panel_main;
    private JScrollPane scroll_history;


    public Client(){
        super("Client.");
        setContentPane(panel_main);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        scroll_history.setViewportView(text_history);

        //инициализация интерфейса
        label_port.setText(Integer.toString(app_client.serverPort));
        label_IP.setText(app_client.address);
    }



}
