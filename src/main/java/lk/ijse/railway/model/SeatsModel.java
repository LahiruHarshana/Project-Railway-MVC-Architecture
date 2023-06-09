package lk.ijse.railway.model;

import lk.ijse.railway.db.DBConnection;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatsModel {
    public static List<String> loadIds(String trainId, Date date) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT SeatsID FROM Booking WHERE TrainID = ? AND ToDate =?");
        pstm.setString(1, trainId);
        pstm.setDate(2, date);

        ResultSet resultSet = pstm.executeQuery();

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
