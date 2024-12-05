package lk.ijse.gdse68.crop_monitoring_backend.Repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.gdse68.crop_monitoring_backend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    boolean existsByEmail(@NotBlank @Email String email);
}
