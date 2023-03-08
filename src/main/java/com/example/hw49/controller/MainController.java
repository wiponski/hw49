package com.example.hw49.controller;

import com.example.hw49.service.Candidate;
import com.example.hw49.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class MainController {
    private  List<Candidate> candidates = getCandidates();

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("cds", candidates);
        return "candidates";
    }

    private List<Candidate> getCandidates (){
        List<Candidate> candidates = FileService.readCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            candidates.get(i).setId(i + 1);
        }
        return candidates;
    }

    private Candidate getCurrentCandidate (int id){
        for (Candidate candidate : candidates) {
            if (id == candidate.getId()) {
               candidate.vote();
                return candidate;
            }
        }
        throw new RuntimeException();
    }

    @RequestMapping(value="/thankyou" )
    public String thankyouPage(Integer test1, Model model){
        model.addAttribute("candidate", getCurrentCandidate(test1));

        model.addAttribute("count", count(test1));
        return "thankyou";
    }

    @RequestMapping(value="/vote", method = RequestMethod.POST)
    public String votePage(Integer test2, Model model){
        return "redirect:/thankyou?test1=" + test2;
    }

    @RequestMapping(value="/votes", method = RequestMethod.GET)
    public String voteResult(Model model){
        model.addAttribute("cdsv", candidates);

        return "votes";
    }

    private Integer count(int x){
       int  totalVotes = candidates.stream()
                .mapToInt(Candidate::getVotes)
                .sum();
        int y =0;
        if (totalVotes == 0) {
            return 0;
        }else {
            y = (y + x)/totalVotes * 100;
            return y;
        }
    }
}
