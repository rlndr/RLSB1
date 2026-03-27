package tech.lander.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(description = "This is an instance of a maintenance performed")
@Document(collection = "service")
public class Service {

    @Id
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date serviceDate;
    private int serviceMileage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getServiceMileage() {
        return serviceMileage;
    }

    public void setServiceMileage(int serviceMileage) {
        this.serviceMileage = serviceMileage;
    }
}
