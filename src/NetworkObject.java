/*
    NetworkObject - NetworkObject.java

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
