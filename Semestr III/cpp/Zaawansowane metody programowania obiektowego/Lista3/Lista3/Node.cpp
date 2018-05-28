#include "Node.h"

using namespace std;


//-------KOSTRUKTORY I DESTRUKTORY---------

Node::Node(string s, nodeType t) {
	type = t;
	varOper = s;

	if (t == OPERATOR && (s == SIN || s == COS)) {			//dla sin i cos tylko jedno dziecko
		children.push_back(nullptr);
	}
	else if (t == OPERATOR && !(s == SIN || s == COS || s == SUPERSUM)) {	//dla reszty operatorów dwoje dzieci(domyœlnie)
		for (int i = 0; i < NUM_OF_CH; i++)
			children.push_back(nullptr);
	}
	else if (t == OPERATOR && s == SUPERSUM) {
		for (int i = 0; i < NUM_OF_CH + 1; i++)
			children.push_back(nullptr);
	}
}


Node::Node(int val, nodeType t) {
	type = t;
	value = val;
}


Node::Node(const Node &other) {
	type = other.type;
	varOper = other.varOper;
	value = other.value;

	for (int i = 0; i < other.children.size(); i++)
		children.push_back(nullptr);

	for (int i = 0; i < other.children.size(); i++)
		children.at(i) = new Node(*other.children.at(i));

}


Node::~Node() {
	/*
	for (std::vector< Node* >::iterator it = children.begin(); it != children.end(); ++it) {
		delete (*it);
	}
	*/
	for (std::vector< Node* >::iterator it = children.begin(); it != children.end();) {
		delete (*it);
		it = children.erase(it);
	}
}