package ferchulobo777.um.service;

import ferchulobo777.um.model.Character;
import ferchulobo777.um.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService implements ICharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<Character> listCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character searchCharacterForId(Integer idCharacter) {
        Character character = characterRepository.findById(idCharacter).orElse(null);
        return character;
    }

    @Override
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(Character character) {
         characterRepository.delete(character);
    }
}
