import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
/**
 * Created by coma on 25.07.2016.
 */
public class App extends JFrame{
    private JTextField field_send;
    private JButton button_send;
    private JLabel label_name;
    private JPanel panel_main;


    private JButton button_port;
    private JTextField text_port;
    private JLabel label_port;
    private JTextField text_IP;
    private JButton button_IP;
    private JPanel panel_options;
    private JCheckBox check_options;
    private JLabel label_IP;
    private JLabel message;
    private JTextArea text_history;
    private JTextField text_nickname;
    private JLabel label_connection;
    private JRadioButton flag_connection;


    int serverPort = 666;
    String address = "127.0.0.1";//192.168.1.34
    String History;
    String nickname = "Nickname_1: ";
    InputStream sin;
    OutputStream sout;

    // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
    DataInputStream in;
    DataOutputStream out;

    String line = "";


    public static void main(String[] args) {
        GUI();

    }


    public App() {

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            message.setText("Your IP address " + address + " and port " + serverPort);
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            flag_connection.setSelected(true);
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            in  = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.

            message.setText("Type in something and press send.");


        } catch (Exception x) {x.printStackTrace();}



            button_send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button_send) {

                        try {
                            line = nickname + field_send.getText(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                            message.setText("Sending this message to the server...");
                            out.writeUTF(line); // отсылаем введенную строку текста серверу.
                            out.flush(); // заставляем поток закончить передачу данных.

                            History += "\n" + line;
                            text_history.setText("\n" + History);

                            message.setText("Message sent. Waiting for response.");
                            line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                            History += "\nServer: " + line;
                            text_history.setText(History);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });


        button_port.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_port)
                    try {
                        serverPort = Integer.parseInt(text_port.getText());
                        label_port.setEnabled(true);
                        label_port.setText(text_port.getText());
                    }catch (Exception global_exception){JOptionPane.showMessageDialog(null, "Error while getting new port."); }

            }
        });



        check_options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == check_options) {
                    if (check_options.isSelected() == true) {
                        panel_options.setVisible(true);
                    }
                    panel_options.setVisible(false);
                }
            }
        });
        button_IP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_IP)
                    try {
                        address = text_IP.getText();
                        label_IP.setEnabled(true);
                        label_IP.setText(text_IP.getText());
                    }catch (Exception global_exception){JOptionPane.showMessageDialog(null, "Error while getting new IP."); }

            }
        });
        text_nickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname = text_nickname.getText() + ": ";
                JOptionPane.showMessageDialog(null, "Nickname changed.");
            }
        });

        text_nickname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                nickname = text_nickname.getText() + ": ";
            }
        });
    }



    public static void GUI(){
        JFrame frame = new JFrame("Chat client.");
        frame.setContentPane(new App().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        //frame.setSize(500, 300);
    }




}
