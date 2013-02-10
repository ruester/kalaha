/*
    ClientInterface - ClientInterface.java

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
