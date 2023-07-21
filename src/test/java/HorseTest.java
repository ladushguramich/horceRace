import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMassage() {  //Checking that when passed to the constructor with the first parameter null,
        // the thrown message will be sent the message "Name cannot be empty".
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"\n", "\t", " "})
    public void blankNameException(String horseName) {//Check that when passing in the constructor, only an empty string is specified or only whitespace,
        // characters are specified (space, tab, etc.)
        try {
            new Horse(horseName, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    public void negativeNameException(int ints) {
        try {
            new Horse("Horse", ints, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    public void distanceNameException(int distance) {
        try {
            new Horse("Horse", 1, distance);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getName() {
        Horse horse = new Horse("pantera", 1, 1);
        assertEquals("pantera", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    public void moveRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(anyDouble(), anyDouble()));
            //horse.move()
            //assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}
