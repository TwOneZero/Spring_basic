package com.fastcampus.ch2;

public class Car    {
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car{" +
				"color='" + color + '\'' +
				'}';
	}

	private String color = "red";
	public String getColor() { return color; }
}