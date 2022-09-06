package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		temporal = temporal.plus(1, ChronoUnit.DAYS);
		while (!isFriday13(temporal)) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);
		}
		return temporal;
	}
	
	private boolean isFriday13(Temporal temporal) {
		return temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.FRIDAY.ordinal() + 1 && temporal.get(ChronoField.DAY_OF_MONTH) == 13;
	}

}
