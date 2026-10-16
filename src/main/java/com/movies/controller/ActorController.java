package com.movies.controller;

import com.movies.domain.Actors;
import com.movies.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @PostMapping("/actor")
    public ResponseEntity<Object> createActor(@RequestBody Actors actors) {
        Actors actor = actorService.createActor(actors);
        return new ResponseEntity<Object>(actor, HttpStatus.CREATED);
    }

    @PatchMapping("/actor/{actorId}")
    public String updateActorTier(@PathVariable int actorId, @RequestParam String actorTier) {
        Actors actors = actorService.updateActorTier(actorId, actorTier);
        return actors.getActorName() + " Updated";
    }

    @GetMapping("/getActors")
    public List<Actors> getActors()
    {
     return actorService.getAllActors();
    }
}
