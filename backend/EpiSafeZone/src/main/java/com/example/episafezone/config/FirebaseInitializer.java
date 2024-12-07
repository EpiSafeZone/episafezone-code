package com.example.episafezone.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FirebaseInitializer {

    @PostConstruct
    public void initialize(){
        try {
            if(FirebaseApp.getApps().isEmpty()){
                InputStream serviceAccount = new ClassPathResource("episafezone-5cbef-firebase-adminsdk-nlonh-a9375cd18d.json").getInputStream();
                FirebaseOptions option  = FirebaseOptions.builder().
                        setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(option);
            }else{
                System.out.println(FirebaseApp.getApps());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing Firebase", e);
        }
    }
}
