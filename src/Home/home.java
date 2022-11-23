package Home;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class home extends JPanel{
        private  ImageIcon Bg = new ImageIcon(this.getClass().getResource("img/Dragonball1.png"));
        private  ImageIcon GameIcon = new ImageIcon(this.getClass().getResource("img/Dragonbicon.png"));
	private  ImageIcon start = new ImageIcon(this.getClass().getResource("img/Start.jpg"));
	public JButton BStart = new JButton(start);
        
	public home(){
            setLayout(null);
           
            BStart.setBounds(270,370,165,75);
            add(BStart);
	}
        @Override
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(Bg.getImage(),1,1,700,600,this);
             g.drawImage(GameIcon.getImage(), 80, 70, 500, 300, this);
	}
}