#include "Tree.h"

using namespace std;



//-------KOSTRUKTORY I DESTRUKTORY-------------

Tree::Tree() {
	root = nullptr;
}
Tree::~Tree() {
	delete root;
}


//-----------OPERATORY---------

Tree& Tree::operator=(const Tree &other) {
	root = other.root;

	return *this;
}


Tree* Tree::operator+( Tree &other) {
	bool isAdd = false;
	
	getValueOrVariable(root, &other, isAdd);

	//delete &other;

	return this;
}


//------------------------------FUNKCJE G£ÓWNE-------------------------------------

bool Tree::makeTree(string *data, unsigned int numberOfElements) {
	int counter = 0;
	bool isRepaired = false;
	bool isBroken = false;

	isRepaired = addNode(&root, data, counter, numberOfElements, isRepaired, isBroken);

	return isRepaired;
}


double Tree::calculate(string *data, int numOfVariables, int &error) {
	if (root == nullptr)
		return false;

	return calculateRec(data, numOfVariables, root, error);
}


double Tree::calculateRec(string *data, int numberOfValues, Node *node, int &error) {
	int counter = 0;									//licznik dla numOfVariables()
	int dataCounter = 0;								//licznik dla assignValues()
	int countVariable = numOfVariables(node, counter);	//wczytuje iloœæ zmiennych w drzewie

	if (numberOfValues != countVariable) {	//jeœli iloœc podanych sta³ych jest ró¿na od iloœci zmiennych zwróæ false
		error = 1;
		return 0;
	}
	else
		assignValues(node, data, dataCounter);	//przypisz dane do wartoœci

	double treeResult = result(node);
	//cout << "wynik to " << treeResult << endl;
	error = 0;
	return treeResult;
}


void Tree::getVar() {
	getVarRec(root);
}


void Tree::getVarRec(Node *node) {
	if (node->type == VARIABLE)
		cout << node->varOper << " ";
	else
		for (int i = 0; i < node->children.size(); i++) {
			if (node->children.at(i) != nullptr)
				getVarRec(node->children.at(i));
		}
}


string Tree::print() {
	string res = "";
	return printRec(root, res);
}


string Tree::printRec(Node *node, string &result) {
	if (node->type == VALUE) {
		ostringstream ss;
		ss << node->value;
		result += ss.str();
		result += " ";
	}
	else {
		result += node->varOper;
		result += " ";
	}

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			printRec(node->children.at(i), result);
	}
	return result;
}


Tree* Tree::mergeTree(string *newData, unsigned int numberOfElements) {
	Tree *newTree = new Tree();

	bool result = newTree->makeTree(newData, numberOfElements);


	Tree *sum = new Tree();
	sum = (*this) + (*newTree);

	//delete newTree;

	return sum;
}



//--------------------------FUNKCJE POMOCNICZE-------------------------

bool Tree::isInt(const std::string& s) {
	std::string::const_iterator it = s.begin();
	while (it != s.end() && std::isdigit(*it)) ++it;

	return !s.empty() && it == s.end();
}


bool Tree::isOper(string val) {
	if (val == PLUS || val == MINUS || val == MULTI || val == SUB || val == SIN || val == COS || val == SUPERSUM)
		return true;
	else
		return false;
}


bool Tree::isVar(string val) {
	if ((isInt(val) || isOper(val)) && val != "" && val != " ")
		return false;
	else
		return true;
}


int Tree::stringToInt(string val) {
	int result;
	stringstream(val) >> result;

	return result;
}


int Tree::numOfVariables(Node *node, int &counter) {
	if (node->type == VARIABLE)
		counter++;
	else
		for (int i = 0; i < node->children.size(); i++) {
			if (node->children.at(i) != nullptr)
				numOfVariables(node->children.at(i), counter);
		}

	return counter;
}


void Tree::assignValues(Node *node, string *data, int &dataCounter) {
	if (node->type == VARIABLE) {
		int converted = stringToInt(data[dataCounter]);
		node->value = converted;
		++dataCounter;
	}
	else {
		for (int i = 0; i < node->children.size(); i++) {
			assignValues(node->children.at(i), data, dataCounter);
		}
	}
}


