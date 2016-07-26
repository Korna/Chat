import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;





public class Server extends JFrame{

    private JPanel panel_main;
    public JTextArea text_history;
    public JButton button_send;
    public JTextField text_send;
    private JButton button_reconnect;


    public Server(){
        super("Server side.");

        setContentPane(panel_main);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }

}

/*
public class Server {

    private JPanel panel_main;
    private JTextArea text_history;
    private JButton button_send;
    private JTextField text_send;
    private JButton button_reconnect;


    String History;

    String line = null;
    int port = 666;




    public void program(){

    }

    public Server() {




        try {




            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Connection estabilished");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);


            while(true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.

                History += "\n" + line;
                text_history.setText("Message " + History);

                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for a message...");
            }
        } catch(Exception x) { x.printStackTrace(); }






        button_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }

    public static void main(String[] args)    {
        GUI();

    }


    public static void GUI(){
        JFrame frame = new JFrame("Chat server.");
        frame.setContentPane(new program().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.setSize(500, 300);
    }

}

*/