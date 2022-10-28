package com.example.codereader;

public class ReceiveCustDetails {
    String Customer_Code,Customer_name,Customer_Number,Plant_Name,Date_planted,Lastly_Watered,Lastly_sprayed;

    public ReceiveCustDetails(String customer_Code, String customer_name, String customer_Number, String plant_Name, String date_planted, String lastly_Watered, String lastly_sprayed) {
        Customer_Code = customer_Code;
        Customer_name = customer_name;
        Customer_Number = customer_Number;
        Plant_Name = plant_Name;
        Date_planted = date_planted;
        Lastly_Watered = lastly_Watered;
        Lastly_sprayed = lastly_sprayed;
    }

    public ReceiveCustDetails() {
    }

    public String getCustomer_Code() {
        return Customer_Code;
    }

    public void setCustomer_Code(String customer_Code) {
        Customer_Code = customer_Code;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getCustomer_Number() {
        return Customer_Number;
    }

    public void setCustomer_Number(String customer_Number) {
        Customer_Number = customer_Number;
    }

    public String getPlant_Name() {
        return Plant_Name;
    }

    public void setPlant_Name(String plant_Name) {
        Plant_Name = plant_Name;
    }

    public String getDate_planted() {
        return Date_planted;
    }

    public void setDate_planted(String date_planted) {
        Date_planted = date_planted;
    }

    public String getLastly_Watered() {
        return Lastly_Watered;
    }

    public void setLastly_Watered(String lastly_Watered) {
        Lastly_Watered = lastly_Watered;
    }

    public String getLastly_sprayed() {
        return Lastly_sprayed;
    }

    public void setLastly_sprayed(String lastly_sprayed) {
        Lastly_sprayed = lastly_sprayed;
    }
}
