package pl.ryzykowski.rmffon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.rmffon.dto.TrackDTO;
import pl.ryzykowski.rmffon.service.SearchAuthorService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchAuthorController {

    private SearchAuthorService searchAuthorService;

    @Autowired
    public SearchAuthorController(SearchAuthorService searchAuthorService) {
        this.searchAuthorService = searchAuthorService;
    }

    @GetMapping("/search/{author}")
    public List<TrackDTO> getAllTracks(@PathVariable("author") String author){
        return searchAuthorService.getAuthorTracks(author);
    }

}
