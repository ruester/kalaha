public interface ServerInterface {

    /**
     * start a server
     * @param port - listening on the port
     * @return SERVER_STARTED if successful
     */
    public NetworkConnectionEnum start(int port);

    /**
     * start a server
     * port default is 1234
     * @return SERVER_STARTED if successful
     */
    public NetworkConnectionEnum start();

    /**
     * restart the server
     * @return SERVER_STARTED if successful
     */
    public NetworkConnectionEnum restart();

    /**
     * stop the server
     * @return SERVER_STOPPED if successful
     */
    public NetworkConnectionEnum stop();

    /**
     * listening on the port and waits for a NetworkObject
     * @return the received NetworkObject or null
     */
    public NetworkObject waitForMessage();

    /**
     * send a NetworkObject to the client
     * @param message - NetworkObject to send
     * @return MESSAGE_SENDED if successful
     */
    public NetworkConnectionEnum sendMessage(NetworkObject message);
}
