package com.example.demo.mapper;

import com.example.demo.DTO.DiaryDTO;
import com.example.demo.entity.Diary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiaryConvertor {
 private final ModelMapper modelMapper;

 public DiaryConvertor() {
  this.modelMapper = new ModelMapper();
 }


 public DiaryDTO convertToDto(Diary entity){
  return modelMapper.map(entity, DiaryDTO.class);
 }
}