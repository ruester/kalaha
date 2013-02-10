public enum NetworkConnectionEnum {
  //Server:
    //Errors:
    PORT_ERROR, SERVER_START_ERROR, SERVER_STOP_ERROR,
    //all right
    SERVER_STOPPED, SERVER_STARTED,

  //Client:
    //Errors:
    SERVER_NOT_FOUND, CONNECTION_FAILED, CLIENT_STOP_ERROR,
    //all right
    CONNECTED, DISCONNECTED,

  //both
    //Errors:
    STREAM_ERROR, SEND_MESSAGE_ERROR, MESSAGE_TYPE_ERROR,
    //all right
    MESSAGE_SENDED
}
