package com.sap.hotelbooking;

import java.util.*;

public class RoomBooking {

	/************************************ CONSTANTS DECLARED ***********************************/
	private static final String INPUT_DAYS = "Enter Total Number of days for which Booking is allowed: ";
	private static final String INVALID_RANGE = "Sorry we cannot proceed with your booking input entered is not valid";
	private static final String INPUT_VALIDITY = "Please enter your input as 0 or 1";
	private static final String THANK_YOU = "Thank you for your time!! We were delighted to serve you..";
	private static final String CONTINUE_BOOKING = "Please enter 1 in case you want to continue with another booking or 0 if you do not have any other bookings to make.";
	private static final String DECLINE = "We are sorry to inform you that your Booking is declined,Please try again with other dates";
	private static final String SUCCESS = "Congratulations!!Your Booking is successfully accepted,Enjoy your stay at our Hotel";
	private static final String DATE_RANGE = "Please enter the days starting from current day0 to any future day upto day";
	private static final String STARTDAY_LESS_ENDDAY = "Please make sure that startday is lesser than endday";
	private static final String ENDDAY_INPUT = "Enter the end day";
	private static final String INTEGER_VALIDITY = "Please enter a valid number.Note that partial values are not allowed";
	private static final String INPUT_STARTDAY = "Enter the start day";
	private static final String AVAILABLE_DAYS_PER_ROOM = "AvailableDaysPerRoom";
	private static final String HOTEL_SIZE = "hotelSize";
	private static final String HOTEL_SIZE_INPUT = "Enter Total number of Rooms in the hotel";
	
	
	
	/************************************ GLOBAL VARIABLES DECLARED******************************/
	public int[][] bookingSlots;
	
	
	/*************************** START OF METHOD INPUTSIZE ***************************************/
	
	/* This method fetches the Size of the hotel room */
	
	@SuppressWarnings("resource")
	private Map<String, Integer> inputSize() {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> mapInputs = new HashMap<String, Integer>();
		
		//Assumption made as per assignment  maximum number of hotel rooms <=1000 and total number of days allowed for booking is 365
		int hotelmaxSize = 1000,maxDays = 365;
		
		/***********************To make the number of hotel rooms and total number of days allowed configurable uncomment this piece of code below********************/
		
		/*
		//To input the maximum numbers days allowed for booking
			System.out.println(INPUT_DAYS);
			try{
			maxDays = in.nextInt();
			}
			catch(InputMismatchException e){
			 System.out.println(INTEGER_VALIDITY);
			 System.exit(0);
			}
			if(maxDays<0){
				System.out.println(INVALID_RANGE);
				System.exit(0);
			}
			

			//To input the total number of hotel rooms
			System.out.println(HOTEL_SIZE_INPUT);
			try{
			hotelmaxSize = in.nextInt();
			}catch(InputMismatchException e){
				System.out.println(INTEGER_VALIDITY);
				System.exit(0);
				}
			
			if (hotelmaxSize < 1) {
				System.out.println(INVALID_RANGE);
				System.exit(0);
			}
			
		*/
			/***********************To make the number of hotel rooms and total number of days allowed configurable uncomment this piece of code above********************/
		
			mapInputs.put(HOTEL_SIZE, hotelmaxSize);
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
			continueWithNextbooking(size,bookingSlots,maxsize);
		}
		try {
				System.out.println(ENDDAY_INPUT);
				endday = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(INTEGER_VALIDITY);
			continueWithNextbooking(size,bookingSlots,maxsize);
		}
		
		if(startday > endday){
			System.out.println(STARTDAY_LESS_ENDDAY);
			continueWithNextbooking(size,bookingSlots,maxsize);
		}
		else if(startday < 0 || endday > maxsize){
			System.out.println(DATE_RANGE + maxsize);
			continueWithNextbooking(size,bookingSlots,maxsize);
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
	
				continueWithNextbooking(size,bookingSlots,maxsize);
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
	
	public void continueWithNextbooking(int size,int bookingSlots[][],int maxsize){
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
					continueWithNextbooking(size,bookingSlots,maxsize);
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
		booking.bookingSlots = new int[inputMap.get(HOTEL_SIZE)][inputMap.get(AVAILABLE_DAYS_PER_ROOM)];
		booking.bookingQuery(inputMap.get(HOTEL_SIZE), booking.bookingSlots,(inputMap.get(AVAILABLE_DAYS_PER_ROOM)-1));
	}

}
