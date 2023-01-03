package ru.rozhdestvenskiy.twiwwer.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.twiwwer.domain.api.request.PublicPhraseReq;
import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;
import ru.rozhdestvenskiy.twiwwer.dto.TagDto;
import ru.rozhdestvenskiy.twiwwer.model.Phrase;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhraseMapper {
    PhraseDto publicPhraseReqMapToPhraseDto(PublicPhraseReq publicPhraseReq);
    Phrase PhraseDtoMapToPhrase(PhraseDto phraseDto);

    default String fromTagDto(TagDto tagDto) {
        return tagDto == null ? null : tagDto.getTag();
    }

    default TagDto fromStringToTagDto(String tag) {
        if (tag == null){
            return null;
        }
        TagDto tagDto = new TagDto();
        tagDto.setTag(tag);
        return  tagDto;
    }
}
