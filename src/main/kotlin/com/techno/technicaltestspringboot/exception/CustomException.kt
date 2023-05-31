package com.techno.technicaltestspringboot.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException

class CustomException(message : String): RuntimeException(message) {
}