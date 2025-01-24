package org.cowary.vichabsync.controller;

import lombok.RequiredArgsConstructor;
import org.cowary.vichabsync.service.HabiticaService;
import org.cowary.vikunja.api.TaskApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final TaskApi taskApi;
    private final HabiticaService habiticaService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
