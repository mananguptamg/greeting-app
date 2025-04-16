package com.bridgelabz.greetingapp.service;

import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.model.User;
import com.bridgelabz.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingServiceImpl implements IGreetingService{
    private static final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting addGreeting(User user){
        String message = String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString());
        return greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
    }

    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).get();
    }

    @Override
    public void deleteGreetingById(long id) {
        greetingRepository.deleteById(id);
    }

    @Override
    public Greeting updateGreetingById(long id, User user) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            String updatedMessage = "Hello " + user.getFirstname() + " " + user.getLastname();
            greeting.setMessage(updatedMessage);
            return greetingRepository.save(greeting);
        } else {
            throw new RuntimeException("Greeting not found with ID: " + id);
        }
    }
}
