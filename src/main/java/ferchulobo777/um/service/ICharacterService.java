package ferchulobo777.um.service;

import java.util.List;
import ferchulobo777.um.model.Character;

public interface ICharacterService {
    public List<Character> listCharacters();

    public Character searchCharacterForId(Integer idCharacter);

    public Character saveCharacter(Character character);

    public void deleteCharacter(Character character);
}
