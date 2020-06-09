package org.bonn.se.ss20.midterm.test;

import org.bonn.se.ss20.midterm.entity.UserStory;
import org.bonn.se.ss20.midterm.exception.ContainerException;
import org.bonn.se.ss20.midterm.model.Container;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContainerTest {
    private final UserStory userStory = new UserStory();

    @Before
    public void setUp() throws ContainerException {
        userStory.setId(Container.getInstance().getHighestId() + 1);
        userStory.setTitle("Titel test");
        userStory.setDescription("Beschreibung Test");
        userStory.setDetails("Details Test");
        userStory.setAkzeptanz("Akzeptanz");
        userStory.setEpic("Epic Test");
        userStory.setPriority(3);
        userStory.setCompleted(false);
        userStory.setActor("Student");

        System.out.println(userStory.getId());

    }

    @Test
    public void A_getInstance() {

        Container container = null;

        try {
            container = Container.getInstance();
        } catch (Exception e) {
            fail();
        }
        assertEquals(container, Container.getInstance());

    }

    @Test
    public void B_addUserStory() throws ContainerException {
        Container.getInstance().addUserStory(userStory);
        assertEquals(Container.getInstance().size(), 1);
        assertEquals(Container.getInstance().getHighestId(), 1);

        Container.getInstance().addUserStory(genUserStory());
        assertEquals(Container.getInstance().size(), 2);
        assertEquals(Container.getInstance().getHighestId(), 2);
    }

    @Test
    public void C_getUserStories() {
        assertNotNull(Container.getInstance().getUserStories(false));
    }

    //TODO
    @Test
    public void D_getUserStory() {
        assertEquals(userStory, Container.getInstance().getUserStory(1));
    }

    @Test
    public void E_setMyStories() {
    }

    @Test
    public void F_getHighestId() {
    }

    @Test
    public void G_containsUserStory() {
    }

    @Test
    public void H_removeUserStory() {
    }

    @Test
    public void I_removeAll() {
    }

    @Test
    public void J_addActor() {
    }

    @Test
    public void getActors() {
    }

    @Test
    public void containsActor() {
    }

    @Test
    public void removeActor() {
    }

    @Test
    public void addHistory() {
    }

    @Test
    public void undoHistory() {
    }

    @Test
    public void addCommand() {
    }

    @Test
    public void getCommand() {
    }

    @Test
    public void getAllCommands() {
    }

    @Test
    public void getCompleteHelp() {
    }

    @Test
    public void getHelp() {
    }

    @Test
    public void size() {
    }

    private UserStory genUserStory() {
        UserStory story = new UserStory();
        int randInt = (int) (Math.random() * 100);
        story.setId(Container.getInstance().getHighestId() + 1);
        story.setTitle("Titel test" + randInt);
        story.setDescription("Beschreibung Test" + randInt);
        story.setDetails("Details Test" + randInt);
        story.setAkzeptanz("Akzeptanz" + randInt);
        story.setEpic("Epic Test" + randInt);
        story.setPriority(randInt);
        story.setCompleted(false);
        story.setActor("Student" + randInt);

        return story;
    }

    @After
    public void tearDown() {
        Container.getInstance().removeAll();
    }


}