package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.ScoreCrudRepository;
import com.example.reto5.entities.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScoreRepository {
    @Autowired
    private ScoreCrudRepository scoreCrudRepository;

    public List<Score> getScores(){
        return (List<Score>) scoreCrudRepository.findAll();
    }
    public Optional<Score> getScoreId(int id){ return scoreCrudRepository.findById(id);}
    public Score saveScore(Score score){ return scoreCrudRepository.save(score); }
    public void deleteScore(Score score){
        scoreCrudRepository.delete(score);
    }
}
