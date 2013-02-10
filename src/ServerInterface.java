public interface ServerInterface {

	/**
	 * start a server
	 * return values:
	 * SERVER_START_ERROR, STREAM_ERROR or SERVER_STARTED
	 */
	public NetworkConnectionEnum start(int port);

	/**
	 * same as start(1234)
	 */
	public NetworkConnectionEnum start();

	/**
	 * restart the server
	 * return values same as start()
	 */
	public NetworkConnectionEnum restart();

	/**
	 * stop the server
	 * return values:
	 * SERVER_STOP_ERROR or SERVER_STOPPED
	 */
	public NetworkConnectionEnum stop();

	/**
	 * listening on the port and wait for a NetworkObject
	 * not null: the received NetworkObject
	 * null:     object could not be received
	 */
	public NetworkObject waitForMessage();

	/**
	 * send a NetworkObject to the client
	 * return values:
	 * SEND_MESSAGE_ERROR or MESSAGE_SENDED
	 */
	public NetworkConnectionEnum sendMessage(NetworkObject message);
}
