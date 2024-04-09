package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class EditWindow extends Rec2D implements IClickable {
	public Texture DoodleTexture;
	private Pixmap _doodleMap;
	public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
		super(scale, position, backgroundColor);
		InputManager.Instance.Clickables.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
	}
	public void paintAtPosition(Vector2 position) {
		_doodleMap.drawPixel((int) (position.x - Position.x), (int) (Scale.y - position.y + Position.y));
		DoodleTexture = new Texture(_doodleMap);
	}
	public void onClickDown(Vector2 mousePosition) { paintAtPosition(mousePosition); }
	public void onClickUp(Vector2 mousePosition) {
	}
	public void onClickDragged(Vector2 mousePosition) { paintAtPosition(mousePosition); }
}
