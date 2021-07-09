package com.duroflex.neuma.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duroflex.neuma.app.model.Feedback;
import com.duroflex.neuma.app.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

    public Feedback saveFeedback(Feedback fb) {
        return feedbackRepo.save(fb);
    }
}
