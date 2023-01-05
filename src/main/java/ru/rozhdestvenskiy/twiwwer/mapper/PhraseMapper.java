package ru.rozhdestvenskiy.twiwwer.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.twiwwer.domain.api.phrase.PhraseRes;
import ru.rozhdestvenskiy.twiwwer.domain.api.phrase.PublicPhraseReq;
import ru.rozhdestvenskiy.twiwwer.dto.PhraseDto;
import ru.rozhdestvenskiy.twiwwer.dto.TagDto;
import ru.rozhdestvenskiy.twiwwer.model.Phrase;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhraseMapper {
    PhraseDto publicPhraseReqMapToPhraseDto(PublicPhraseReq publicPhraseReq);
    List<PhraseRes> phraseDtoListMapToPhraseRespList(List<PhraseDto> phraseDtoList);

    Phrase phraseDtoMapToPhrase(PhraseDto phraseDto);

    PhraseDto phraseMapToPhraseDto(Phrase phrase);

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
