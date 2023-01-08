package me.viharev.filestohttp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class Emploee {
    private String name;
    private String lastName;
    private int age;

}
