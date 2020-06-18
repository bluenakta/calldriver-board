package calldriver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Board_table")
public class Board {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long callId;
        private String payStatus;
        private Long driverId;
        private String driverStatus;
        private String callStatus;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getCallId() {
            return callId;
        }

        public void setCallId(Long callId) {
            this.callId = callId;
        }
        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }
        public String getDriverStatus() {
            return driverStatus;
        }

        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }
        public String getCallStatus() {
            return callStatus;
        }

        public void setCallStatus(String callStatus) {
            this.callStatus = callStatus;
        }

}
