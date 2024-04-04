package com.mygdx.imageeditor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
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
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
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
		Vector2 rectangleScale = new Vector2(100, 100);
		Button button;
		button = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2f, ScreenSize.y / 2f + rectangleScale.y / 2f),
			Color.ORANGE);
		button = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f + rectangleScale.x, ScreenSize.y / 2f + rectangleScale.y / 2f),
			Color.GREEN);
		button = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x / 2f - rectangleScale.x / 2f, ScreenSize.y / 2f - rectangleScale.y / 2f),
				Color.WHITE);
		button = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2f, ScreenSize.y / 2f - 3*rectangleScale.y / 2f),
			Color.BLUE);
		button = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f + rectangleScale.x, ScreenSize.y / 2f - 3*rectangleScale.y / 2f),
			Color.RED);
		CollisionManager collisionManager = new CollisionManager();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		Rec2D rec;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
