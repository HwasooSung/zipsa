package com.teamnexters.zipsa;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Hwasoo.Sung on 2016-08-19.
 */
public class Room extends RealmObject {

    /* id */
    @PrimaryKey
    private long roomId;

    /* 북마크 */
    private boolean bookmarked;

    /* 건물 정보 */
    private String address;
    private String floorNumber;
    private String area;
    private boolean elevatorExist;
    private boolean parkingIsPossible;
    private String roomType;

    /* 가격 정보 */
    // 매매
    private boolean buyRoom;
    private String roomPrice;
    // 전세
    private boolean isOnDeposit;
    private String depositMoney;
    //월세
    private boolean isMonthlyRent;
    private String monthlyRent;
    //관리비
    private String managementBill;
    private boolean includeUtilityBill;

    /* 가전 / 가구 옵션 */
    private boolean washingMachine;
    private boolean fridge;
    private boolean microwave;
    private boolean induction;
    private boolean gasStove;
    private boolean airConditioner;
    private boolean sinkOfKitchen;
    private boolean shoeShelf;
    private boolean deskAndChair;
    private boolean closet;
    private boolean drawer;
    private boolean bed;

    /* 방정보 */
    private String roomDimension;
    private boolean doorLock;
    private String sizeOfWindow;
    private String lightLevel;
    private boolean gompang;
    private String noiseLevel;
    private String ceilingFinishLevel;
    private boolean securityGrille;

    /* 화장실 */
    private String cleanessLevel;
    private String hotWaterLevel;
    private boolean sinkInBathrrom;
    private boolean windowInBathroom;
    private boolean shelfInBathroom;

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isElevatorExist() {
        return elevatorExist;
    }

    public void setElevatorExist(boolean elevatorExist) {
        this.elevatorExist = elevatorExist;
    }

    public boolean isParkingIsPossible() {
        return parkingIsPossible;
    }

    public void setParkingIsPossible(boolean parkingIsPossible) {
        this.parkingIsPossible = parkingIsPossible;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isBuyRoom() {
        return buyRoom;
    }

    public void setBuyRoom(boolean buyRoom) {
        this.buyRoom = buyRoom;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isOnDeposit() {
        return isOnDeposit;
    }

    public void setOnDeposit(boolean onDeposit) {
        isOnDeposit = onDeposit;
    }

    public String getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(String depositMoney) {
        this.depositMoney = depositMoney;
    }

    public boolean isMonthlyRent() {
        return isMonthlyRent;
    }

    public void setMonthlyRent(boolean monthlyRent) {
        isMonthlyRent = monthlyRent;
    }

    public String getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(String monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getManagementBill() {
        return managementBill;
    }

    public void setManagementBill(String managementBill) {
        this.managementBill = managementBill;
    }

    public boolean isIncludeUtilityBill() {
        return includeUtilityBill;
    }

    public void setIncludeUtilityBill(boolean includeUtilityBill) {
        this.includeUtilityBill = includeUtilityBill;
    }

    public boolean isWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(boolean washingMachine) {
        this.washingMachine = washingMachine;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isMicrowave() {
        return microwave;
    }

    public void setMicrowave(boolean microwave) {
        this.microwave = microwave;
    }

    public boolean isInduction() {
        return induction;
    }

    public void setInduction(boolean induction) {
        this.induction = induction;
    }

    public boolean isGasStove() {
        return gasStove;
    }

    public void setGasStove(boolean gasStove) {
        this.gasStove = gasStove;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public boolean isSinkOfKitchen() {
        return sinkOfKitchen;
    }

    public void setSinkOfKitchen(boolean sinkOfKitchen) {
        this.sinkOfKitchen = sinkOfKitchen;
    }

    public boolean isShoeShelf() {
        return shoeShelf;
    }

    public void setShoeShelf(boolean shoeShelf) {
        this.shoeShelf = shoeShelf;
    }

    public boolean isDeskAndChair() {
        return deskAndChair;
    }

    public void setDeskAndChair(boolean deskAndChair) {
        this.deskAndChair = deskAndChair;
    }

    public boolean isCloset() {
        return closet;
    }

    public void setCloset(boolean closet) {
        this.closet = closet;
    }

    public boolean isDrawer() {
        return drawer;
    }

    public void setDrawer(boolean drawer) {
        this.drawer = drawer;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public String getRoomDimension() {
        return roomDimension;
    }

    public void setRoomDimension(String roomDimension) {
        this.roomDimension = roomDimension;
    }

    public boolean isDoorLock() {
        return doorLock;
    }

    public void setDoorLock(boolean doorLock) {
        this.doorLock = doorLock;
    }

    public String getSizeOfWindow() {
        return sizeOfWindow;
    }

    public void setSizeOfWindow(String sizeOfWindow) {
        this.sizeOfWindow = sizeOfWindow;
    }

    public String getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(String lightLevel) {
        this.lightLevel = lightLevel;
    }

    public boolean isGompang() {
        return gompang;
    }

    public void setGompang(boolean gompang) {
        this.gompang = gompang;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String getCeilingFinishLevel() {
        return ceilingFinishLevel;
    }

    public void setCeilingFinishLevel(String ceilingFinishLevel) {
        this.ceilingFinishLevel = ceilingFinishLevel;
    }

    public boolean isSecurityGrille() {
        return securityGrille;
    }

    public void setSecurityGrille(boolean securityGrille) {
        this.securityGrille = securityGrille;
    }

    public String getCleanessLevel() {
        return cleanessLevel;
    }

    public void setCleanessLevel(String cleanessLevel) {
        this.cleanessLevel = cleanessLevel;
    }

    public String getHotWaterLevel() {
        return hotWaterLevel;
    }

    public void setHotWaterLevel(String hotWaterLevel) {
        this.hotWaterLevel = hotWaterLevel;
    }

    public boolean isSinkInBathrrom() {
        return sinkInBathrrom;
    }

    public void setSinkInBathrrom(boolean sinkInBathrrom) {
        this.sinkInBathrrom = sinkInBathrrom;
    }

    public boolean isWindowInBathroom() {
        return windowInBathroom;
    }

    public void setWindowInBathroom(boolean windowInBathroom) {
        this.windowInBathroom = windowInBathroom;
    }

    public boolean isShelfInBathroom() {
        return shelfInBathroom;
    }

    public void setShelfInBathroom(boolean shelfInBathroom) {
        this.shelfInBathroom = shelfInBathroom;
    }
}