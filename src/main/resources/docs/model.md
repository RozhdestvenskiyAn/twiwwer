# _Модель данных_

### User

_id, nickname, password, email, time_insert_;

### Phrase

_id, text, user_id, time_insert_;

### Tag

_id, tag_;

### Phrase_tag

_phrase_id, tag_id_;