package org.bonn.se.ss20.midterm.view;

import org.bonn.se.ss20.midterm.dto.UserStoryDTO;

import java.util.List;

public interface IDialog {

    void display(List<UserStoryDTO> list);

}