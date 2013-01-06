package network;


public interface ClientInterface {

    /**
     * create a connection to a server
     * @param ip - ip of the server
     * @param port - port of the server
     * @return CONNECTED if successful
     */
    public NetworkConnectionEnum connect(String ip, int port);
    /**
     * create a connection to a server
     * ip of the server is Localhost
     * port of the server is 1234
     * @return CONNECTED if successful
     */
    public NetworkConnectionEnum connect();

    /**
     * disconnect from the server
     * @return DISCONNECTED if successful
     */
    public NetworkConnectionEnum disconnect();

    /**
     * listening on the port and waits for a NetworkObject
     * @return the received NetworkObject or null
     */
    public NetworkObject waitForMessage();

    /**
     * send a NetworkObject to the server
     * @param message - NetworkObject to send
     * @return MESSAGE_SENDED if successful
     */
    public NetworkConnectionEnum sendMessage(NetworkObject message);
}
