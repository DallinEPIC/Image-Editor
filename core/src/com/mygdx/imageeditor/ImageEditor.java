package com.mygdx.imageeditor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
	Rec2D rectangle;
	Rec2D rectangle2;
	public Vector2 ScreenSize;

	public ImageEditor() {
		Instance = this;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		Vector2 rectangleScale = new Vector2(100, 50);
		rectangle = new Rec2D(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2f, ScreenSize.y / 2f - rectangleScale.y / 2f),
			Color.ORANGE);
		rectangle2 = new Rec2D(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f + rectangleScale.x, ScreenSize.y / 2f - rectangleScale.y / 2f),
			Color.GREEN);
		CollisionManager collisionManager = new CollisionManager(rectangle, rectangle2);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(rectangle.RecTexture, rectangle.Position.x, rectangle.Position.y);
		batch.draw(rectangle2.RecTexture, rectangle2.Position.x, rectangle2.Position.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
