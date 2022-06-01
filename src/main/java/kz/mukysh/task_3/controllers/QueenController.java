package kz.mukysh.task_3.controllers;

import kz.mukysh.task_3.models.Queen;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/coordinates")
public class QueenController {
    @GetMapping("/")
    public ResponseEntity<List<String[]>> index() {
        Queen queen = new Queen();
        List<String[]> list = Arrays.stream(Queen.twoD).collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("{coordinates}")
    public ResponseEntity<List<String>> getCoordinates(@PathVariable String coordinates) {
        List<String> list = new ArrayList<>();
        String[] firstR = new String[8];
        String delimeter = " "; // Разделитель
        String[] subStr = coordinates.split(delimeter);
        if (subStr.length == 2 && subStr[0].matches("[-+]?\\d+")
                && subStr[1].matches("[-+]?\\d+") &&
                (Integer.parseInt(subStr[0])<8&& Integer.parseInt(subStr[0])>=0) &&
                (Integer.parseInt(subStr[1])<8&& Integer.parseInt(subStr[1])>=0)) {
            for (int i = 0; i < 92; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Queen.twoD[i][j].contains(coordinates)) {
                        for (int k = 0; k < 8; k++) {
                            firstR[k] = Queen.twoD[i][k];
                        }
                        String s = Arrays.toString(firstR);
                        list.add(s);
                    }
                }
            }
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
