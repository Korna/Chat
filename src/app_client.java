import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by coma on 26.07.2016.
 */
public class app_client {

    static public int serverPort = 666;
    static public String address = "192.168.56.1";//192.168.1.34
    static public String History = "";
    static public String nickname = "Nickname";
    static public String line = "";
    static public InetAddress ipAddress;

    public static void main(String[] args) {
        Client myclient = new Client();




        InputStream sin;
        OutputStream sout;

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        DataInputStream in;
        DataOutputStream out;



        try {

            ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            myclient.flag_connection.setSelected(true);
            myclient.label_connection.setText("Connection established.");
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            myclient.message.setText("Type in something and press send.");


            myclient.button_send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == myclient.button_send) {

                        try {
                            line = nickname + myclient.field_send.getText(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                            myclient.message.setText("Sending this message to the server...");
                            out.writeUTF(line); // отсылаем введенную строку текста серверу.
                            out.flush(); // заставляем поток закончить передачу данных.

                            History += "\n" + line;
                            myclient.text_history.setText("\n" + History);

                            myclient.message.setText("Message sent. Waiting for response.");
                            line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                            History += "\nServer: " + line;
                            myclient.text_history.setText(History);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });


        } catch (Exception x) {
            x.printStackTrace();
        }


        myclient.button_port.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == myclient.button_port)
                    try {
                        serverPort = Integer.parseInt(myclient.text_port.getText());
                        myclient.label_port.setEnabled(true);
                        myclient.label_port.setText(myclient.text_port.getText());
                    }catch (Exception global_exception){
                        JOptionPane.showMessageDialog(null, "Error while getting new port."); }

            }
        });



        myclient.check_options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == myclient.check_options) {
                    if (myclient.check_options.isSelected() == true) {
                        myclient.panel_options.setVisible(true);
                    }
                    myclient.panel_options.setVisible(false);
                }
            }
        });
        myclient.button_IP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == myclient.button_IP)
                    try {
                        address = myclient.text_IP.getText();
                        myclient.label_IP.setEnabled(true);
                        myclient.label_IP.setText(myclient.text_IP.getText());
                        ipAddress = InetAddress.getByName(address);
                    }catch (Exception global_exception){JOptionPane.showMessageDialog(null, "Error while getting new IP."); }

            }
        });
        myclient.text_nickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nickname = myclient.text_nickname.getText() + ": ";
                JOptionPane.showMessageDialog(null, "Nickname changed.");

            }
        });

        myclient.text_nickname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                nickname = myclient.text_nickname.getText() + ": ";
            }
        });





    }



}
