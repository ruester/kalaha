import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface{

    private int port = 1234;
    private ServerSocket server = null;
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

    public NetworkConnectionEnum start(int port) {
        this.port = port;
        return start();
    }

    public NetworkConnectionEnum start() {
        try {
            server = new ServerSocket(port);
            s = server.accept();
        } catch (IOException e) {
            return NetworkConnectionEnum.SERVER_START_ERROR;
        }

        try {
            initStreams();
        } catch (IOException e) {
            return NetworkConnectionEnum.SREAM_ERROR;
        }

        return NetworkConnectionEnum.SERVER_STARTED;
    }

    public NetworkConnectionEnum stop() {
        try {
            server.close();
        } catch (IOException e) {
            return NetworkConnectionEnum.SERVER_STOP_ERROR;
        }
        return NetworkConnectionEnum.SERVER_STOPPED;
    }

    public NetworkConnectionEnum restart() {
        stop();
        return start();
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
        try{
            out.writeObject(message);
            out.flush();
        } catch(IOException ioException){
            return NetworkConnectionEnum.SEND_MESSAGE_ERROR;
        }
        return NetworkConnectionEnum.MESSAGE_SENDED;
    }
}
