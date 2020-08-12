package org.bonn.se.ss20.midterm.view;

import org.bonn.se.ss20.midterm.dto.UserStoryDTO;

import java.util.Collections;
import java.util.List;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class UserOutputDialog implements IDialog {

    @Override
    public void display(List<UserStoryDTO> list) {

        if (list.size() == 0) {
            System.out.println("No user stories found!");
            return;
        }

        System.out.println("Userstories: \n");
        Collections.sort(list);
        list.forEach(System.out::println);

    }
}
