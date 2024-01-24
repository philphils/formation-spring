package org.formation.spring.core;

public class ReservationSalleDaoLocator {

	private static ReservationSalleDao reservationSalleDao;

	public static ReservationSalleDao get() {

		if (reservationSalleDao != null) {
			reservationSalleDao = new ReservationSalleDaoImpl();
		}

		return reservationSalleDao;
	}

}
