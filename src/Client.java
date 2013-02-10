/*
    Client - Client.java

    Copyright (C) 2013 Lucas Schwass
    Copyright (C) 2013 Matthias Ruester

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientInterface{
	private int port;
	private String serverIP;
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Client() {
		port = 1234;
		serverIP = "localhost";
	}

	private void initStreams() throws IOException{
		out = new ObjectOutputStream(s.getOutputStream());
		in  = new ObjectInputStream(s.getInputStream());
		out.flush();
	}

	public NetworkConnectionEnum connect(String ip, int port) {
		serverIP  = ip;
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
			no = (NetworkObject) in.readObject();
		} catch (Exception e) { }

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
