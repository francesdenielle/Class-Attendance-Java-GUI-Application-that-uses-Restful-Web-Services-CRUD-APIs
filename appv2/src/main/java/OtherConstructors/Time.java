package OtherConstructors;

public class Time {
	private int hour;
	private int minute;
	private String meridiem;

	public Time(int hour, int minute, String meridiem) {
		this.hour = hour;
		this.minute = minute;
		this.meridiem = meridiem;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	public String getMeridiem() {
		return meridiem;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public void setMeridiem(String meridiem) {
		this.meridiem = meridiem;
	}
	
	public static Time newTime (String time) {
		String temptime = time;
		String[] parts = temptime.split(":");
		
		int hour = Integer.parseInt(parts[0]);
		System.out.println(parts[1]);
        int minute = Integer.parseInt(parts[1]);
        String meridiem;
        if (hour >= 12) {
        	meridiem = "pm";
        	if (hour > 12) {
        		hour -= 12;
        	}
        }else {
        	meridiem = "am";
        }
        
        Time newTime = new Time(hour, minute, meridiem);
		return newTime;
	}
}
