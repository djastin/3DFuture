package views.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Dashboard extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Display display;

	public Dashboard()
	{
		display = new Display();
		initGUI();
	}
	
	public void initGUI()
	{
		add(display);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);
	}
	
	public void startGame()
	{
		display.start();
		setVisible(true);
	}

	public Display getDisplay()
	{
		return display;
	}

	public void setDisplay(Display display) 
	{
		this.display = display;
	}
}
