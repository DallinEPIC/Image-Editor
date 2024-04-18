package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int[] ints = unsignBytes(bytes);
		int result = 0;
		for(int i = 0; i < ints.length; i++) {
			result += ints[i] << (8 * i);
		}
		return result;
	}
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			if (bytes[i] >= 0) {
				ints[i] = bytes[i];
			}
			else {
				ints[i] = bytes[i] + 256;
			}
		}
		return ints;
	}
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		int temp;
		result[0] = (byte) (value >> 24);
		temp = value << 8;
		result[1] = (byte) (temp >> 24);
		temp = value << 16;
		result[2] = (byte) (temp >> 24);
		temp = value << 24;
		result[3] = (byte) (temp >> 24);
		return result;
	}
	public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
		Pixmap newMap = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		for(int x = 0; x < newMap.getWidth(); x++) {
			for(int y = 0; y < newMap.getHeight(); y++) {
				int sourceX = (int)Math.round(x / desiredSize.x * map.getWidth());
				int sourceY = (int)Math.round(y / desiredSize.y * map.getHeight());
				newMap.setColor(map.getPixel(sourceX, sourceY));
				newMap.drawPixel(x, y);
			}
		}
		return newMap;
	}
}
