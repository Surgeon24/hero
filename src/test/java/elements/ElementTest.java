package elements;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTest {
    @Test
    public void setPosition() throws NoSuchFieldException, IllegalAccessException{
        final Element el = new Element(0,0);
        Position pos = new Position(8,9);
        el.setPosition(pos);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        assertEquals(field.get(el), pos);
    }

    @Test
    public void setX() {
        final Element el = new Element(0,0);
        el.setX(10);
        assertEquals(el.getX(), 10);
    }


    @Test
    public void getPosition() throws NoSuchFieldException, IllegalAccessException{
        final Element el = new Element(0,0);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos = new Position(5,6);
        el.setPosition(pos);
        field.set(el, pos);
        assertEquals(el.getPosition(), pos);
    }

    @Test
    public void getX() throws NoSuchFieldException, IllegalAccessException{
        final Element el = new Element(0,0);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos = new Position(5,6);
        el.setPosition(pos);
        field.set(el, pos);
        assertEquals(el.getPosition().getX(), 5);
    }

    @Test
    public void getY() throws NoSuchFieldException, IllegalAccessException{
        final Element el = new Element(0,0);
        final Field field = el.getClass().getDeclaredField("position");
        field.setAccessible(true);
        Position pos = new Position(5,6);
        el.setPosition(pos);
        field.set(el, pos);
        assertEquals(el.getPosition().getY(), 6);
    }
}
