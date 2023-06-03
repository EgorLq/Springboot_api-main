package com.example.demo.controller;

import com.example.demo.dto.DiaryDTO;

import com.example.demo.services.DiaryService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<DiaryDTO> createDiary(@RequestBody DiaryDTO diaryDTO) {
        DiaryDTO createdDiary = diaryService.createDiary(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiary);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<DiaryDTO>> createDiaryList(@RequestBody List<DiaryDTO> diaryDTOList) {
        List<DiaryDTO> createdDiaryList = diaryService.createDiaryList(diaryDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiaryList);
    }

    @GetMapping
    public ResponseEntity<List<DiaryDTO>> getDiaryList() {
        List<DiaryDTO> diaryList = diaryService.getDiaryList();
        return ResponseEntity.ok(diaryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable int id) {
        try {
            DiaryDTO diaryDTO = diaryService.getDiaryById(id);
            return ResponseEntity.ok(diaryDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaryDTO> updateDiaryById(@PathVariable int id, @RequestBody DiaryDTO diaryDTO) {
        diaryDTO.setId(id);
        try {
            DiaryDTO updatedDiary = diaryService.updateDiaryById(diaryDTO);
            return ResponseEntity.ok(updatedDiary);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaryById(@PathVariable int id) {
        diaryService.deleteDiaryById(id);
        return ResponseEntity.noContent().build();
    }
}
