import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client {

    Socket as = null;

    private void executer(String[] args) {
        Utils utils = new Utils();
        List<String> fileLines = null;
        try {
            fileLines = utils.getFilelines(args[0]);
        } catch (Exception e) {
            System.out.println("Bad parameter");
            System.exit(-1);
        }

        try {
            as = new Socket(InetAddress.getLocalHost(), 4000);
            System.out.println("[Client]: Connexion établie");

            while (true) {
                DataOutputStream out = new DataOutputStream(as.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
                BufferedReader line = new BufferedReader(new InputStreamReader(System.in));

                // On envoie au serveur ligne par ligne
                for (String s : fileLines) {
                    out.writeBytes(s);
                    out.writeBytes("\n");
                }
                out.writeBytes("::END::\n");
                System.out.println("[Client] : Message envoyé");

                String response = in.readLine();
                if (response != null) {
                    System.out.println("[Client] : Message reçu : \n" + response);
                } else {
                    System.out.println("Message null");
                    as.close();
                    return;
                }

            }
        } catch (IOException ex) {
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        Client c = new Client();
        c.executer(args);
    }

}
