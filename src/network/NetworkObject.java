package network;

import java.io.Serializable;

public class NetworkObject implements Serializable{

    private static final long serialVersionUID = 3629826798931538346L;

    private String message;

    /**
     * Constructor with the message
     * @param message
     */
    public NetworkObject(String message) {
        this.message = message;
    }

    /**
     * Constructor
     */
    public NetworkObject(){

    }

    /**
     * returns the message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * set the message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
