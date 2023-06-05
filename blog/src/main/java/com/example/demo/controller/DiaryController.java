package com.example.demo.controller;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.services.DiaryService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diaries")
public class DiaryController {

  private final DiaryService diaryService;

  @ApiOperation("Создать новый дневник")
  @PostMapping
  public ResponseEntity<DiaryDTO> createDiary(@RequestBody DiaryDTO diaryDTO) {
    DiaryDTO createdDiary = diaryService.createDiary(diaryDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdDiary);
  }

  @ApiOperation("Создать пак дневников")
  @PostMapping("/batch")
  public ResponseEntity<List<DiaryDTO>> createDiaryList(@RequestBody List<DiaryDTO> diaryDTOList) {
    List<DiaryDTO> createdDiaryList = diaryService.createDiaryList(diaryDTOList);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdDiaryList);
  }

  @ApiOperation("Получить список всех дневников")
  @GetMapping
  public ResponseEntity<List<DiaryDTO>> getDiaryList() {
    List<DiaryDTO> diaryList = diaryService.getDiaryList();
    return ResponseEntity.ok(diaryList);
  }

  @ApiOperation("Получить дневник по ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Дневник успешно получен"),
        @ApiResponse(code = 404, message = "Дневник не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  @GetMapping("/{id}")
  public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable Long id) throws NotFoundException {
    DiaryDTO diaryDTO = diaryService.getDiaryById(id);
    return ResponseEntity.ok(diaryDTO);
  }

  @ApiOperation("Обновить дневник по ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Дневник успешно обновлен"),
        @ApiResponse(code = 404, message = "Дневник не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  @PutMapping("/{id}")
  public ResponseEntity<DiaryDTO> updateDiaryById(
      @PathVariable Long id, @RequestBody DiaryDTO diaryDTO) throws NotFoundException {
    diaryDTO.setId(id);
    DiaryDTO updatedDiary = diaryService.updateDiaryById(diaryDTO);
    return ResponseEntity.ok(updatedDiary);
  }

  @ApiOperation("Удалить дневник по ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Дневник успешно удален"),
        @ApiResponse(code = 404, message = "Дневник не найден"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDiaryById(@PathVariable Long id) throws NotFoundException {
    diaryService.deleteDiaryById(id);
    return ResponseEntity.noContent().build();
  }
}
