import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientInterface{

    private int port = 1234;
    private String serverIP = "Localhost";
    private Socket s = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    /**
     * initializes the streams
     * @throws IOException
     */
    private void initStreams() throws IOException{
        out = new ObjectOutputStream(s.getOutputStream());
        out.flush();
        in = new ObjectInputStream(s.getInputStream());
    }

    public NetworkConnectionEnum connect(String ip, int port) {
        serverIP = ip;
        this.port = port;
        return connect();
    }

    public NetworkConnectionEnum connect() {
        try {
            s = new Socket(serverIP, port);
        } catch (UnknownHostException e) {
            return NetworkConnectionEnum.SERVER_NOT_FOUND;
        } catch (IOException e) {
            return NetworkConnectionEnum.CONNECTION_FAILED;
        }

        try {
            initStreams();
        } catch (IOException e) {
            return NetworkConnectionEnum.STREAM_ERROR;
        }

        return NetworkConnectionEnum.CONNECTED;
    }

    public NetworkConnectionEnum disconnect() {
        try {
            s.close();
        } catch (IOException e) {
            return NetworkConnectionEnum.CLIENT_STOP_ERROR;
        }
        return NetworkConnectionEnum.DISCONNECTED;
    }

    public NetworkConnectionEnum reconnect() {
        disconnect();
        return connect();
    }

    public NetworkObject waitForMessage(){
        NetworkObject no = null;
        try {
            no = (NetworkObject)in.readObject();
        } catch (IOException e) {
            //do nothing
        } catch (ClassNotFoundException e) {
            //do nothing
        }
        return no;
    }

    public NetworkConnectionEnum sendMessage(NetworkObject message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            return NetworkConnectionEnum.SEND_MESSAGE_ERROR;
        }
        return NetworkConnectionEnum.MESSAGE_SENDED;
    }
}
