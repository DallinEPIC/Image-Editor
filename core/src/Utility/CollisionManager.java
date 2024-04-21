package Utility;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Rec2D;

public class CollisionManager {
	public static CollisionManager Instance;

	public CollisionManager() {
		Instance = this;
	}
	public IClickable getClicked(Vector2 coordinates) {
		Rec2D iterationClickable;
		for(int i = 0; i < InputManager.Instance.Clickables.size; i++) {
			iterationClickable = (Rec2D) InputManager.Instance.Clickables.get(i);
			if(
				coordinates.x > iterationClickable.Position.x && 
				coordinates.y > iterationClickable.Position.y &&
				coordinates.x < iterationClickable.Position.x + iterationClickable.Scale.x &&
				coordinates.y < iterationClickable.Position.y + iterationClickable.Scale.y
			) { return (IClickable) iterationClickable; }
		}
		return null;
	}
	public IHoverable getHovered(Vector2 coordinates) {
		Rec2D iteratingHoverable;
		for(int i = 0; i < InputManager.Instance.Hoverables.size; i++) {
			iteratingHoverable = (Rec2D) InputManager.Instance.Hoverables.get(i);
			if(
				coordinates.x > iteratingHoverable.Position.x && 
				coordinates.y > iteratingHoverable.Position.y &&
				coordinates.x < iteratingHoverable.Position.x + iteratingHoverable.Scale.x &&
				coordinates.y < iteratingHoverable.Position.y + iteratingHoverable.Scale.y
			) { return (IHoverable) iteratingHoverable; }
		}
		return null;
	}
}
