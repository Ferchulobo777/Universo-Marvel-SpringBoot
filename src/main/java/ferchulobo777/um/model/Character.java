package ferchulobo777.um.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCharacter;
    String firstName;
    String lastName;
    String alias;
    String photoUrl;
    String description;
    String powers;
    String weapon;
    Integer levelPower;
    String origin;
}