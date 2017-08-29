package court;

import java.util.Random;

import lawyer.types.Judge;

public class GetPerson<E> {

	public E get(Object[] ob, E element) {
		while (true) {
			int rand = new Random().nextInt(ob.length);
			if (ob[rand].getClass().isInstance(element)) {
				return (E) ob[rand];
			}
		}
	}

}