Node* Tree::makeNode(string val) {
	if (isInt(val)) {
		int result = stringToInt(val);
		Node* newNode = new Node(result, VALUE);

		return newNode;
	}
	else if (isOper(val)) {
		Node* newNode = new Node(val, OPERATOR);

		return newNode;
	}
	else {
		Node* newNode = new Node(val, VARIABLE);

		return newNode;
	}
}


bool Tree::addNode(Node **newNode, string *data, int &counter, unsigned int &numberOfElements, bool isRepaired, bool &isBroken) {
	if (numberOfElements) {
		if (*newNode == nullptr && !isBroken) {
			//Node* n = makeNode(data[counter]);
			*newNode = makeNode(data[counter]);
		}
		else {
			//Node* n = makeNode(data[counter]);
			*newNode = makeNode(DEFAULT_REPAIR);

			//isBroken = false;
		}

		for (int i = 0; i < (*newNode)->children.size(); i++) {
			if ((*newNode)->children.at(i) == nullptr && numberOfElements > 1) {
				addNode(&(*newNode)->children.at(i), data, ++counter, --numberOfElements, isRepaired, isBroken);
			} //if (newNode->children.at(i) == nullptr)

			else {
				++numberOfElements;
				--counter;	//prawdopodobnie do usuniecia
				isBroken = true;
				isRepaired = true;
				//pod nie wiadomo czy inkrementowaæ i dekrementowaæ
				addNode(&(*newNode)->children.at(i), data, ++counter, --numberOfElements, isRepaired, isBroken);

			}
		} //for (int i = 0; i < newNode->children.size(); i++)
	}

	if (numberOfElements > 1)
		isRepaired = true;

	return isRepaired;
}


bool Tree::checkRootIsNull() {
	if (root == nullptr)
		return true;

	return false;
}


double Tree::result(Node *node) {
	if (node->type == VALUE || node->type == VARIABLE) {
		return node->value;
	}
	else if (node->type == OPERATOR && (node->varOper == SIN || node->varOper == COS)) {
		if (node->varOper == SIN) {
			double forSin;
			double sinResult;

			for (int i = 0; i < (node)->children.size(); i++) {
				forSin = result(node->children.at(i));
			}

			return sinResult = sin(forSin * PI / 180);
		} //if (node->varOper == SIN)
		else {
			double forCos;
			double cosResult;

			for (int i = 0; i < (node)->children.size(); i++) {
				forCos = result(node->children.at(i));
			}

			return cosResult = cos(forCos * PI / 180);

		} // else (COS)
	}

	else {
		if (node->varOper == PLUS || node->varOper == SUPERSUM) {
			double opResult = result(node->children.at(0));

			for (int i = 1; i < (node)->children.size(); i++) {
				opResult += result(node->children.at(i));
			}

			return opResult;
		} //PLUS

		else if (node->varOper == MINUS) {
			double opResult = result(node->children.at(0));

			for (int i = 1; i < (node)->children.size(); i++) {
				opResult -= result(node->children.at(i));
			}

			return opResult;
		} //MINUS

		else if (node->varOper == MULTI) {
			double opResult = result(node->children.at(0));

			for (int i = 1; i < (node)->children.size(); i++) {
				opResult *= result(node->children.at(i));
			}

			return opResult;
		} //MULTI

		else {
			double opResult = result(node->children.at(0));

			for (int i = 1; i < (node)->children.size(); i++) {
				opResult /= result(node->children.at(i));
			}

			return opResult;
		} //SUB
	} //else (operators)
}


void Tree::getValueOrVariable(Node *node, Tree *newTree, bool &isAdd) {

	for (int i = 0; i <node->children.size(); i++) {
		if ((node->children.at(i)->type == VALUE || node->children.at(i)->type == VARIABLE) && !isAdd) {
			isAdd = true;
			delete node->children.at(i);
			node->children.at(i) = (newTree->root);
			return;
		}
		else {
			getValueOrVariable(node->children.at(i), newTree, isAdd);		//funkcja która zapisuje do liœcia nowe drzewo 
		}
	}
}