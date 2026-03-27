package tech.lander.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Details of the vehicle entity.")
@Document(collection = "vehicle")
public class Vehicle {

    @Id
    private String id;
    private String make;
    private String plate;
    private String model;
    private int mileage;
    private int yearPurchased;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYearPurchased() {
        return yearPurchased;
    }

    public void setYearPurchased(int yearPurchased) {
        this.yearPurchased = yearPurchased;
    }
}
