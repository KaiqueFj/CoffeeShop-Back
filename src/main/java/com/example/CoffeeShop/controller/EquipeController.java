package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.model.Equipe.Equipe;
import com.example.CoffeeShop.repository.EquipeRepository;
import com.example.CoffeeShop.service.EquipeDTO.EquipeRequestDTO;
import com.example.CoffeeShop.service.EquipeDTO.EquipeResponseDTO;

import java.util.List;
import java.util.Optional;

@RequestMapping("team")
@RestController
public class EquipeController {

  @Autowired
  private EquipeRepository repository;

  // Save the dealer
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/postTeam")
  public ResponseEntity<Equipe> saveTeam(@RequestBody EquipeRequestDTO data) {

    try {
      Equipe equipeData = new Equipe(data);
      repository.save(equipeData);
      return new ResponseEntity<Equipe>(equipeData, HttpStatus.CREATED);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Equipe>(HttpStatus.BAD_REQUEST);
    }

  }

  // Get all dealers
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllTeams")
  public ResponseEntity<List<EquipeResponseDTO>> getAll() {
    try {

      List<EquipeResponseDTO> equipeList = repository.findAll().stream().map(EquipeResponseDTO::new)
          .toList();

      return new ResponseEntity<List<EquipeResponseDTO>>(equipeList, HttpStatus.FOUND);
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<List<EquipeResponseDTO>>(HttpStatus.NOT_FOUND);
    }

  }

  // Get the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getTeamById/{id_equipe}")
  public ResponseEntity<Equipe> getTeamById(@PathVariable Integer id_equipe) {
    Optional<Equipe> equipeData = repository.findById(id_equipe);
    if (equipeData.isPresent()) {
      return new ResponseEntity<>(equipeData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @DeleteMapping("teams/{id_equipe}")
  private ResponseEntity<String> deleteTeam(@PathVariable Integer id_equipe) {
    try {
      boolean deleted = repository.existsById(id_equipe);

      if (deleted) {
        repository.deleteById(id_equipe);
        return ResponseEntity.ok("Resource deleted successfully");

      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
      }
    } catch (Exception e) {
      e.printStackTrace();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

  }

  // Update the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateTeamById/{id_equipe}")
  private ResponseEntity<Equipe> updateTeamById(@PathVariable Integer id_equipe,
      @RequestBody Equipe newEquipeData) {

    try {
      Optional<Equipe> oldEquipeData = repository.findById(id_equipe);

      if (oldEquipeData.isPresent()) {
        Equipe updateEquipeData = oldEquipeData.get();
        updateEquipeData.setDs_descricao(newEquipeData.getDs_descricao());
        repository.save(updateEquipeData);
        return new ResponseEntity<Equipe>(updateEquipeData, HttpStatus.OK);
      }

      else {
        return new ResponseEntity<Equipe>(HttpStatus.NOT_MODIFIED);
      }
    }

    catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Equipe>(HttpStatus.NOT_MODIFIED);
    }
  }

}
