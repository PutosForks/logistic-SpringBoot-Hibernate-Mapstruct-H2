package com.logistic.task.dto;

import com.logistic.task.entity.Address;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */

@Data
public class ClientDto {

    private long id;
    @Size(max = 255)
    private String Name;
    @Size(max = 25)
    private String PhoneNumber;
    private List<Address> address;
}
