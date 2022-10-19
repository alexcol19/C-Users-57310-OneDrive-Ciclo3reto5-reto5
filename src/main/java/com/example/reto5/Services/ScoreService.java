package com.example.reto5.Services;

import com.example.reto5.Repository.ScoreRepository;
import com.example.reto5.entities.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getScores(){ return scoreRepository.getScores(); }
    public Optional<Score> getScoreId(int id){
        return scoreRepository.getScoreId(id);
    }
    public Score saveScore(Score score){
        if(score.getIdScore()==null){
            return scoreRepository.saveScore(score);
        }else{
            Optional<Score> paux= scoreRepository.getScoreId(score.getIdScore());
            if(paux.isEmpty()){
                return scoreRepository.saveScore(score);
            }else
                return score;
        }
    }

    public Score update(Score score) {
        if (score.getIdScore() != null) {

            Optional<Score> paux = scoreRepository.getScoreId(score.getIdScore());
            if (!paux.isEmpty()) {
                if (score.getIdScore() != null) {
                    paux.get().setIdScore(score.getIdScore());
                }
                if (score.getReservation() != null) {
                    paux.get().setReservation(score.getReservation());
                }
                if (score.getMessageText() != null) {
                    paux.get().setMessageText(score.getMessageText());
                }
                return scoreRepository.saveScore(paux.get());
            }
        }
        return score;
    }
    public boolean deleteScore(int id){
        Boolean r = getScoreId(id).map(score ->{
            scoreRepository.deleteScore(score);
            return true;
        }).orElse(false);

        return r;

    }
}
