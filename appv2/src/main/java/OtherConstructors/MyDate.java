package OtherConstructors;

public class MyDate {
	
	private int month;
	private int day;
	private int year;
	
	public MyDate (int month, int day, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	public int getYear() {
		return year;
	}
	
	public void setMonth(int month) {
		this.month = month+1;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setYear(int year) {
		this.year = year+1900;
	}
	
	public static MyDate newDate(String date) {
		String[] parts = date.split("-");
        MyDate dateified = new MyDate(
        		Integer.parseInt(parts[1]),
        		Integer.parseInt(parts[2]),
        		Integer.parseInt(parts[0]));
        return dateified;
	}
	
}
