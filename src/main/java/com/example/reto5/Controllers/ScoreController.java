package com.example.reto5.Controllers;

import com.example.reto5.Services.ScoreService;
import com.example.reto5.entities.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})


public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> getAll() { return scoreService.getScores();
    }
    @GetMapping("/{id}")
    public Optional<Score> getReservationsId(@PathVariable("id") int id){
        return scoreService.getScoreId(id);
    }

    @PostMapping("/save")
    public Score saveScore(@RequestBody Score score) { return scoreService.saveScore(score);
    }

    @PutMapping("/update")
    public Score update(@RequestBody Score score) { return scoreService.update(score);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return scoreService.deleteScore(id);

    }



}
