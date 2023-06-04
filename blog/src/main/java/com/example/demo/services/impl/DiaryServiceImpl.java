package com.example.demo.services.impl;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.entity.Diary;
import com.example.demo.maper.DiaryMapper;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.services.DiaryService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryServiceImpl implements DiaryService {

  private final DiaryRepository diaryRepository;
  private final DiaryMapper diaryMapper;
  /**
   * Создает новый дневник.
   *
   * @param diaryDTO данные нового дневника
   * @return созданный дневник
   */
  @Override
  @Transactional
  public DiaryDTO createDiary(DiaryDTO diaryDTO) {
    Diary diary = diaryMapper.diaryDTOToDiary(diaryDTO);
    Diary savedDiary = diaryRepository.save(diary);
    return diaryMapper.diaryToDiaryDTO(savedDiary);
  }
  /**
   * Создает список новых дневников.
   *
   * @param diaryDTOList список данных новых дневников
   * @return список созданных дневников
   */
  @Override
  @Transactional
  public List<DiaryDTO> createDiaryList(List<DiaryDTO> diaryDTOList) {
    List<Diary> diaryList =
        diaryDTOList.stream().map(diaryMapper::diaryDTOToDiary).collect(Collectors.toList());
    List<Diary> savedDiaryList = diaryRepository.saveAll(diaryList);
    return savedDiaryList.stream().map(diaryMapper::diaryToDiaryDTO).collect(Collectors.toList());
  }
  /**
   * Получает список всех дневников.
   *
   * @return список всех дневников
   */
  @Override
  @Transactional(readOnly = true)
  public List<DiaryDTO> getDiaryList() {
    List<Diary> diaryList = diaryRepository.findAll();
    return diaryList.stream().map(diaryMapper::diaryToDiaryDTO).collect(Collectors.toList());
  }
  /**
   * Получает дневник по его идентификатору.
   *
   * @param id идентификатор дневника
   * @return найденный дневник
   * @throws NotFoundException если дневник не найден
   */
  @Override
  @Transactional(readOnly = true)
  public DiaryDTO getDiaryById(int id) throws NotFoundException {
    Diary diary =
        diaryRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Diary not found with id: " + id));
    return diaryMapper.diaryToDiaryDTO(diary);
  }
  /**
   * Обновляет дневник по его идентификатору.
   *
   * @param diaryDTO новые данные для обновления дневника
   * @return обновленный дневник
   * @throws NotFoundException если дневник не найден
   */
  @Override
  @Transactional
  public DiaryDTO updateDiaryById(DiaryDTO diaryDTO) throws NotFoundException {
    Diary diary =
        diaryRepository
            .findById(Math.toIntExact(diaryDTO.getId()))
            .orElseThrow(
                () -> new NotFoundException("Diary not found with id: " + diaryDTO.getId()));

    diary.setTimes(diaryDTO.getTimes());
    diary.setCases(diaryDTO.getCases());

    Diary updatedDiary = diaryRepository.save(diary);
    return diaryMapper.diaryToDiaryDTO(updatedDiary);
  }

  /**
   * Удаляет дневник по его идентификатору.
   *
   * @param id идентификатор дневника
   */
  @Override
  public void deleteDiaryById(int id) {
    diaryRepository.deleteById(id);
  }
}
