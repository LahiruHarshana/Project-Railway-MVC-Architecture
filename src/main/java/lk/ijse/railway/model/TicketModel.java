package lk.ijse.railway.model;

import lk.ijse.railway.db.DBConnection;
import lk.ijse.railway.dto.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketModel {
    public static List<String> loadTrainIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT TrainID FROM train");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;

    }

    public static int search() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT MAX(TicketID) FROM Ticket");
        int id = 0;


        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;

    }


    public static int search1class(String idl) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT SUM(HowMany) FROM Ticket WHERE ClassType =? AND TrainID = ?");
        pstm.setString(1, "3rdClass");
        pstm.setString(2, idl);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static int search2class(String idl) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT SUM(HowMany) FROM Ticket WHERE TrainID = ? AND ClassType =?");
        pstm.setString(1, idl);
        pstm.setString(2, "2ndClass");

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static int search3class(String idl) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT COUNT(BookingID) FROM Booking WHERE TrainID = ?");
        pstm.setString(1, idl);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
