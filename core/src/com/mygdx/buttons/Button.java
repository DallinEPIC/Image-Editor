package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Rec2D;

import Utility.IClickable;
import Utility.IHoverable;
import Utility.InputManager;

public class Button extends Rec2D implements IHoverable, IClickable {
	private ButtonState _state = ButtonState.None;
	public enum ButtonState {Clicked, Hovered, None};
	public String ButtonText;
	protected Color _startColor;
	private Color _hoverColor;
	private Color _clickColor;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		_clickColor = new Color(_startColor.r / 4f, _startColor.g / 4f, _startColor.b / 4f, 1);
		_hoverColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		InputManager.Instance.Clickables.add(this);
		InputManager.Instance.Hoverables.add(this);
	}
		
	public void onClickDown(Vector2 position) {
		if(_state == ButtonState.Clicked) return;
		_state = ButtonState.Clicked;
		_recColor = _clickColor;
		super.generateTexture();
	}
	public void onHovered() {
		if(_state == ButtonState.Clicked) return;
		if(_state == ButtonState.Hovered) return;
		_state = ButtonState.Hovered;
		_recColor = _hoverColor;
		super.generateTexture();
	}
	public void onHoverExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		super.generateTexture();
	}
	public void onClickUp(Vector2 position) {
		if(_state != ButtonState.Clicked) return;
		_recColor = _hoverColor;
		super.generateTexture();
		_state = ButtonState.Hovered;
	}
	public void onClickDragged(Vector2 position) {
	}
}
