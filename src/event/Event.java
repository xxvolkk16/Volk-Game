package event;

import Charactor.*;

public class Event {
			public static boolean checkHit(Cat Cat,Wave wave,int CatSize,int waveHeight){
							if(Cat.x+CatSize>wave.x&&Cat.x<wave.x) {
								if(Cat.y+CatSize>=wave.y-waveHeight) {
									return true;
								}
							}
							return false;
			}
			
			public static void gameStop(Wave[] wave,Environment[] env) {

			}

}
