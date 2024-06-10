package ci.luckyman.uobackend.controller;

import ci.luckyman.uobackend.entities.Opinion;
import ci.luckyman.uobackend.enums.OpinionType;
import ci.luckyman.uobackend.service.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping(value = "opinion")
public class OpinionController {
    private final OpinionService opinionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createOpinion(@RequestBody Opinion opinion) {
        opinionService.creer(opinion);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody List<Opinion> getOpinions(@RequestParam(required = false) OpinionType type) {
        return opinionService.allOpinion(type);
    }

    @DeleteMapping(path = "{id}")
    public  void deleteOpinion(@PathVariable Long id) {
        opinionService.deleteOpinion(id);
    }
}
