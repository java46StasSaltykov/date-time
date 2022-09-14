package telran.time.application;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

//Application class
//Performs beeps each specified time interval

public class Reminder {
	
	public static void main(String[] args) {
		// mandatory args[0] interval value
		// mandatory args[1] ChronoUnit enum string value (case insensitive)
		// optional args[2] when ended in the given ChronoUnit (see args[1]), default in 1 hour
		// beep System.out.println("\007\007\007") - will sound only on real console
		// example of launch : java -jar reminder.jar 10 seconds 100 
		// - each 10 seconds during 100 seconds there should be beeps
		
		
		try {
			int beepInterval = getBeepInterval(args[0]);
			ChronoUnit timeUnit = getTimeUnit(args[1]);
			int duration = args[2] != null ? getDuration(args[2]) : -1;
			remind(beepInterval, timeUnit, duration);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void remind(int beepInterval, ChronoUnit timeUnit, int duration) {
		ChronoUnit durationTimeUnit = duration > 0 ? timeUnit : ChronoUnit.HOURS;
		if (duration < 0) {
			duration = 1;
		}
		Temporal currentTime = LocalTime.now();
		LocalTime finishTime = LocalTime.now().plus(duration, durationTimeUnit);
		while (!LocalTime.now().equals(finishTime)) {
			if (LocalTime.now().equals(currentTime.plus(beepInterval, timeUnit))) {
				System.out.println("\007\007\007");
				currentTime = LocalTime.now();
			}
		}
		System.out.println("Finished!");
	}

	private static int getDuration(String duration) throws Exception {
		try {
			int res = Integer.parseInt(duration);
			if (res <= 0) {
				throw new Exception("duration should be positive a number");
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("duration should be a number");
		}
	}

	private static ChronoUnit getTimeUnit(String timeUnit) throws Exception {
		try {
			String unit = timeUnit.toUpperCase();
			ChronoUnit[] chronoValues = ChronoUnit.values();
			ChronoUnit res = ChronoUnit.valueOf(unit);
			for (int i = 0; i < chronoValues.length; i++) {
				if (chronoValues[i].equals(res)) {
					return res;
				}
			}
			return null;		
			
		} catch (IllegalArgumentException e) {
			throw new Exception("Time unit should be a string!");
		}
	}

	private static int getBeepInterval(String beepInterval) throws Exception {
		try {
			int res = Integer.parseInt(beepInterval);
			if (res <= 0) {
				throw new Exception("Interval should be a positive number");
			}
			return res;
			
		} catch (NumberFormatException e) {
			throw new Exception("Interval should be a number");
		}
	}

}
