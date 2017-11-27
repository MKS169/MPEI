package proj;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
	private int day, month, year;
	
	public Date(){
		this.day = today().getDay();
		this.month = today().getMonth();
		this.year = today().getYear();		
	}
	
	public Date(int day, int month, int year){
			this.day = day;
			this.month = month;
			this.year = year;
	}
	
	public Date(String date){
		String[] temp = date.split("/");
		day = Integer.parseInt(temp[0]);
		month = Integer.parseInt(temp[1]);
		year = Integer.parseInt(temp[2]);
	}
	
	public int getDay(){
		return day;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getYear(){
		return year;
	}
	
	@Override
	public String toString(){
		return day+"/"+month+"/"+year;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj) return true;
		if (obj == null || this.getClass()!=obj.getClass()) return false;
		Date d = (Date) obj;
		return day == d.getDay() && month == d.getMonth() && year == d.getYear();
	}
	
	public static boolean ValidDate(String date){
		String[] temp = date.split("-/");
		int day=-1, month=-1, year=-1;
		
		if (temp.length!=3) return false;
		try{
			day = Integer.parseInt(temp[0]);
			month = Integer.parseInt(temp[1]);
			year = Integer.parseInt(temp[2]);
		}catch(IllegalArgumentException e){
			return false;
		}
		return ValidDate(day, month, year);
	}
	
	public static boolean ValidDate(int day, int month, int year){
		if(year>=0  && day>0){
			switch(month){
				case 1: case 3: case 5: case 7: case 8: case 10: case 12: 
					return day<=31;
				case 2:
					if(year%400==0 || year%4==0 && year%100!=0){
						return day<=29;
					}else{
						return day<=28;
					}
				case 4: case 6: case 9: case 11:
					return day<=30;
				default:
					return false;
			}
		}else{
			return false;
		}
	}
	
	public static Date today(){
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		return new Date(day, month, year);
	}
}
