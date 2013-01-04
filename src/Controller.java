/*
    Controller - Controller.java

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

public class Controller {
	private ModelInterface model;
	private ViewInterface view;

	public Controller(ModelInterface m, ViewInterface v) {
		model = m;
		view  = v;
	}

	public void showView() {
		view.activate();
	}

	public void newGame(int stoneCount) {
		model.setStoneCount(stoneCount);
		model.reset();
		view.refresh();
	}

	public void p1Move(int bin) {
		assert(bin >= 0 && bin <= 5);
		model.p1Move(bin);
	}

	public void p2Move(int bin) {
		assert(bin >= 0 && bin <= 5);
		model.p2Move(bin);
	}
}
