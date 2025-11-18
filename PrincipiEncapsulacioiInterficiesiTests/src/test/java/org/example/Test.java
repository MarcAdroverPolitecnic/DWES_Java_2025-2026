package org.example;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CuaTest {

    @Test
    void testEncuar() {
        CuaCotxes cua = new CuaCotxesQueueImpl(); // Aquí triem la implementació de la interface CuaCotxes

        Cotxe cotxe1 = new Cotxe("1111AAA", "Seat Ibiza");
        Cotxe cotxe2 = new Cotxe("2222BBB", "Ferrari TestaRosa");
        Cotxe cotxe3 = new Cotxe("3333CCC", "Twingo");

        assertThrows(IllegalArgumentException.class, () ->{
            cua.encuar(null);
        });

        cua.encuar(cotxe1);
        cua.encuar(cotxe2);
        cua.encuar(cotxe3);

        assertEquals(cotxe1, cua.get(0));
        assertEquals(cotxe2, cua.get(1));
        assertEquals(cotxe3, cua.get(2));

        assertEquals(3, cua.count());

    }

    @Test
    void testDesencuar() {
        CuaCotxes cua = new CuaCotxesQueueImpl();

        Cotxe cotxe1 = new Cotxe("1111AAA", "Seat Ibiza");
        Cotxe cotxe2 = new Cotxe("2222BBB", "Ferrari TestaRosa");
        Cotxe cotxe3 = new Cotxe("3333CCC", "Twingo");

        cua.encuar(cotxe1);
        cua.encuar(cotxe2);
        cua.encuar(cotxe3);

        cua.desencuar();

        assertEquals(cotxe2, cua.get(0));
        assertEquals(2, cua.count());

        cua.desencuar();

        assertEquals(cotxe3, cua.get(0));
        assertEquals(1, cua.count());

        cua.desencuar();

        assertThrows(NoSuchElementException.class, ()->{
            cua.desencuar();
        });
    }

    @Test
    void testCancelar(){
        CuaCotxes cua = new CuaCotxesQueueImpl();

        Cotxe cotxe1 = new Cotxe("1111AAA", "Seat Ibiza");
        Cotxe cotxe2 = new Cotxe("2222BBB", "Ferrari TestaRosa");
        Cotxe cotxe3 = new Cotxe("3333CCC", "Twingo");

        cua.encuar(cotxe1);
        cua.encuar(cotxe2);
        cua.encuar(cotxe3);

        cua.cancelar();

        assertEquals(cotxe1, cua.get(0));
        assertEquals(2, cua.count());

        cua.cancelar();

        assertEquals(cotxe1, cua.get(0));
        assertEquals(1, cua.count());

        cua.cancelar();

        assertThrows(NoSuchElementException.class, ()->{
            cua.cancelar();
        });


    }

    @Test
    void testCount() {

        CuaCotxes cua = new CuaCotxesQueueImpl();

        Cotxe cotxe1 = new Cotxe("1111AAA", "Seat Ibiza");
        Cotxe cotxe2 = new Cotxe("2222BBB", "Ferrari TestaRosa");
        Cotxe cotxe3 = new Cotxe("3333CCC", "Twingo");

        cua.encuar(cotxe1);
        cua.encuar(cotxe2);
        cua.encuar(cotxe3);

        assertEquals(3, cua.count());

        cua.desencuar();
        cua.desencuar();

        assertEquals(1, cua.count());

        Cotxe cotxe4 = new Cotxe("4444DDD", "Citroën C3");
        Cotxe cotxe5 = new Cotxe("5555EEE", "Peugeot Rifter");

        cua.encuar(cotxe4);
        cua.encuar(cotxe5);

        assertEquals(3, cua.count());

        cua.desencuar();
        cua.desencuar();
        cua.desencuar();

        assertEquals(0, cua.count());
    }
}