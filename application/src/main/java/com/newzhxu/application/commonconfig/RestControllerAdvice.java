package com.newzhxu.application.commonconfig;

import com.newzhxu.application.common.R;
import com.newzhxu.application.common.exception.NoContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail("服务器异常");
    }

    @ExceptionHandler(NoContent.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> handleNotFoundException(NoContent e) {
//        log.info("No content: {}", e.getMessage(), e);
        return ResponseEntity.noContent().build();  // 明确 204 + 无 body
    }
}
