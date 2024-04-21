package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

import Utility.IClickable;
import Utility.InputManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class EditWindow extends Rec2D implements IClickable {
	public static EditWindow Instance;
	public Texture DoodleTexture;
	public Pixmap _doodleMap;
	public Color DrawColor;
	private Vector2 _previousPaintPosition;
	public EditWindow(Vector2 scale, Vector2 position) {
		super(scale, position, Color.SLATE);
		Instance = this;
		InputManager.Instance.Clickables.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		DrawColor = Color.ORANGE;
		_doodleMap.setColor(DrawColor);
		DoodleTexture = new Texture(_doodleMap);
	}
	public void paintAtPosition(Vector2 position) {
		Vector2 paintPosition = new Vector2(position.x - Position.x,Scale.y - position.y);
		int startX = (int) _previousPaintPosition.x;
		int startY = (int)_previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		_doodleMap.drawLine(startX, startY, endX, endY);
		_doodleMap.drawLine(startX + 1, startY, endX + 1, endY);
		_doodleMap.drawLine(startX - 1, startY, endX - 1, endY);
		_doodleMap.drawLine(startX, startY + 1, endX, endY + 1);
		_doodleMap.drawLine(startX, startY - 1, endX, endY - 1);
		_previousPaintPosition = paintPosition;
		DoodleTexture = new Texture(_doodleMap);
	}
	public void onClickDown(Vector2 mousePosition) {
		if(_previousPaintPosition == null) {
			_previousPaintPosition = new Vector2(mousePosition.x - Position.x,Scale.y - mousePosition.y);
		}
		paintAtPosition(mousePosition); 
	}
	public void onClickUp(Vector2 mousePosition) { _previousPaintPosition = null;}
	public void onClickDragged(Vector2 mousePosition) { paintAtPosition(mousePosition); }

}
