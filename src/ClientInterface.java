public interface ClientInterface {
	/**
	 * create a connection to a server
	 * return values:
	 * SERVER_NOT_FOUND, CONNECTION_FAILED or CONNECTED
	 */
	public NetworkConnectionEnum connect(String ip, int port);

	/**
	 * same as connect("localhost", 1234)
	 */
	public NetworkConnectionEnum connect();

	/**
	 * reconnect to server
	 * return values same as connect()
	 */
	public NetworkConnectionEnum reconnect();

	/**
	 * disconnect from the server
	 * return values:
	 * CLIENT_STOP_ERROR or DISCONNECTED
	 */
	public NetworkConnectionEnum disconnect();

	/**
	 * listening on the port and wait for a NetworkObject
	 * return values:
	 * not null: the received NetworkObject
	 * null:     object could not be received
	 */
	public NetworkObject waitForMessage();

	/**
	 * send a NetworkObject to the server
	 * return values:
	 * SEND_MESSAGE_ERROR or MESSAGE_SENDED
	 */
	public NetworkConnectionEnum sendMessage(NetworkObject message);
}
