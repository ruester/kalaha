/*
    ViewInterface - ViewInterface.java

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

public interface ViewInterface {
	// register controller
	public void setController(Controller c);

	// activate the view to be visible
	// or show some kind of introduction
	public void activate();

	// there was changes in the model
	// query model and show data
	public void refresh();

	// game has ended; show end result
	public void finish();

	// show an error message
	public void showError(String message);
}
