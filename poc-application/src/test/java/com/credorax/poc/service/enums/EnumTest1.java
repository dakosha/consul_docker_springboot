package com.credorax.poc.service.enums;

import org.junit.Test;

public class EnumTest1 {

    @Test
    public void test1() {
        String code1 = "3ds_shipnameindicator";
        EPowerParameter parameter = EPowerParameter.valueOfCode(code1);

        assert parameter == EPowerParameter.THREEDS_SHIP_NAME_IND;
    }

}
