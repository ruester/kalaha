import java.io.Serializable;

public class NetworkObject implements Serializable{
	private static final long serialVersionUID = 3629826798931538346L;
	private String message;

	public NetworkObject(String message) {
		this.message = message;
	}

	public NetworkObject() { }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
