package com.sap.hotelbooking;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomBookingTest {

/***variables required for the test**/
int size, Accept = 1,Decline = 0;
	

/*************************TEST CASES TO CHECK THE CHECKAVAILABILITY METHOD**************/

     /***********************TEST CASE 1 : Requests outside our planning period ******************************/
	@Test
	public final void testcase1CheckAvailability() {
		
		RoomBooking testcase1 = new RoomBooking();
		size = 1;
		int [][]testcase1slot = new int[30][30];
		assertEquals(Decline,testcase1.checkAvailability(-4,2,size,testcase1slot));
		assertEquals(Decline,testcase1.checkAvailability(200,400,size,testcase1slot));
	}
	
	 /***********************TEST CASE 2 : Requests are accepted *******************************************/
	@Test
	public final void testcase2CheckAvailability() {
		
		RoomBooking testcase2 = new RoomBooking();
		size = 3;
		int [][]testcase2slot = new int[30][30];
		assertEquals(Accept,testcase2.checkAvailability(0,5,size,testcase2slot));
		assertEquals(Accept,testcase2.checkAvailability(7,13,size,testcase2slot));
		assertEquals(Accept,testcase2.checkAvailability(3,9,size,testcase2slot));
		assertEquals(Accept,testcase2.checkAvailability(5,7,size,testcase2slot));
		assertEquals(Accept,testcase2.checkAvailability(6,6,size,testcase2slot));
		assertEquals(Accept,testcase2.checkAvailability(0,4,size,testcase2slot));
		
	}
	
	/***********************TEST CASE 3: Requests are declined**********************************************/
	@Test
	public final void testcase3CheckAvailability() {
		
		RoomBooking testcase3 = new RoomBooking();
		size = 3;
		int [][]testcase3slot = new int[30][30];
		assertEquals(Accept,testcase3.checkAvailability(1,3,size,testcase3slot));
		assertEquals(Accept,testcase3.checkAvailability(2,5,size,testcase3slot));
		assertEquals(Accept,testcase3.checkAvailability(1,9,size,testcase3slot));
		assertEquals(Decline,testcase3.checkAvailability(0,15,size,testcase3slot));
		
	}
	
	/***********************TEST CASE 4: Requests can be accepted after a decline ****************************/
	@Test
	public final void testcase4CheckAvailability() {
		
		RoomBooking testcase4 = new RoomBooking();
		size = 3;
		int [][]testcase4slot = new int[30][30];
		assertEquals(Accept,testcase4.checkAvailability(1,3,size,testcase4slot));
		assertEquals(Accept,testcase4.checkAvailability(0,15,size,testcase4slot));
		assertEquals(Accept,testcase4.checkAvailability(1,9,size,testcase4slot));
		assertEquals(Decline,testcase4.checkAvailability(2,5,size,testcase4slot));
		assertEquals(Accept,testcase4.checkAvailability(4,9,size,testcase4slot));
		
	}
	
	/***********************TEST CASE 5: Complex Requests **************************************************/
	@Test
	public final void testcase5CheckAvailability() {

		RoomBooking testcase5 = new RoomBooking();
		size = 2;
		int [][]testcase5slot = new int[30][30];
		assertEquals(Accept,testcase5.checkAvailability(1,3,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(0,4,size,testcase5slot));
		assertEquals(Decline,testcase5.checkAvailability(2,3,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(5,5,size,testcase5slot));
		assertEquals(Decline,testcase5.checkAvailability(4,10,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(10,10,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(6,7,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(8,10,size,testcase5slot));
		assertEquals(Accept,testcase5.checkAvailability(8,9,size,testcase5slot));
		
	}
	/***********************TEST CASE 6: Requests with start day after endday********************************/
	@Test
	public final void testcase6CheckAvailability() {
		
		RoomBooking testcase6 = new RoomBooking();
		size = 2;
		int [][]G = new int[30][30];
		assertEquals(Decline,testcase6.checkAvailability(5,3,size,G));
		
	}


}
