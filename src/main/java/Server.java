import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    ServerSocket ps = null;
    ThreadCpt exec;

    void executer(String[] args) {

        try {
            exec = new ThreadCpt();
            ps = new ServerSocket(4000);

            while (true) {
                Socket as = ps.accept();
                System.out.println("Connexion établie avec " + as);

                BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
                DataOutputStream out = new DataOutputStream(as.getOutputStream());
                String msg;
                List<String> allLines = new ArrayList<>();

                // On recupere ligne par ligne
                while (!(msg = in.readLine()).equals("::END::")) {
                    allLines.add(msg);
                }

                try {
                    exec.nbThread = Integer.parseInt(args[0]);
                } catch (Exception e) {
                    System.out.println("Bad parameter");
                    System.exit(-1);
                }
                // On execute le Thread compteur
                exec.fileLines = new ArrayList<>(allLines);
                String ret = exec.execMultiThread();

                out.writeBytes(ret + "\n");
                System.out.println("[Server] Réponse envoyée : " + as);

            }
        } catch (IOException ex) {
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.executer(args);
    }
}
