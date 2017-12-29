package br.com.rest.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EndpointUtil implements Serializable {

    public ResponseEntity<?> returnObjectOrNotFound(Object object) {
        return object == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(object, HttpStatus.OK);
    }

}
