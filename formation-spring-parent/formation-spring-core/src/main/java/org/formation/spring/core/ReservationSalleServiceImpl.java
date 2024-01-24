package org.formation.spring.core;

public class ReservationSalleServiceImpl implements ReservationSalleService {

	private ReservationSalleDao reservationSalleDao;

	public ReservationSalleServiceImpl(ReservationSalleDao reservationSalleDao) {
		this.reservationSalleDao = reservationSalleDao;
	}

	public void reserver(ReservationSalle reservationSalle) {
		// Traitement nécessaire à la réservation
		// par exemple vérifier la disponibilité etc.
		// Sauvegarde de la réservation
		reservationSalleDao.sauver(reservationSalle);
	}

}
