#include "Tree.h"

using namespace std;



//-------KOSTRUKTORY I DESTRUKTORY-------------

Tree::Tree() {
	root = nullptr;
	valueFunction = 0;
}

Tree::Tree(const Tree &other) {
	root = new Node(*other.root);

	valueFunction = other.valueFunction;
}


Tree::~Tree() {
	delete root;
}


//-----------OPERATORY---------

Tree& Tree::operator=(const Tree &other) {
	root = other.root;

	return *this;
}


Tree* Tree::operator+(Tree &other) {
	bool isAdd = false;

	getValueOrVariable(root, &other, isAdd);

	//delete &other;

	return this;
}


bool Tree::operator < (const Tree& str) const {
	return (valueFunction < str.valueFunction);
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
	map<string, double> numberOfVariables;					//licznik zmiennych
	map<string, double> variablesWithValue;				//do przypisania wartoœci do zmiennych zmiennych drzewu

	/*int countVariable = numOfVariables(node, counter);	//wczytuje iloœæ zmiennych w drzewie

	if (numberOfValues < countVariable) {	//jeœli iloœc podanych sta³ych jest ró¿na od iloœci zmiennych zwróæ false
		error = 1;
		return 0;
	}
	else


	getVariables(root, numberOfVariables);

	if (numberOfValues != numberOfVariables.size()) {	//jeœli iloœc podanych sta³ych jest ró¿na od iloœci zmiennych zwróæ false
		error = 1;
		return 0;
	}
	*/

	getVariables(root, numberOfVariables);

	if (numberOfValues < numberOfVariables.size()) {	//jeœli iloœc podanych sta³ych jest ró¿na od iloœci zmiennych zwróæ false
		error = 1;
		return 0;
	}
	else {
		if (numberOfValues > numberOfVariables.size()) {	//jeœli iloœc podanych sta³ych jest ró¿na od iloœci zmiennych zwróæ false
			numberOfValues = numberOfVariables.size();
		}
		//assignValues(node, data, dataCounter);	//przypisz dane do wartoœci
		assignValuesToMap(root, variablesWithValue, data, dataCounter);	//uzupe³nia mape 
		assignValuesToTreeWithMap(root, variablesWithValue);
	}

	double treeResult = result(node);
	//cout << "wynik to " << treeResult << endl;
	error = 0;
	return treeResult;
}


void Tree::assignValuesToMap(Node *node, map<string, double> &variablesWithValue, string *data, int &dataCounter) {
	if (node->type == VARIABLE) {
		if (variablesWithValue.find(node->varOper) == variablesWithValue.end()) {
			variablesWithValue.insert(std::pair<string, double>(node->varOper, stringToInt(data[dataCounter])));
			dataCounter++;
		}
	}

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			assignValuesToMap(node->children.at(i), variablesWithValue, data, dataCounter);
	}

}



void Tree::assignValuesToTreeWithMap(Node *node, map<string, double> &variablesWithValue) {
	if (node->type == VARIABLE) {
		std::map<string, double>::iterator it = variablesWithValue.find(node->varOper);
		if (it != variablesWithValue.end())
			node->value = it->second;
	}

	for (int i = 0; i < node->children.size(); i++) {
		assignValuesToTreeWithMap(node->children.at(i), variablesWithValue);
	}
}



void Tree::getVariables(Node *node, map<string, double> &variablesWithValue) {
	if (node->type == VARIABLE) {
		if (variablesWithValue.find(node->varOper) == variablesWithValue.end()) {
			variablesWithValue.insert(std::pair<string, int>(node->varOper, 0));
		}
		else {

		}
	}

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			getVariables(node->children.at(i), variablesWithValue);
	}

}


void Tree::getVar() {
	//getVarRec(root);

	map<string, double> variablesWithValue;
	getVariables(root, variablesWithValue);
	vector<string> v;

	for (map<string, double>::iterator it = variablesWithValue.begin(); it != variablesWithValue.end(); ++it) {
		v.push_back(it->first);
		cout << it->first << " ";
	}

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

//to zmodyfikowaæ na double
bool Tree::isDouble(string const & str) {
	auto result = double();
	auto i = std::istringstream(str);

	i >> result;
	i >> std::ws;

	return !i.fail() && i.eof();
}


bool Tree::isOper(string val) {
	if (val == PLUS || val == MINUS || val == MULTI || val == SUB || val == SIN || val == COS || val == SUPERSUM)
		return true;
	else
		return false;
}


bool Tree::isVar(string val) {
	if ((isDouble(val) || isOper(val)) && val != "" && val != " ")
		return false;
	else
		return true;
}

//zmodyfikowaæ na double
double Tree::stringToInt(string val) {
	double result;
	stringstream(val) >> result;

	return result;
}


int Tree::numOfVariables(Node *node, int &counter) {
	if (node->type == VARIABLE) {
		counter++;
	}

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			numOfVariables(node->children.at(i), counter);
	}

	return counter;
}

