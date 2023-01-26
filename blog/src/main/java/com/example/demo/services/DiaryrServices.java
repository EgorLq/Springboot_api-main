package com.example.demo.services;

import com.example.demo.entity.Diary;
import com.example.demo.repository.DiaryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryrServices {


    private final DiaryRepository diaryRepository;


    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    public List<Diary> createDiaryList(List<Diary> list) {
        return diaryRepository.saveAll(list);
    }

    public List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public Diary getDiaryById(int id) {

        return diaryRepository.findById(id).orElseThrow();
    }

    @Transactional


    public Diary updateDiaryById(Diary diary) {
        Optional<Diary> userFound = diaryRepository.findById(diary.getId());

        userFound.ifPresent(diary1 -> {
            diary1.setUsers(diary.getUsers());
            diary1.setTimes(diary.getTimes());
            diary1.setCases(diary.getCases());

            diaryRepository.save(diary1);
        });
        return diaryRepository.findById(diary.getId()).orElseThrow();
    }

    public void deleteDiaryById(int id) {
        diaryRepository.deleteById(id);
    }
}
