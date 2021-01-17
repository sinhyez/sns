package com.ipro.sns.controller;


import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.PostDto;
import com.ipro.sns.model.dto.UserDto;
import com.ipro.sns.repository.PostRepository;
import com.ipro.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final UserService userService;
    private final PostRepository postRepository;

    @RequestMapping("/upload")
    public String uploadProc(@RequestParam("file")MultipartFile file, @RequestParam("caption") String caption,
                             @RequestParam("usernick") String usernick) throws IOException {

        Optional<UserModel> userModelWrapper = userService.findByUsernick(usernick);
        UserModel userModel = userModelWrapper.get();
        String path = "C:/Users/yoon sung/Desktop/java/sns/src/main/resources/static/img/";
        String redirect = "redirect:/ipro/main/user/" + userModel.getUsernick();

        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(path + filename);
        Files.write(filepath, file.getBytes());

        PostDto postDto = new PostDto();
        postDto.setCaption(caption);
        postDto.setImgurl(path);
        postDto.setUser(userModel);
        postDto.setImgurl(filename);
        postRepository.save(postDto.toEntity()).getId();

        return redirect;

    }

    @RequestMapping("/ipro/post/details/{id}")
    public String postDetails(@PathVariable("id") int id, Model model) throws Exception {

        Optional<PostDto> postModel = postRepository.findById(id);
        model.addAttribute("post", postModel);

        return "view/post/post_details";
    }

}
