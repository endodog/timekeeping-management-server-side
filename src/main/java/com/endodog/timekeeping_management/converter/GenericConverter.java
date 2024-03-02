package com.endodog.timekeeping_management.converter;

public interface GenericConverter<D, E> {
  D convertToDto(E entity);

  E convertToEntity(D response);

//  Page<D> convertToPageResponse(Page<E> entityPage);
//
//  Page<E> convertToPageEntity(Page<D> responsePage);
//
  List<D> convertToListResponse(List<E> entityList);

  List<E> convertToListEntity(List<D> responseList);

  List<E> convertListRequestToListEntity(List<R> requestList);
}
