package com.example.sun.umstouristsapp.model;

/**
 * Created by sun on 4/8/2017.
 */

public class advertisementModel {
    private String advertisementId;
    private String advertisementDescription;
    private String advertisementImage;
    private String advertisementTitle;

    public advertisementModel(){

    }

    public advertisementModel(String advertisementId, String advertisementDescription, String advertisementTitle, String advertisementImage){
        this.advertisementId = advertisementId;
        this.advertisementDescription = advertisementDescription;
        this.advertisementTitle = advertisementTitle;
        this.advertisementImage = advertisementImage;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getAdvertisementDescription() {
        return advertisementDescription;
    }

    public void setAdvertisementDescription(String advertisementDescription) {
        this.advertisementDescription = advertisementDescription;
    }

    public String getAdvertisementImage() {
        return advertisementImage;
    }

    public void setAdvertisementImage(String advertisementImage) {
        this.advertisementImage = advertisementImage;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle;
    }
}
