package com.iem.tfm.infrastructure.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.iem.tfm.domain.model.EmployeeRoleEnum;
import com.iem.tfm.domain.model.User;
import com.iem.tfm.infrastructure.apirest.dto.response.LoginResponseDto;
import com.iem.tfm.infrastructure.apirest.dto.response.UserResponseDto;

/**
 * Mapeador para convertir entre objetos del dominio {@link User} y DTOs de
 * respuesta {@link UserResponseDto}.
 * <p>
 * Esta interfaz utiliza MapStruct para automatizar la conversión de objetos
 * entre las capas de dominio e infraestructura, facilitando el paso de datos al
 * cliente sin exponer directamente la lógica interna del dominio.
 * </p>
 * 
 * @author Inigo
 * @version 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

	/**
	 * Convierte un objeto del dominio User en un DTO de respuesta UserResponseDto.
	 * 
	 * @param user objeto de dominio a convertir
	 * @return DTO equivalente para respuesta en la API
	 */
	public UserResponseDto toDto(User user);

	/**
	 * Convierte una lista de objetos del dominio User en una lista de DTOs de
	 * respuesta UserResponseDto.
	 * 
	 * @param users lista de objetos de dominio
	 * @return lista de DTOs de respuesta
	 */
	public List<UserResponseDto> toDtoList(List<User> users);

	/**
	 * Convierte un {@link User} en un {@link UserResponseDto} especializado para
	 * autenticación.
	 * 
	 * Preguntar a Cliff la mejor manera de haber hecho esto.
	 *
	 * @param user         objeto del dominio
	 * @param employeeRole rol del empleado vinculado al usuario
	 * @return DTO con información necesaria para la sesión iniciada
	 */
	default UserResponseDto toLoginDto(User user, EmployeeRoleEnum employeeRole) {
		return UserResponseDto.builder().id(user.getId()).username(user.getUsername()).role(user.getRole())
				.userActive(user.isUserActive()).employeeId(user.getEmployeeId()).employeeRole(employeeRole).build();
	}

	/**
	 * Convierte un {@link User} en un {@link LoginResponseDto} completo, incluyendo
	 * información del usuario, su rol y departamentos asociados.
	 * <p>
	 * Esta variante es útil para construir la respuesta completa al iniciar sesión
	 * y poblar vistas dinámicas según el departamento y rol.
	 * </p>
	 * 
	 * Preguntar a Cliff la mejor manera de haber hecho esto.
	 *
	 * @param user            objeto de dominio
	 * @param employeeRole    rol del empleado
	 * @param departmentIds   lista de IDs de departamentos asociados
	 * @param departmentNames lista de nombres de departamentos asociados
	 * @return DTO con toda la información para login y vista contextual
	 */
	default LoginResponseDto toLoginDtoLogin(User user, EmployeeRoleEnum employeeRole, List<String> departmentIds,
			List<String> departmentNames) {
		return LoginResponseDto.builder().userId(user.getId()).username(user.getUsername()).role(user.getRole())
				.userActive(user.isUserActive()).employeeId(user.getEmployeeId()).employeeRole(employeeRole)
				.departmentIds(departmentIds).departmentNames(departmentNames).build();
	}
}