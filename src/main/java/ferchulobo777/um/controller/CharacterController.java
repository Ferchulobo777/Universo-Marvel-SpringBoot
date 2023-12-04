package ferchulobo777.um.controller;


import ferchulobo777.um.exception.ResourceNotFoundException;
import ferchulobo777.um.model.Character;
import ferchulobo777.um.service.CharacterService;
import ferchulobo777.um.service.ICharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/um/
@RequestMapping("um")
@CrossOrigin(value="http://localhost:5173")
public class CharacterController {
    private static final Logger logger =
            LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    private ICharacterService characterService;

    @GetMapping("/characters")
    public List<Character> obtainCharacter(){
        var characters = characterService.listCharacters();
        characters.forEach((character -> logger.info(character.toString())));
        return characters;
    }

    @PostMapping("/characters")
    public Character addCharacter(@RequestBody Character character){
        logger.info("Personaje a agregar: " + character);
        return characterService.saveCharacter(character);
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Character>
    obtainCharacterForId(@PathVariable Integer id){
        Character character = characterService.searchCharacterForId(id);
        if(character==null)
            throw new ResourceNotFoundException("No se enconro el Personaje: " + id);
        return ResponseEntity.ok(character);
    }
    @PutMapping("/characters/{id}")
    public ResponseEntity<Character>
    updateCharacter(@PathVariable Integer id,
                    @RequestBody Character characterReceived){
Character character = characterService.searchCharacterForId(id);
if (character == null)
    throw new ResourceNotFoundException("El id recibido no existe: " + id);
    character.setFirstName(characterReceived.getFirstName());
    character.setLastName(characterReceived.getLastName());
    character.setAlias(characterReceived.getAlias());
    character.setPhotoUrl(characterReceived.getPhotoUrl());
    character.setDescription(characterReceived.getDescription());
    character.setPowers(characterReceived.getPowers());
    character.setWeapon(characterReceived.getWeapon());
    character.setOrigin(characterReceived.getOrigin());
    character.setLevelPower(characterReceived.getLevelPower());
    characterService.saveCharacter(character);
    return ResponseEntity.ok(character);
    }
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCharacter(@PathVariable Integer id) {
        Character character = characterService.searchCharacterForId(id);
        if (character == null) {
            throw new ResourceNotFoundException("El id recibido no existe: " + id);
        }
        characterService.deleteCharacter(character);

        // Json {"eliminado": "true"}
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
