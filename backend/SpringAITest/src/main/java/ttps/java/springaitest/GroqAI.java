package ttps.java.springaitest;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/groqai")
@CrossOrigin("*")
public class GroqAI {

    private OpenAiChatModel chatModel;

    public GroqAI(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }



    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
       try{
           String response = chatModel.call(message);
           return ResponseEntity.ok(response);
       } catch (Exception e) {
           System.out.println(e);
       }

       return ResponseEntity.badRequest().build();



    }



}
