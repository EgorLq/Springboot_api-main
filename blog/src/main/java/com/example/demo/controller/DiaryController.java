package com.example.demo.controller;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.services.impl.DiaryServiceImpl;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryServiceImpl diaryServiceImpl;

    public DiaryController(DiaryServiceImpl diaryServiceImpl) {
        this.diaryServiceImpl = diaryServiceImpl;
    }

    @ApiOperation("Создать новый дневник")
    @PostMapping
    public ResponseEntity<DiaryDTO> createDiary(@RequestBody DiaryDTO diaryDTO) {
        DiaryDTO createdDiary = diaryServiceImpl.createDiary(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiary);
    }

    @ApiOperation("Создать пакет дневников")
    @PostMapping("/batch")
    public ResponseEntity<List<DiaryDTO>> createDiaryList(@RequestBody List<DiaryDTO> diaryDTOList) {
        List<DiaryDTO> createdDiaryList = diaryServiceImpl.createDiaryList(diaryDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiaryList);
    }

    @ApiOperation("Получить список всех дневников")
    @GetMapping
    public ResponseEntity<List<DiaryDTO>> getDiaryList() {
        List<DiaryDTO> diaryList = diaryServiceImpl.getDiaryList();
        return ResponseEntity.ok(diaryList);
    }

    @ApiOperation("Получить дневник по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дневник успешно получен"),
            @ApiResponse(code = 404, message = "Дневник не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable int id) {
        try {
            DiaryDTO diaryDTO = diaryServiceImpl.getDiaryById(id);
            return ResponseEntity.ok(diaryDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Обновить дневник по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Дневник успешно обновлен"),
            @ApiResponse(code = 404, message = "Дневник не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DiaryDTO> updateDiaryById(@PathVariable int id, @RequestBody DiaryDTO diaryDTO) {
        diaryDTO.setId((long) id);
        try {
            DiaryDTO updatedDiary = diaryServiceImpl.updateDiaryById(diaryDTO);
            return ResponseEntity.ok(updatedDiary);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Удалить дневник по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Дневник успешно удален"),
            @ApiResponse(code = 404, message = "Дневник не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaryById(@PathVariable int id) {
        try {
            diaryServiceImpl.deleteDiaryById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
