package com.example.chat.common;

public interface EntityMapper {
    interface DtoToEntity<E, req> {
        E convert(req dto);
    }
    interface EntityToDto<E, Dto> {
        Dto convert(E entity);
    }
}



