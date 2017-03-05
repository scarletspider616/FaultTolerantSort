import java.util.TimerTask;

/*
 * Watchdog timer
 
 */

/**
 * Original Design by Dr. Dick. 
 * Modifications and additions by Joey-Michael Fallone
 *
 
 */
public class Watchdog extends TimerTask {

Thread watched;

	public Watchdog (Thread target){
		// Constructor sets the class variable 'watched'
		watched = target;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		watched.stop();
		// System.out.println(watched.toString() + " timed out.");
	}

	// overrides the "watched" thread 
	// ie switch threads on the same timer
	public void changeThread(Thread target) {
		watched = target;
	}

}
