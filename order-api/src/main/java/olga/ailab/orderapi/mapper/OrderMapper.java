package olga.ailab.orderapi.mapper;

import olga.ailab.orderapi.dto.OrderDTO;
import olga.ailab.orderapi.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderEntity toEntity(OrderDTO dto);

    OrderDTO toDto(OrderEntity entity);
} 