//package com.sharecom.sharecom_be;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sharecom.sharecom_be.domain.parts.PatchPartsDto;
//import org.junit.jupiter.api.Test;
//import org.openapitools.jackson.nullable.JsonNullable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//
//@SpringBootTest
//public class JacksonConfigTest {
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Test
//    void should_use_json_nullable_module() throws JsonProcessingException {
//        assertEquals(JsonNullable.of("S2EURAIL"),
//                mapper.readValue("{\"serial\":\"S2EURAIL\"}",
//                        PatchPartsDto.class).getSerial());
//
//        assertEquals(JsonNullable.of(null),
//                mapper.readValue("{\"serial\":null}",
//                        PatchPartsDto.class).getSerial());
//
//        assertNull(mapper.readValue("{}", PatchPartsDto.class).getSerial());
//    }
//}
