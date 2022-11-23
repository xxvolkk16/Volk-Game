package Play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;

import Home.home;
import display.Display;

public class play extends JFrame implements ActionListener{  
	home homee = new home();
	Display d = new Display();
	public play(){
		this.setSize(700,600);
		this.add(homee);
                homee.BStart.addActionListener(this);
                
	}
        
    @Override
	public void actionPerformed(ActionEvent e) {
            if(e.getSource()== homee.BStart){
		        this.setLocationRelativeTo(null);
		        this.remove(homee);
                this.setSize(700,600);
                this.add(d);
                d.requestFocusInWindow();
           
	}
            this.validate();
            this.repaint();
        
     
        
        
	
        }
}
