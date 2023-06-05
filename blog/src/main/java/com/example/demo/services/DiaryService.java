package com.example.demo.services;

import com.example.demo.dto.DiaryDTO;
import javassist.NotFoundException;

import java.util.List;

public interface DiaryService {

  /**
   * Создает новый дневник.
   *
   * @param diaryDTO данные для создания дневника
   * @return созданный дневник
   */
  DiaryDTO createDiary(DiaryDTO diaryDTO);

  /**
   * Создает пакет дневников.
   *
   * @param diaryDTOList список данных для создания дневников
   * @return список созданных дневников
   */
  List<DiaryDTO> createDiaryList(List<DiaryDTO> diaryDTOList);

  /**
   * Возвращает список всех дневников.
   *
   * @return список всех дневников
   */
  List<DiaryDTO> getDiaryList();

  /**
   * Возвращает дневник по указанному идентификатору.
   *
   * @param id идентификатор дневника
   * @return найденный дневник
   * @throws NotFoundException если дневник с указанным идентификатором не найден
   */
  DiaryDTO getDiaryById(Long id) throws NotFoundException;

  /**
   * Обновляет информацию о дневнике.
   *
   * @param diaryDTO данные для обновления дневника
   * @return обновленный дневник
   * @throws NotFoundException если дневник для обновления не найден
   */
  DiaryDTO updateDiaryById(DiaryDTO diaryDTO) throws NotFoundException;

  /**
   * Удаляет дневник по указанному идентификатору.
   *
   * @param id идентификатор дневника для удаления
   */
  void deleteDiaryById(Long id);
}
