package com.example.demo.services;

import com.example.demo.dto.DiaryDTO;
import javassist.NotFoundException;

import java.util.List;

public interface DiaryService {

  DiaryDTO createDiary(DiaryDTO diaryDTO);

  List<DiaryDTO> createDiaryList(List<DiaryDTO> diaryDTOList);

  List<DiaryDTO> getDiaryList();

  DiaryDTO getDiaryById(int id) throws NotFoundException;

  DiaryDTO updateDiaryById(DiaryDTO diaryDTO) throws NotFoundException;

  void deleteDiaryById(int id);
}
