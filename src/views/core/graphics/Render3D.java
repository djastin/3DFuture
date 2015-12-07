package views.core.graphics;

import java.awt.Dimension;

import views.core.graphics.components.Floor;

public class Render3D extends Render
{
	public Render3D(int width, int height) 
	{
		super(width, height);
	}
	
	public void build()
	{
		Floor floor = new Floor(new Dimension(width, height), pixels);
		floor.build();
	}

}
