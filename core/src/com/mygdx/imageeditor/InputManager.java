package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	public boolean keyDown(int keycode) {return false;}
	public boolean keyUp(int keycode) {return false;}
	public boolean keyTyped(char character) {return false;}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
	public boolean mouseMoved(int screenX, int screenY) {return false;}
	public boolean scrolled(float amountX, float amountY) {return false;}
}
