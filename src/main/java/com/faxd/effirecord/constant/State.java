package com.faxd.effirecord.constant;

public enum State {
    OK(200),
    ERR_BAD_REQUEST(400),
    ERR_CATEGORY_NAME_DUPLICATE(401),//client -- category --  name -- duplicate
    ERR_CATEGORY_NOT_FOUND(40101),//client -- category -  not found
    ERR_JWT_EXPIRED(40901),//client -- LOGIN -  expired data
    ERR_JWT_MALFORMED(40902),//client -- login -  invalid format data
    ERR_JWT_SIGNATURE(40903),//client -- login -  wrong signature
    ERR_INSERT(50000), // service -- insert error
    ERR_UPDATE(50001),//service --  update error
    ERR_INTERNAL_SERVER(500);//service --  update error


    private Integer value;

    private State(Integer value){
        this.value=value;
    }

    public Integer getValue(){
        return value;
    }
}
