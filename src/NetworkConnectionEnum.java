public enum NetworkConnectionEnum {
	// server
	PORT_ERROR, SERVER_START_ERROR, SERVER_STOP_ERROR,

	SERVER_STOPPED, // server stopped successfully
	SERVER_STARTED, // server started successfully

	// client
	SERVER_NOT_FOUND, CONNECTION_FAILED, CLIENT_STOP_ERROR,

	CONNECTED,    // client connected successfully
	DISCONNECTED, // client disconnected successfully

	// both
	STREAM_ERROR, SEND_MESSAGE_ERROR, MESSAGE_TYPE_ERROR,

	MESSAGE_SENDED // message sent successfully
}
