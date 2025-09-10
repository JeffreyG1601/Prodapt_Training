package comtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.D34j1;

class D34t1 {
	D34j1 d = new D34j1();
	@Test
	void add() {
		assertEquals(300,d.add(100,200));
	}
	@Test
	void sub() {
		assertEquals(100,d.sub(100,200));
	}
	@Test
	void fact() {
		assertEquals(120,d.fact(5));
	}

}
