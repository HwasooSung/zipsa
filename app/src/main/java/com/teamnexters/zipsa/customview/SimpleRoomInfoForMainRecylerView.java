package com.teamnexters.zipsa;

/**
 * Created by Hwasoo.Sung on 2017-02-06.
 */

public class SimpleRoomInfoForMainRecylerView {
    private String address;
    private String kindOfRoom;
    private String priceOfRoom;

    SimpleRoomInfoForMainRecylerView(Room room) {
        address = room.getAddress();
        kindOfRoom = room.getRoomType();
        priceOfRoom = room.getRoomPrice();
    }
}
