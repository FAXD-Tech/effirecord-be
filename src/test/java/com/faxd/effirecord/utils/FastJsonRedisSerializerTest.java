package com.faxd.effirecord.utils;

import com.faxd.effirecord.dto.employee.EmployeePutDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastJsonRedisSerializerTest {


    @Test
    void testSerializeAndDeserialize(){
        FastJsonRedisSerializer<EmployeePutDto> employeeFastJsonRedisSerializer = new FastJsonRedisSerializer<>(EmployeePutDto.class);
        byte[] serialize = employeeFastJsonRedisSerializer.serialize(createEmployeePutDtoSample());

        EmployeePutDto deserialize = employeeFastJsonRedisSerializer.deserialize(serialize);

        assertEquals(deserialize.toString(),createEmployeePutDtoSample().toString());
    }

    private EmployeePutDto createEmployeePutDtoSample() {
        return EmployeePutDto.builder()
                .phone("1234567890")
                .name("john_doe")
                .password("new_password")
                .build();
    }

}
