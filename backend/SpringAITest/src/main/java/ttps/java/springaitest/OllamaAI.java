package ttps.java.springaitest;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/ollama")
@CrossOrigin("*")
public class OllamaAI {

    private ChatClient chatClient;


    public OllamaAI(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }



    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
        ChatResponse chatResponse = chatClient
                .prompt(message)
                .call()
                .chatResponse();


        System.out.println("Metadata de interes:" + chatResponse.getMetadata().getRateLimit() +
                " " +
                chatResponse.getMetadata().getModel());


        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());




    }



}
