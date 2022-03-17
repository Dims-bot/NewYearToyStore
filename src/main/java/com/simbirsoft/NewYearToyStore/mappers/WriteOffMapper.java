package com.simbirsoft.NewYearToyStore.mappers;


import com.simbirsoft.NewYearToyStore.models.dtos.WriteOffDto;
import com.simbirsoft.NewYearToyStore.models.entity.WriteOff;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WriteOffMapper {

    @Mapping(source = "created", target = "created", qualifiedByName = "localDateTimeString")
    WriteOffDto entityToDto(WriteOff writeOff, @MappingTarget WriteOffDto writeOffDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "created", target = "created", qualifiedByName = "localDateTime")
    WriteOff dtoToEntity(WriteOffDto writeOffDto, @MappingTarget WriteOff writeOff);

    @Named("localDateTime")
    static LocalDateTime localDateTime(String localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

    @Named("localDateTimeString")
    static String localDateTimeString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toString();
    }


}




