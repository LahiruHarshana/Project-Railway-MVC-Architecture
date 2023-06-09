package lk.ijse.railway.model;


import lk.ijse.railway.db.DBConnection;
import lk.ijse.railway.util.CrudUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bookingModel {


    public static boolean paymentBooking(String bookingId, String date, Date toDate, String trainId, String stationName, String seatsId,String paymentId, double price, String passengerId, String passengerName, String contactNum, String email, String address, String nic) throws SQLException, SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isBookingSaved = bookingModel.save(bookingId,date,toDate,trainId,stationName,seatsId,price);
            if(isBookingSaved) {
                boolean isPassengerSaved = PassengerModel.save(passengerId,passengerName,bookingId,contactNum,email,address,nic);

                if(isPassengerSaved) {
                    boolean isPaymentSaved = PaymentModel.saveB(paymentId,bookingId,date,price);

                    if(isPaymentSaved){
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }

    private static boolean save(String bookingId, String date, Date toDate, String trainId, String stationName, String SeatsId, Double price) throws SQLException {


        String sql = "INSERT INTO Booking(BookingID,Date,ToDate,TrainID,StationName,SeatsID,price) VALUES(?,?,?,?,?,?,?)";


        return CrudUtil.execute(
                sql,
                bookingId,
                date,
                toDate,
                trainId,
                stationName,
                SeatsId,
                price);

    }

}
