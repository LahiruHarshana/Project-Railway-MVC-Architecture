package lk.ijse.railway.dto;

import javafx.scene.layout.Pane;

import java.sql.Date;
import java.sql.Time;

import lombok.*;
@ToString
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

public class LoginHistory {
    private int uId;
    private Date logInDate;
    private Time logInTime;
    private Date logOutDate;

    private Time logOutTime;
}
