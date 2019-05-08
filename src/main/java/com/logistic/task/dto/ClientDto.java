package com.logistic.task.dto;

import com.logistic.task.entity.Address;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientDto {

    private long id;
    @Size(max = 255)
    private String Name;
    @Size(max = 25)
    private String PhoneNumber;
    private Address address;
}
