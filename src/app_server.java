import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by coma on 26.07.2016.
 */
public class app_server {

    static public String message_in = null;
    static public String message_ou = null;
    static public String History = "";

    public static void main(String[] args) {
        Server myserver = new Server();


        try {
            int port = 666;
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту

            History += "Waiting for a client...\n";
            myserver.text_history.setText(History);
            // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            Socket socket = ss.accept();
            History += "Connection established.\n";
            myserver.text_history.setText(History);

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);


            while(true) {
                message_in = in.readUTF(); // ожидаем пока клиент пришлет строку текста.

                History += message_in + "\n";
                myserver.text_history.setText(History);



                myserver.button_send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {

                            message_ou = myserver.text_send.getText();
                            History += "Server: " + message_ou + "\n";


                            out.writeUTF(/*app_server.*/message_ou);
                            out.flush(); // заставляем поток закончить передачу данных.
                            myserver.text_history.setText(History);
                        } catch(Exception ex){};

                    }
                });
        /*
                out.writeUTF(message_ou); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                History += "Server: " + message_ou + "\n";
                myserver.text_history.setText(History);
                */
            }
        } catch(Exception x) { x.printStackTrace(); }

    }


    public app_server(){




    }


}
