package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.model.Feedback;
import com.duroflex.neuma.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * Adding feedback
     *
     * @param feedback
     * @return
     */

    @PostMapping("/addFeedback")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.saveFeedback(feedback));

    }
}
