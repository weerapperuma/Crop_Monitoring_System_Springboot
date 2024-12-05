package lk.ijse.gdse68.crop_monitoring_backend.customObj.errorResponse;

import lk.ijse.gdse68.crop_monitoring_backend.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserErrorResponse implements UserResponse , Serializable {
    private int errorCode;
    private String errorMessage;
}