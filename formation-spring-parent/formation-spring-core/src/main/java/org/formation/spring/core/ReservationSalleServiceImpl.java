package org.formation.spring.core;

public class ReservationSalleServiceImpl implements ReservationSalleService {

	private ReservationSalleDao reservationSalleDao;

	public ReservationSalleServiceImpl(ReservationSalleDao reservationSalleDao) {
		this.reservationSalleDao = reservationSalleDao;
	}

	public void reserver(ReservationSalle reservationSalle) {
		// faire un traitement nécessaire
		// (par exemple la validation de la réservation)

		// sauvegarder la réservation
		reservationSalleDao.sauver(reservationSalle);
	}

}
