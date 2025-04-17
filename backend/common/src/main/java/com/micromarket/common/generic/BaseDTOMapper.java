package com.micromarket.common.generic;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface BaseDTOMapper<DM extends ExtendedEntity, DTO extends BaseDTO> {

    DM fromDTO(DTO dto);

    List<DM> fromDTOs(List<DTO> dtos);

    Set<DM> fromDTOs(Set<DTO> dtos);

    Collection<DM> fromDTOs(Collection<DTO> dtos);

    DTO toDTO(DM dm);

    List<DTO> toDTOs(List<DM> dms);

    Set<DTO> toDTOs(Set<DM> dms);

    Collection<DTO> toDTOs(Collection<DM> dms);
}
