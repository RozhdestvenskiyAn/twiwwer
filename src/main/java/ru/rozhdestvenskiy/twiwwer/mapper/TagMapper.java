package ru.rozhdestvenskiy.twiwwer.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.twiwwer.dto.TagDto;
import ru.rozhdestvenskiy.twiwwer.model.Tag;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag TagDtoMapTag (TagDto tagDto);
    TagDto TagMapTagDto (Tag tag);
    Set<TagDto> setTagDtoMapToSetTag(Set<Tag> tags);
    Set<Tag> setTagMapToSetTagDto(Set<TagDto> tagsDto);
}
