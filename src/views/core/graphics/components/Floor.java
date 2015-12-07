package views.core.graphics.components;

import java.awt.Dimension;

public class Floor 
{
	private Dimension dimension;
	private int[] pixels;
	
	public Floor(Dimension dimension, int[] pixels)
	{
		this.dimension = dimension;
		this.pixels = pixels;
	}
	
	public void build()
	{
		for(int y = 0; y < dimension.height; y++)
		{
			double yDepth = y - dimension.height / 2.4;
			double z = 100.0 / yDepth;
			
			for(int x = 0; x < dimension.width; x++)
			{
				double xDepth = x - dimension.width / 2;  
				xDepth *= z;
							
				/* & = AND bitwise operator
				 * << = shift left operator
				 * >> = shift right operator
				 * 
				 * */
				
				int xx = (int) (xDepth) & 5;
				pixels[x+y * dimension.width] = xx * 128; 
			}
		}
	}

	public Dimension getDimension()
	{
		return dimension;
	}

	public void setDimension(Dimension dimension)
	{
		this.dimension = dimension;
	}

	public int[] getPixels()
	{
		return pixels;
	}

	public void setPixels(int[] pixels)
	{
		this.pixels = pixels;
	}
}