//to na double
void Tree::assignValues(Node *node, string *data, int &dataCounter) {
	if (node->type == VARIABLE) {
		double converted = stringToInt(data[dataCounter]);
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
	if (isDouble(val)) {
		double result = stringToInt(val);
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
			string randomRepair = to_string(GetRandomNumber(MIN_VALUE, MAX_VALUE));
			//Node* n = makeNode(data[counter]);
			*newNode = makeNode(randomRepair);

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

	for (int i = 0; i < node->children.size(); i++) {
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






//-----------FUNKCJE G£ÓWNE GP---------



void Tree::mutating(Tree *randomTree) {
	int counter = getNumOfNodes();


	int randomNodeIndex = GetRandomNumber(ZERO, ONE);


	addTreeRandomly(randomTree, randomNodeIndex, counter);

}



void Tree::addTreeRandomly(Tree *newTree, int randomNodeIndex, int NumOfNodes) {
	if (NumOfNodes == 1 || root->children.size() == 0) {
		delete root;
		root = (newTree->root);

		return;
	}
	else {
		if (root->varOper == SIN || root->varOper == COS || root->children.size() == 1) {
			delete root->children[ZERO];
			root->children.erase(root->children.begin() + ZERO);

			root->children.at(ZERO) = (newTree->root);

			return;
		}
		else {
			delete root->children[randomNodeIndex];
			//root->children.erase(root->children.begin() + randomNodeIndex);

			root->children.at(randomNodeIndex) = new Node(*newTree->root);

			return;
		}
	}
}




Tree& Tree::divideAndReturn(int numOfNodes) {
	if (numOfNodes == 1) {
		Tree *toReturn = new Tree();
		toReturn->root = new Node(*root);


		return *toReturn;
	}
	else {
		Tree *toReturn = new Tree();
		toReturn->root = new Node(*root->children.at(0));

		delete root->children.at(0);
		root->children.erase(root->children.begin() + 0);

		return *toReturn;
	}
}






void Tree::mergeTree(Tree *secondTree) {
	bool checkFirst = false;
	bool isMerged = false;
	int counter = 1;
	int leavesNum = 0;
	numOfVariablesAndValues(root, leavesNum);

	int randomLeaf = GetRandomNumber(ONE, leavesNum);

	mergeTreeHelper(root, secondTree, counter, randomLeaf, isMerged, checkFirst);
}



void Tree::mergeTreeHelper(Node *node, Tree *secondTree, int &counter, int randomLeaf, bool &isMerged, bool &checkFirst) {

	if ((node->type == VALUE || node->type == VARIABLE) && !checkFirst && !isMerged) {
		if (counter == randomLeaf) {
			node = new Node(*secondTree->root);
			return;
		}

		else
			counter++;

	}

	checkFirst = true;


	for (int i = 0; i < node->children.size(); i++) {
		if ((node->children.at(i)->type == VALUE || node->children.at(i)->type == VARIABLE) && !isMerged) {
			if (counter == randomLeaf) {
				isMerged = true;
				node->children.at(i) = new Node(*secondTree->root);
				return;
			}

			else
				counter++;
		}
		else {
			mergeTreeHelper(node->children.at(i), secondTree, counter, randomLeaf, isMerged, checkFirst);
		}
	}
}




//--------FUNKCJE POMOCNICZE GP---------



void Tree::printNumOfNodes() {
	int counter = 0;
	cout << "ilosc wezlow: " << numOfNodesRec(root, counter) << endl;
}


int Tree::getNumOfNodes() {
	int counter = 0;

	numOfNodesRec(root, counter);

	return counter;
}


int Tree::numOfNodesRec(Node *node, int &counter) {
	if (node != nullptr)
		counter++;

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			numOfNodesRec(node->children.at(i), counter);
	}

	return counter;
}


int Tree::GetRandomNumber(int min, int max) {
	random_device g_rd;
	mt19937 g_rng(g_rd());
	uniform_int_distribution<int> uni(min, max);

	auto randomInteger = uni(g_rng);

	return randomInteger;
}


bool Tree::repairTree() {
	return repairTreeRec(root);
}


bool Tree::repairTreeRec(Node *node) {
	if (node->type == OPERATOR) {
		if ((node->varOper == SIN || node->varOper == COS) && node->children.size() != NUM_OF_CH - 1) {
			string randomRepair = to_string(GetRandomNumber(MIN_VALUE, MAX_VALUE));
			node->children.push_back(makeNode(randomRepair));

			return true;
		}
		else if (node->children.size() != NUM_OF_CH) {
			string randomRepair = to_string(GetRandomNumber(MIN_VALUE, MAX_VALUE));
			node->children.push_back(makeNode(randomRepair));


		}
	}

	for (int i = 0; i < node->children.size(); i++) {
		repairTreeRec(node->children.at(i));
	}

	return false;
}


int Tree::numOfVariablesAndValues(Node *node, int &counter) {
	if (node->type == VARIABLE || node->type == VALUE)
		counter++;

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			numOfVariablesAndValues(node->children.at(i), counter);
	}

	return counter;
}






void Tree::setValueFunction(double n) {
	valueFunction = n;
}

double Tree::getValueFunction() {
	return valueFunction;
}




vector < Node* > Tree::getVectorOfNodes(Node *node, vector < Node* > &listsOfNode) {
	listsOfNode.push_back(new Node(*node));

	for (int i = 0; i < node->children.size(); i++) {
		if (node->children.at(i) != nullptr)
			getVectorOfNodes(node->children.at(i), listsOfNode);
	}
	return listsOfNode;
}



void Tree::mutatingInside() {
	vector < Node* > listsOfNode;
	getVectorOfNodes(root, listsOfNode);

	if (listsOfNode.size() <= 2) {
		for (std::vector< Node* >::iterator it = listsOfNode.begin(); it != listsOfNode.end();) {
			delete (*it);
			it = listsOfNode.erase(it);
		}

		return;
	}

	int randomIndexOfNode1 = GetRandomNumber(ONE, listsOfNode.size() - 1);
	int randomIndexOfNode2 = GetRandomNumber(ONE, listsOfNode.size() - 1);
	int counter1 = 0;
	int counter2 = 0;
	bool canAdd = true;

	Tree *firstTree = new Tree(*mutatingInsideRec(root, randomIndexOfNode1, counter1));
	int firstTreeSize = firstTree->getNumOfNodes();



	for (int i = randomIndexOfNode1 - 1; i < randomIndexOfNode1 + firstTreeSize; i++) {
		if (randomIndexOfNode2 == i)
			canAdd = false;
	}

	if (canAdd) {
		int counter3 = 0;
		int counter4 = 0;
		Tree *secondTree = new Tree(*mutatingInsideRec(root, randomIndexOfNode2, counter2));

		addingTreeAtIndex(root, firstTree, counter3, randomIndexOfNode2);
		addingTreeAtIndex(root, secondTree, counter4, randomIndexOfNode1);

		delete firstTree;
		delete secondTree;

		for (std::vector< Node* >::iterator it = listsOfNode.begin(); it != listsOfNode.end();) {
			delete (*it);
			it = listsOfNode.erase(it);
		}
	}
	else {
		delete firstTree;

		for (std::vector< Node* >::iterator it = listsOfNode.begin(); it != listsOfNode.end();) {
			delete (*it);
			it = listsOfNode.erase(it);
		}

		return;
	}
}

Tree* Tree::mutatingInsideRec(Node *node, int randomNode, int &counter) {
	for (int i = 0; i < node->children.size(); i++) {
		if (counter == randomNode) {

			Tree *toReturn = new Tree();
			toReturn->root = new Node(*node->children.at(i));


			return toReturn;
		}

		if (node->children.size() != 0)
			counter++;

		mutatingInsideRec(node->children.at(i), randomNode, counter);
	}
}



void Tree::addingTreeAtIndex(Node *node,Tree *tree, int &counter, int nodeIndex) {
	for (int i = 0; i < node->children.size(); i++) {
		if (counter == nodeIndex ) {
			
			//delete node->children[i];
			node->children.at(i) = new Node(*tree->root);
		}

		if (node->children.size() != 0)
			counter++;

		addingTreeAtIndex(node->children.at(i), tree, counter, nodeIndex);
	}


}











/*

Tree& Tree::getNodeForDivision(int nodeIndex) {
int counter = 1;
bool isFound = false;

//Tree *toReturn = getNodeForDivisionRec(root, counter, nodeIndex, isFound);

//cout << "drukuje drzewo w getNodeForDivision() : " << toReturn->print() << endl;

return getNodeForDivisionRec(root);
}




Tree& Tree::getNodeForDivisionRec(Node *node, int &counter, int nodeIndex, bool &isFound) {
for (int i = 0; i < node->children.size(); i++) {
if (counter == nodeIndex && !isFound) {
isFound = true;

Tree *toReturn = new Tree();
toReturn->root = new Node(*node->children.at(i));

delete node->children.at(i);
node->children.erase(node->children.begin() + i);

return *toReturn;
}

if (node->children.size() != 0)
counter++;

getNodeForDivisionRec(node->children.at(i), counter, nodeIndex, isFound);
}


}

*/


/*

void Tree::mutating(Tree *randomTree) {
int counter = getNumOfNodes();

//tutaj mo¿liwe ¿e b³¹d
int randomNodeIndex = GetRandomNumber(ONE, counter);

int addTreeRandomlyCounter = 0;
bool isAdd = false;

addTreeRandomly(root, randomTree, addTreeRandomlyCounter, randomNodeIndex, isAdd);

}

void Tree::addTreeRandomly(Node *node, Tree *newTree, int &counter, int randomNodeIndex, bool &isAdd) {
if (randomNodeIndex == 1) {
node = (newTree->root);
return;
}
for (int i = 0; i < node->children.size(); i++) {
if (counter == randomNodeIndex && !isAdd) {
isAdd = true;

//delete node->children.at(i);	//tutaj sprawdziæ
delete node->children[i];
node->children.erase(node->children.begin() + i);


node->children.at(i) = (newTree->root);
return;
}
else {
counter++;
addTreeRandomly(node->children.at(i), newTree, counter, randomNodeIndex, isAdd);
}
}
}


*/