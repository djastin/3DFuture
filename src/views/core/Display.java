package views.core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import views.core.graphics.Screen;

public class Display extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private Thread thread;
	private boolean running;
	private BufferedImage img;
	private Screen screen;
	private int[] pixels;
	
	public static final String TITLE = "Future Now!";
	
	public Display()
	{
		width = 800;
		height = 600;
		running = false;
		
		screen = new Screen(width, height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	private void tick()
	{

	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		
		for(int i = 0; i < width * height; i++)
		{
			pixels[i] = screen.pixels[i];
		}
				
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, width, height, null);
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
		while(running)
		{
			tick();
			render();
		}
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

}
