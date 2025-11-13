package ttps.java.springaitest;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class OpenAI {

    private OpenAiChatModel chatModel;

    public OpenAI(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }



    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
        String response = chatModel.call(message);


        return ResponseEntity.ok(response);


    }



}
