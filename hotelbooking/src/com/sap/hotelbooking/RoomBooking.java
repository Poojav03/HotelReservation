package com.sap.hotelbooking;

import java.util.*;

public class RoomBooking {

	
	/************************************ CONSTANTS DECLARED ***********************************/
	private static final String INVALID_RANGE = "Sorry we cant proceed with your booking as the room size entered is not valid";
	private static final String INPUT_VALIDITY = "Please enter your input as 0 or 1";
	private static final String THANK_YOU = "Thank you for your time!! We were delighted to serve you..";
	private static final String CONTINUE_BOOKING = "Please enter 1 in case you want to continue with another booking or 0 if you do not have any other bookings to make.";
	private static final String DECLINE = "We are sorry to inform you that your Booking is declined,Please try again with other dates";
	private static final String SUCCESS = "Congratulations!!Your Booking is successfully accepted,Enjoy your stay at our Hotel";
	private static final String DATE_RANGE = "Please enter the days ranging from starting from current day(0) to any future day upto ";
	private static final String STARTDAY_LESS_ENDDAY = "Please make sure that startday is lesser than endday";
	private static final String ENDDAY_INPUT = "Enter the end day";
	private static final String INTEGER_VALIDITY = "Please enter a valid integer number";
	private static final String INPUT_STARTDAY = "Enter the start day";
	private static final String AVAILABLE_DAYS_PER_ROOM = "AvailableDaysPerRoom";
	private static final String HOTEL_SIZE = "hotelSize";
	private static final String HOTEL_SIZE_INPUT = "Enter Hotel size between 1 and 1000";
	
	
	
	/************************************ GLOBAL VARIABLES DECLARED******************************/
	public int[][] bookingSlots;
	
	
	/*************************** START OF METHOD INPUTSIZE ***************************************/
	
	/* This method fetches the Size of the hotel room */
	
	@SuppressWarnings("resource")
	private Map<String, Integer> inputSize() {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> mapInputs = new HashMap<String, Integer>();
		
		//Assumption made that maximum number of hotel rooms <=1000 and total number of days allowed for booking is 365,to change this uncomment code below
		int hotelSize = 0,hotelmaxSize = 1000,maxDays = 365;
		
		/**To change the maximum number of hotel rooms allowed uncomment this code below**/
		/*
		System.out.println("Please enter the maximum number of days allowed");
		hotelmaxSize = in.nextInt();
		*/
		/**To change the maximum number of hotel rooms allowed uncomment this code above**/
		
		
			// Size of the hotel is taken from user
			System.out.println(HOTEL_SIZE_INPUT);
			hotelSize = in.nextInt();
			if (hotelSize > hotelmaxSize || hotelSize < 1) {
				System.out.println(INVALID_RANGE);
			}
			else{
				mapInputs.put(HOTEL_SIZE, hotelSize);
			}
			
			/** To change maximum number of days allowed for booking uncomment this code below **/
			/*
			System.out.println("Enter Total Available Days per room:");
			maxDays = in.nextInt();
			 * 
			 */
			/** To change maximum number of days allowed for booking uncomment this code below **/
			
			mapInputs.put(AVAILABLE_DAYS_PER_ROOM, maxDays);
		return mapInputs;
	}
	
	/*********************************** END OF METHOD INPUTSIZE *************************************/
	
	
	
	/***********************************START OF METHOD BOOKINGQUERY*********************************/

	/* Method to fetch the startday and endday and also display the Accept and Decline message */
	
	public void bookingQuery(int size, int bookingSlots[][],int maxsize) {

		/* To get the startday and endday for Booking from user*/
		
		Scanner in = new Scanner(System.in);
		int startday = -1, endday = -1;
		try {
				System.out.println(INPUT_STARTDAY);
				startday = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(INTEGER_VALIDITY);
		}
		try {
				System.out.println(ENDDAY_INPUT);
				endday = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(INTEGER_VALIDITY);
		}
		
		if(startday > endday){
			System.out.println(STARTDAY_LESS_ENDDAY);
			continuewithnextbooking(size,bookingSlots,maxsize);
		}
		else if(startday < 0 || endday > maxsize){
			System.out.println(DATE_RANGE + maxsize);
			continuewithnextbooking(size,bookingSlots,maxsize);
		}
		else{
			
			/*Based to the start day and end day room availability is checked*/
				
			int avail = checkAvailability(startday, endday, size,bookingSlots);
	
			if (avail == 1) {
				System.out.println(SUCCESS);
			}
			else {
				System.out.println(DECLINE);
			}
	
				continuewithnextbooking(size,bookingSlots,maxsize);
		}
		in.close();
		
	}
	/***********************************END OF METHOD BOOKINGQUERY*********************************/
	
	
	
	/*********************************** START OF METHOD CHECKAVAILABILITY *********************************/

	/*Method which checks if the room is available for the given dates*/
	
	public int checkAvailability(int startday, int endday, int size, int bookingSlots[][]) {
		int avail = 0;
		for (int i = 0; i < size; i++) {
			for (int j = startday; j <= endday + 1; j++) {
				if (j > endday) {
					roomBooking(startday, endday,i,bookingSlots);
					avail = 1;
					return avail;
				} 
				else {
					try{
						if (bookingSlots[i][j] == 1) {
							break;
						}
					}catch (ArrayIndexOutOfBoundsException e){
						break;
					}
				}
			}
		}
		return avail;
	}

	/*********************************** END OF METHOD CHECKAVAILABILITY *********************************/
	
	
	
	/*********************************** START OF METHOD ROOMBOOKING *************************************/
	/*Method to book the rooms in case the room is available for the given days*/
	
	public static void roomBooking(int startday, int endday, int roomnumber, int bookingSlots[][]) {
		for (int j = startday; j <= endday; j++) {
			bookingSlots[roomnumber][j] = 1;
		}

	}
	/*********************************** END OF METHOD ROOMBOOKING ******************************************/
	
	
	
	/*********************************** START OF METHOD CONITNUENEXTBOOKING ********************************/
	/*Method to check if the user wants to proceed with another booking*/
	
	public void continuewithnextbooking(int size,int bookingSlots[][],int maxsize){
				System.out.println(CONTINUE_BOOKING);
				int proceed;
				Scanner in = new Scanner(System.in);
				proceed = in.nextInt();
				try{
					if (proceed == 1) {
						bookingQuery(size,bookingSlots,maxsize);
					} else {
						System.out.println(THANK_YOU);
				}
				}catch(InputMismatchException e){
					System.out.println(INPUT_VALIDITY);
					continuewithnextbooking(size,bookingSlots,maxsize);
				}
				in.close();
	}
	/*********************************** END OF METHOD CONITNUENEXTBOOKING *********************************/

	

	/*******************************************MAIN METHOD**************************************************/
	
	public static void main(String[] args) {

		/*Object of the class is created*/
		RoomBooking booking = new RoomBooking();
		Map<String, Integer> inputMap = booking.inputSize();
		/*Array to store the bookings*/
		try{
		booking.bookingSlots = new int[inputMap.get(HOTEL_SIZE)][inputMap.get(AVAILABLE_DAYS_PER_ROOM)];
		booking.bookingQuery(inputMap.get(HOTEL_SIZE), booking.bookingSlots,(inputMap.get(AVAILABLE_DAYS_PER_ROOM)-1));
			
	}
		catch(NullPointerException e){
		}
		
	}

}
