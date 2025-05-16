package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.iem.tfm.application.command.EmployeeRegisterCommand;
import com.iem.tfm.domain.model.Employee;
import com.iem.tfm.infrastructure.apirest.dto.request.EmployeeRequestDto;
import com.iem.tfm.infrastructure.apirest.dto.response.DepartmentResponseDto;
import com.iem.tfm.infrastructure.apirest.dto.response.EmployeeResponseDto;

/**
 * Mapper de la capa de infraestructura para convertir DTO a Command.
 * 
 * Transforma un {@link EmployeeRequestDto} a un {@link EmployeeRegisterCommand}.
 * 
 * Se implementa utilizando MapStruct.
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EmployeeDtoMapper {

    @Autowired
    protected DepartmentDtoMapper departmentDtoMapper;

    public abstract EmployeeRegisterCommand fromDtoToCommand(EmployeeRequestDto dto);

    public abstract List<EmployeeResponseDto> fromDomainToDtoList(List<Employee> employees);

    public EmployeeResponseDto fromDomainToDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSurname(employee.getSurname());
        dto.setDni(employee.getDni());
        dto.setAge(employee.getAge());
        dto.setEmail(employee.getEmail());
        dto.setStartDate(employee.getStartDate());
        dto.setEndDate(employee.getEndDate());
        dto.setRole(employee.getRole());

        List<DepartmentResponseDto> departmentDtos = departmentDtoMapper.toDtoList(employee.getDepartments());
        dto.setDepartments(departmentDtos);

        return dto;
    }
}