package com.mygdx.imageeditor;

import java.io.IOException;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();
	public static InputManager Instance;
	private IHoverable _currentlyHovered;
	private IClickable _currentlyClicked;
	private boolean _controlPressed;
	
	public InputManager() {
		Instance = this;
	}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		IClickable collision = CollisionManager.Instance.getClicked(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(collision != null) collision.onClickDown(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		_currentlyClicked = collision;
		return true;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(_currentlyClicked != null) {
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
			_currentlyClicked = null;
		}
		return true;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		// Start and stop hovering on elements
		IHoverable collision = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(collision != _currentlyHovered && _currentlyHovered != null) _currentlyHovered.onHoverExit();
		if(collision != null) collision.onHovered();
		_currentlyHovered = collision;
		// Drag over currently clicked element
		IClickable clickedCollision = CollisionManager.Instance.getClicked(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(clickedCollision != null && clickedCollision == _currentlyClicked) {
			clickedCollision.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		}
		return true;
	}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		return false;
	}
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean scrolled(float amountX, float amountY) {return false;}
	
	public boolean keyDown(int keycode) {
		if(_controlPressed && keycode == Keys.S)
			try {ImageInputOutput.Instance.saveImage("C:\\Users\\Thund\\Desktop\\test.bmp");}
			catch (IOException e) {e.printStackTrace();}
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
		return false;
	}
	public boolean keyUp(int keycode) {
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
		return false;
	}
	public boolean keyTyped(char character) {return false;}
}
