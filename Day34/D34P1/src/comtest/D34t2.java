package comtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.D34j2;

class D34t2 {
	D34j2 d = new D34j2();
	
	@Test
	void mul() {
		assertEquals(1000, d.mul(100, 10));
	}
	@Test
	void div() {
		assertEquals(0,d.idv(100, 0));
	}

}
