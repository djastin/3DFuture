package views.core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import views.core.graphics.Game;
import views.core.graphics.Screen;

public class Display extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
		private Thread thread;
	private boolean running;
	private BufferedImage img;
	private Screen screen;
	private Game game;
	private int[] pixels;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Future Now!";
	
	public Display()
	{		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		running = false;
		screen = new Screen(WIDTH, HEIGHT);
		game = new Game();
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	private void tick()
	{
		game.tick();
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.render(game);
		
		for(int i = 0; i < WIDTH * HEIGHT; i++)
		{
			pixels[i] = screen.pixels[i];
		}
				
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}
	
	public void start()
	{
		if(running) 
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		if(!running)
			return;
		running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Class Display Error: " + e.getMessage());
		}
	}
	
	public void run()
	{		
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;

		while(running)
		{
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while(unprocessedSeconds > secondsPerTick)
			{
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				
				if(tickCount % 60 == 0)
				{
					System.out.println(frames + "fps");
					previousTime += 1000;
					frames = 0;
				}
			}
			
			if(ticked)
			{
				render();
				frames++;
			}
			
			render();
			frames++;
		}
	}
}
