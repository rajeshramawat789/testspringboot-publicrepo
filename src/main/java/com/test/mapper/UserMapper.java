package com.test.mapper;

import com.test.dto.request.UserRequestDto;
import com.test.entities.UserAccounts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends GenericMapper<UserAccounts, UserRequestDto>{


}
