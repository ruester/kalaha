/*
    Server - Server.java

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
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerInterface{
	private int port;
	private ServerSocket server;
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Server() {
		port = 1234;
	}

	private void initStreams() throws IOException{
		out = new ObjectOutputStream(s.getOutputStream());
		in  = new ObjectInputStream(s.getInputStream());
		out.flush();
	}

	public NetworkConnectionEnum start(int port) {
		this.port = port;
		return start();
	}

	public NetworkConnectionEnum start() {
		try {
			server = new ServerSocket(port);
			s      = server.accept();
		} catch (IOException e) {
			return NetworkConnectionEnum.SERVER_START_ERROR;
		}

		try {
			initStreams();
		} catch (IOException e) {
			return NetworkConnectionEnum.STREAM_ERROR;
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
		} catch (Exception e) { }

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
