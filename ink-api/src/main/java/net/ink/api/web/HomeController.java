package net.ink.api.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.ink.api.core.dto.ApiResult;
import net.ink.core.core.exception.InkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/test")
    public ResponseEntity<ApiResult<TestObject>> test() {
        return ResponseEntity.ok(ApiResult.ok(new TestObject("test is ok!!!")));
    }

    @GetMapping("/authenticated-test")
    public ResponseEntity<ApiResult<TestObject>> authenticatedTest() {
        return ResponseEntity.ok(ApiResult.ok(new TestObject("test is ok!!!")));
    }

    @GetMapping("/test-failure")
    public ResponseEntity<ApiResult<TestObject>> testFailure() {
        throw new InkException("net.ink.ink failure");
    }

    @AllArgsConstructor
    @Getter @Setter
    public static class TestObject {
        private String result;
    }
}
