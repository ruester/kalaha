/*
    ServerInterface - ServerInterface.java

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
