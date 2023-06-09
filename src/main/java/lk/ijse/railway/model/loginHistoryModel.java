package lk.ijse.railway.model;

import lk.ijse.railway.dto.Employee;
import lk.ijse.railway.dto.LoginHistory;
import lk.ijse.railway.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class loginHistoryModel {
    public static boolean save(int uid, Date date, Time time1) throws SQLException {

        {
            String sql = "INSERT INTO LogHistory(UserID, LogInDate, LogInTime) " +
                    "VALUES(?,?,?)";

            return CrudUtil.execute(
                    sql,
                    uid,date,time1);

        }

    }

    public static LoginHistory searchLastID() throws SQLException {
        String sql = "select *from LogHistory ORDER BY LogInTime DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);


        if (rst.next()){
            return new LoginHistory(rst.getInt(1),rst.getDate(2),rst.getTime(3),rst.getDate(4),rst.getTime(5));
        }
        return null;

    }

    public static List<LoginHistory> getAll() throws SQLException {

        List<LoginHistory> data = new ArrayList<>();

        String sql = "SELECT * FROM LogHistory";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            data.add(new LoginHistory(
                    resultSet.getInt(1),
                    resultSet.getDate(2),
                    resultSet.getTime(3),
                    resultSet.getDate(4),
                    resultSet.getTime(5)
            ));
        }
        return data;

    }

    public static boolean saveLogOut(int uId, Date date, Time time1) throws SQLException {
        {
            String sql = "INSERT INTO LogHistory(UserID, LogOutDate, LogOutTime) " +
                    "VALUES(?,?,?)";

            return CrudUtil.execute(
                    sql,
                    uId,date,time1);

        }


    }
}
