package com.mygdx.imageeditor;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;

import Utility.CollisionManager;
import Utility.ImageInputOutput;
import Utility.InputManager;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
	EditWindow editWindow;
	private BitmapFont _font;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public Vector2 ScreenSize;
	
	@Override
	public void create () {
		Instance = this;
		initializeUtilityClasses();
		createGraphicalElements();
	}
	private void initializeUtilityClasses() {
		new CollisionManager();
		new ImageInputOutput();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();

	}
	private void createGraphicalElements() {
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 25);
		Color[] ButtonColors = new Color[]{Color.RED, Color.MAROON, Color.ORANGE, Color.GOLD, Color.YELLOW, Color.BROWN, Color.FOREST, 
			Color.GREEN, Color.BLUE, Color.NAVY, Color.CYAN, Color.PURPLE, Color.VIOLET, Color.GRAY, Color.BLACK, Color.WHITE};
		for (int i = 0; i < ButtonColors.length; i++) {
			new ColorButton(new Vector2(42, 42), new Vector2(i % 2 == 0 ? 42 : 0, (i / 2) * 42), ButtonColors[i]);
		}
		new SaveButton(new Vector2(75,25), new Vector2(0, ScreenSize.y - 25), Color.GRAY);
		new ExitButton(new Vector2(75,25), new Vector2(75, ScreenSize.y - 25), Color.GRAY);
		new ClearDoodleButton(new Vector2(75,25), new Vector2(150, ScreenSize.y - 25), Color.GRAY);
		batch = new SpriteBatch();
		editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));
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
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x, editWindow.Scale.y);
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			if(rec instanceof Button) {
				Button button = (Button) rec;
				if(button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
				button.Scale.x, Align.center, false);
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
	
	public void filesImported(String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if(map == null) return;
		editWindow.RecTexture = new Texture(map);
	}
}
