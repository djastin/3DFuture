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
			double ceiling = (y - dimension.height / 2.0) / dimension.height;
			double z = 2 / ceiling;
			
			for(int x = 0; x < dimension.width; x++)
			{
				double depth = (x - dimension.width / 2) / dimension.height;  
				depth *= z;
							
				/* & = AND bitwise operator
				 * << = shift left operator
				 * >> = shift right operator
				 * 
				 * */
				
				int xx = (int) (depth) & 5;
				int yy = (int) (z) & 5;
				pixels[x+y * dimension.width] = (xx * 16) | (yy * 16) * 64; 
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
