package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D {
	private static int _buttonCount;
	private int _buttonNumber;
	private ButtonState _state = ButtonState.None;
	public enum ButtonState {Clicked, Hovered, None};
	private Color _startColor;
	private Color _hoverColor;
	private Color _clickColor;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		_clickColor = new Color(_startColor.r / 4f, _startColor.g / 4f, _startColor.b / 4f, 1);
		_hoverColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		InputManager.Instance.Buttons.add(this);
		_buttonCount +=1;
		_buttonNumber = _buttonCount;
	}
		
	public void onClickDown() {
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
	public void onClickUp() {
		_recColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		super.generateTexture();
		_state = ButtonState.Hovered;
	}
}
