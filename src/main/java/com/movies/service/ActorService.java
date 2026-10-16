package com.movies.service;

import com.movies.domain.Actors;
import com.movies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepo;

    public Actors createActor(Actors actor) {
        return actorRepo.save(actor);
    }

    @CachePut(value = "actors", key = "#actorId")
    public Actors updateActorTier(int actorId,String actorTier) {
        Actors actor = actorRepo.findById(actorId).orElseThrow(() -> new NoSuchElementException("No Actors Found"));
        actor.setActorTiers(actorTier);
        actorRepo.save(actor);
        return actor;
    }

    @Cacheable("actors")
    public List<Actors> getAllActors()
    {
     return actorRepo.findAll();
    }
}
