package com.mygdx.imageeditor;

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
}
