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
  public void saveTeam(@RequestBody EquipeRequestDTO data) {

    try {
      Equipe equipeData = new Equipe(data);
      repository.save(equipeData);
      return;
    }

    catch (Exception e) {
      e.printStackTrace();
    }

  }

  // Get all dealers
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/getAllTeams")
  public List<EquipeResponseDTO> getAll() {

    List<EquipeResponseDTO> equipeList = repository.findAll().stream().map(EquipeResponseDTO::new)
        .toList();

    return equipeList;
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

    boolean deleted = repository.existsById(id_equipe);

    if (deleted) {
      repository.deleteById(id_equipe);
      return ResponseEntity.ok("Resource deleted successfully");

    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }
  }

  // Update the Dealer by Id
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PutMapping("/updateTeamById/{id_equipe}")
  private void updateTeamById(@PathVariable Integer id_equipe,
      @RequestBody Equipe newEquipeData) {

    try {
      Optional<Equipe> oldEquipeData = repository.findById(id_equipe);

      if (oldEquipeData.isPresent()) {
        Equipe updateEquipeData = oldEquipeData.get();
        updateEquipeData.setDs_descricao(newEquipeData.getDs_descricao());
        repository.save(updateEquipeData);
        return;
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
