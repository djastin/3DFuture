package views.core.graphics;

import java.util.Random;

public class Screen extends Render
{
	private Render test;
	private Render3D render;
	private Random random;
	
	public Screen(int width, int height)
	{
		super(width, height);
		random = new Random();
		render = new Render3D(width, height	);
		test = new Render(256, 256); 
		
		for(int i = 0; i < 256 * 256; i++)
		{
			test.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);
		}
	}
	
	public void render(Game game)
	{
		for(int i = 0; i < width * height; i++)
			pixels[i] = 0;
		
		for(int i = 0; i < 50; i++)
		{
			//Math.sin((System.currentTimeMillis() + i * 8) % 2000.0 / 2000 * Math.PI * 2.0) * 200
			//Math.cos((System.currentTimeMillis() + i * 8) % 2000.0 / 2000 * Math.PI * 2.0) * 200
			int anim = (int) (Math.sin((game.time +i * 2) % 1000.0 / 100) * 100);
			int anim2 = (int) (Math.cos((game.time +i * 2) % 1000.0 / 100) * 100);
			
			render.floor();
			draw(render, 0, 0);
			
			//draw(test, (width - 256) / 2 + anim, (height - 256) / 2 + anim2);	
			
		}
	}
}
