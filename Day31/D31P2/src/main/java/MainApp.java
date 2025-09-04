
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("=== Showcasing 1:1 Unidirectional ===");
        Passport passport = Passport.builder().passportNumber("P12345").nationality("Indian").build();
        Passenger passenger = Passenger.builder().passportId("PID001").name("John Doe").age(30).passportUni(passport).build();
        session.save(passport);
        session.save(passenger);

        System.out.println("=== Showcasing 1:1 Bidirectional ===");
        Passport passportBi = Passport.builder().passportNumber("P54321").nationality("USA").build();
        Passenger passengerBi = Passenger.builder().passportId("PID002").name("Jane Roe").age(28).build();
        passportBi.setPassengerBi(passengerBi);
        passengerBi.setPassportBi(passportBi);
        session.save(passportBi);
        session.save(passengerBi);

        System.out.println("=== Showcasing 1:M Unidirectional ===");
        Airline airline = Airline.builder().airlineName("SkyJet Airways").build();
        Flight flight = Flight.builder().flightNumber("SJ101").airlineUni(airline).build();
        airline.getFlightsUni().add(flight);
        session.save(airline);
        session.save(flight);

        System.out.println("=== Showcasing 1:M Bidirectional ===");
        Airline airlineBi = Airline.builder().airlineName("Indigo").build();
        Flight flightBi = Flight.builder().flightNumber("IN202").airlineBi(airlineBi).build();
        airlineBi.getFlightsBi().add(flightBi);
        session.save(airlineBi);
        session.save(flightBi);

        System.out.println("=== Showcasing M:1 Unidirectional ===");
        Booking bookingUni = Booking.builder().seatNumber("12A").flightUni(flight).build();
        session.save(bookingUni);

        System.out.println("=== Showcasing M:1 Bidirectional ===");
        Booking bookingBi = Booking.builder().seatNumber("15B").passengerBi(passenger).build();
        passenger.getBookingsBi().add(bookingBi);
        session.save(bookingBi);

        System.out.println("=== Showcasing M:M Unidirectional === (via Flight owning side)");
        Flight mmFlight = Flight.builder().flightNumber("MM303").build();
        Passenger mmPassenger = Passenger.builder().passportId("PID003").name("Alice").age(26).build();
        mmFlight.getPassengers().add(mmPassenger);
        mmPassenger.getFlights().add(mmFlight);
        session.save(mmPassenger);
        session.save(mmFlight);

        System.out.println("=== Showcasing M:M Bidirectional ===");
        Flight mmFlight2 = Flight.builder().flightNumber("MM404").build();
        Passenger mmPassenger2 = Passenger.builder().passportId("PID004").name("Bob").age(32).build();
        mmFlight2.getPassengers().add(mmPassenger2);
        mmPassenger2.getFlights().add(mmFlight2);
        session.save(mmPassenger2);
        session.save(mmFlight2);

        tx.commit();
        session.close();
        System.out.println("=== All mappings executed successfully ===");
    }
}
