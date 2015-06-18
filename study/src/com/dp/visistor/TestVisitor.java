package com.dp.visistor;

import java.util.ArrayList;
/**
 * The working process is like the following:

1. A visitor FirstTimeVisitor and an element City are created.
2. Program starts with "City accept a visitor".
3. The accept method in City defines that let this visitor to visit.
4. Accepted visitor call it's overloaded method "visit" to visit this City.
 * @author arisupply
 *
 */
interface Visitor {
	public void visit(City city);

	public void visit(Museum museum);

	public void visit(Park park);
}

class FirstTimeVisitor implements Visitor {

	@Override
	public void visit(City city) {
		System.out.println("I'm visiting the city!");
	}

	@Override
	public void visit(Museum museum) {
		System.out.println("I'm visiting the Museum!");
	}

	@Override
	public void visit(Park park) {
		System.out.println("I'm visiting the Park!");
	}
}

interface Element {
	public void accept(Visitor visitor);
}

class City implements Element {

	ArrayList<Element> places = new ArrayList<Element>();

	public City() {
		places.add(new Museum());
		places.add(new Park());
	}

	@Override
	public void accept(Visitor visitor) {
		System.out.println("City is accepting visitor.");
		visitor.visit(this);

		for (Element e : places) {
			e.accept(visitor);
		}
	}
}

class Museum implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Museum is accepting visitor.");
		visitor.visit(this);
	}
}

class Park implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Park is accepting visitor.");
		visitor.visit(this);
	}

}

public class TestVisitor {
	public static void main(String[] args) {
		FirstTimeVisitor visitor = new FirstTimeVisitor();
		City city = new City();
		city.accept(visitor);
	}
}