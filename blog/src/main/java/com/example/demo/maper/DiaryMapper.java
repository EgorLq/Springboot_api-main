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
    /**

     Преобразует сущность Diary в объект DiaryDTO.
     @param diary сущность Diary для преобразования
     @return объект DiaryDTO, содержащий данные из сущности Diary
     */
    public DiaryDTO diaryToDiaryDTO(Diary diary) {
        return modelMapper.map(diary, DiaryDTO.class);
    }
    /**

     Преобразует объект DiaryDTO в сущность Diary.
     @param diaryDTO объект DiaryDTO для преобразования
     @return сущность Diary, содержащая данные из объекта DiaryDTO
     */
    public Diary diaryDTOToDiary(DiaryDTO diaryDTO) {
        return modelMapper.map(diaryDTO, Diary.class);
    }
}
