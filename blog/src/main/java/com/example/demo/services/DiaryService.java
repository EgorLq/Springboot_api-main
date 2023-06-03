package com.example.demo.services;

import com.example.demo.dto.DiaryDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.User;
import com.example.demo.repository.DiaryRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryDTO createDiary(DiaryDTO diaryDTO) {
        Diary diary = convertToDiary(diaryDTO);
        Diary savedDiary = diaryRepository.save(diary);
        return convertToDiaryDTO(savedDiary);
    }

    public List<DiaryDTO> createDiaryList(List<DiaryDTO> diaryDTOList) {
        List<Diary> diaryList = diaryDTOList.stream()
                .map(this::convertToDiary)
                .collect(Collectors.toList());
        List<Diary> savedDiaryList = diaryRepository.saveAll(diaryList);
        return savedDiaryList.stream()
                .map(this::convertToDiaryDTO)
                .collect(Collectors.toList());
    }

    public List<DiaryDTO> getDiaryList() {
        List<Diary> diaryList = diaryRepository.findAll();
        return diaryList.stream()
                .map(this::convertToDiaryDTO)
                .collect(Collectors.toList());
    }

    public DiaryDTO getDiaryById(int id) throws NotFoundException {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Diary not found with id: " + id));
        return convertToDiaryDTO(diary);
    }

    @Transactional
    public DiaryDTO updateDiaryById(DiaryDTO diaryDTO) throws NotFoundException {
        Diary diary = diaryRepository.findById(diaryDTO.getId())
                .orElseThrow(() -> new NotFoundException("Diary not found with id: " + diaryDTO.getId()));

        diary.setTimes(diaryDTO.getTimes());
        diary.setCases(diaryDTO.getCases());

        Diary updatedDiary = diaryRepository.save(diary);
        return convertToDiaryDTO(updatedDiary);
    }

    public void deleteDiaryById(int id) {
        diaryRepository.deleteById(id);
    }

    private Diary convertToDiary(DiaryDTO diaryDTO) {
        Diary diary = new Diary();
        diary.setId(diaryDTO.getId());
        diary.setTimes(diaryDTO.getTimes());
        diary.setCases(diaryDTO.getCases());
        diary.setUser(convertToUser(diaryDTO.getUser()));
        return diary;
    }

    private DiaryDTO convertToDiaryDTO(Diary diary) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setId(diary.getId());
        diaryDTO.setTimes(diary.getTimes());
        diaryDTO.setCases(diary.getCases());
        diaryDTO.setUser(convertToUserDTO(diary.getUser()));
        return diaryDTO;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId((long) userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Math.toIntExact(user.getId()));
        userDTO.setFullName(user.getFullName());
        userDTO.setRole(user.getRole());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
