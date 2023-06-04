package com.example.demo.maper;
import com.example.demo.dto.DiaryDTO;
import com.example.demo.entity.Diary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class DiaryMapper {

    private final ModelMapper modelMapper;

    public DiaryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DiaryDTO diaryToDiaryDTO(Diary diary) {
        return modelMapper.map(diary, DiaryDTO.class);
    }

    public Diary diaryDTOToDiary(DiaryDTO diaryDTO) {
        return modelMapper.map(diaryDTO, Diary.class);
    }
}
