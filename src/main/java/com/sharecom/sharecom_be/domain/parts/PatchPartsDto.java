package com.sharecom.sharecom_be.domain.parts;

import lombok.*;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchPartsDto {
//    private int id;

//    private JsonNullable<String> serial = JsonNullable.undefined();
//    private JsonNullable<String> name;
//    private JsonNullable<String> etc;
    private String type;
    private String serial;
    private String name;
    private String etc;
    private String buyAt;

//    private LocalDate buyAt;
//    private boolean usedYn;
//    private LocalDate usedAt;
//    private Parts.Type type;

}
