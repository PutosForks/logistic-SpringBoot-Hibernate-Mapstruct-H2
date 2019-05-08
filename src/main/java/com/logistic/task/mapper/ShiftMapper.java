package com.logistic.task.mapper;

import com.logistic.task.dto.ShiftDto;
import com.logistic.task.entity.Shift;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This class is developed by Ivanov Alexey (mrSlilex@gmail.com) on 07.05.2019
 */
@Mapper
public interface ShiftMapper extends BaseMapper<ShiftDto, Shift> {
    @Override
    ShiftDto toDto(Shift shift);

    @Override
    List<ShiftDto> toDto(List<Shift> shifts);

    @Override
    Shift toEntity(ShiftDto shiftDto);

    @Override
    List<Shift> toEntity(List<ShiftDto> shiftDtos);
}
