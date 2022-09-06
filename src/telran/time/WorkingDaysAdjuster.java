package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDaysAdjuster implements TemporalAdjuster {
	
	public WorkingDaysAdjuster(int[] daysOff, int nDays) {
		this.daysOff = daysOff;
		this.nDays = nDays;
	}
	public WorkingDaysAdjuster() {
		
	}

	public int[] getDaysOff() {
		return daysOff;
	}

	public void setDaysOff(int[] daysOff) {
		this.daysOff = daysOff;
	}

	public int getnDays() {
		return nDays;
	}

	public void setnDays(int nDays) {
		this.nDays = nDays;
	}
	
	int[] daysOff;
	int nDays;
	
	@Override
	public Temporal adjustInto(Temporal temporal) {
		if (daysOff.length == 7) {
		    temporal = temporal.plus(0, ChronoUnit.DAYS);
		} else if (daysOff.length == 0) {
			temporal = temporal.plus(nDays, ChronoUnit.DAYS);
		} else if (daysOff.length < 7) {
			temporal = temporal.plus(nDays + daysOff.length, ChronoUnit.DAYS);
		}
		return temporal;
	}

}
