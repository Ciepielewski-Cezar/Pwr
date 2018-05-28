#include "GeneticProgramming.h"

using namespace std;

//-------KOSTRUKTORY I DESTRUKTORY-------------
GeneticProgramming::GeneticProgramming() {
	vRun();
}




GeneticProgramming::~GeneticProgramming() {

	for (std::vector< Tree* >::iterator it = oldGeneration.begin(); it != oldGeneration.end();) {
		delete (*it);
		it = oldGeneration.erase(it);
	}

	for (std::vector< Tree* >::iterator it = newGeneration.begin(); it != newGeneration.end();) {
		delete (*it);
		it = newGeneration.erase(it);
	}
}



//------------------------------FUNKCJE G£ÓWNE-------------------------------------


void GeneticProgramming::vRun() {


	//-----PRAWID£OWY----

	int i = 0;
	initialozation();
	evaluation();
	
	while (i < NUM_OF_ITERATION) {
		selection();
		crossing();
		mutation();
		evaluation();
		i++;

	}
	writeResults();
	


	/*
	//-----TEST CROSSING----

	initialozation();

	evaluation();

	selection();



	//test
	cout << "stara generacja przed : " << endl;
	for (int i = 0; i < oldGeneration.size(); i++) {
		cout << oldGeneration.at(i)->print();
		cout << endl;
	}
	cout << endl;
	cout << "nowa generacja przed: " << endl;

	for (int i = 0; i < newGeneration.size(); i++) {
		cout << newGeneration.at(i)->print();
		cout << endl;
	}
	cout << endl;
	cout << endl;
	cout << endl;
	//~test


	crossing();


	//test
	cout << endl;
	cout << endl;
	cout << "WYNIKI: " << endl;
	cout << "stara generacja po algorytmie: " << endl;
	for (int i = 0; i < oldGeneration.size(); i++) {
		cout << oldGeneration.at(i)->print();
		cout << endl;
	}
	cout << endl;
	cout << "nowa generacja po algorytmie: " << endl;
	//test
	for (int i = 0; i < newGeneration.size(); i++) {
		cout << newGeneration.at(i)->print();
		cout << endl;
	}
	*/

	/*
	initialozation();

	cout << evaluation() << endl;

	//selection();

	cout << "generacja : " << endl;
	for (int i = 0; i < oldGeneration.size(); i++) {
		cout << oldGeneration.at(i)->valueFunction;
		cout << endl;
	}


	writeResults();
	*/



}



void GeneticProgramming::initialozation() {
	for (int i = 0; i < NUM_OF_POPULATION; i++)
		oldGeneration.push_back(MakeRandomTree());

}



bool GeneticProgramming::evaluation() {

	for (int i = 0; i < oldGeneration.size(); i++) {
		vector<string> words;
		ifstream file("read.txt");
		string str;

		if (!file)
			return false;

		int counter = 0;

		while (!file.eof()) {
			getline(file, str);
			words = split(str, ' ');

			int arrLength = words.size();
			string *array = new string[arrLength];

			for (int i = 0; i < words.size(); i++)
				array[i] = words.at(i);
			//array[i] = atof(words.at(i).c_str());

			//double functionResult = atof(array[2].c_str());
			double functionResult = atof(array[arrLength-1].c_str());

			int err = 0;



			double treeResult = oldGeneration.at(i)->calculate(array, arrLength, err);	//wartoœæ dla drzewa

			double result = pow((functionResult - treeResult), 2.0);					//wyliczony wspó³czynnik

			oldGeneration.at(i)->setValueFunction(oldGeneration.at(i)->getValueFunction() + result);	//dodajemy wspó³czynnik i now¹ wartoœææ
			//cout << "drzewo[" << i << "]:	treeResult = " << treeResult << "		functionResult = " << functionResult << "		result = " << result << "		oldGeneration.at(i)->getValueFunction() = " << oldGeneration.at(i)->getValueFunction() << endl;

			counter++;	//zliczamy iloœæ wykonañ


			delete[] array;
		} //while (!file.eof())

		oldGeneration.at(i)->setValueFunction(oldGeneration.at(i)->getValueFunction() / counter);	//dzielimy przez licznik 

		counter = 0;

	} //for (int i = 0; i < oldGeneration.size(); i++) 

	return true;
}



void  GeneticProgramming::selection() {



	//turnieje
	for (int i = 0; i < NUM_OF_POPULATION; i++) {
		int randomInteger1 = GetRandomNumber(ZERO, oldGeneration.size() - 1);
		int randomInteger2 = GetRandomNumber(ZERO, oldGeneration.size() - 1);


		if (oldGeneration.at(randomInteger1)->getValueFunction() <= oldGeneration.at(randomInteger2)->getValueFunction())
			newGeneration.push_back(new Tree(*oldGeneration.at(randomInteger1)));
		else
			newGeneration.push_back(new Tree(*oldGeneration.at(randomInteger2)));
	}

	//wyczyszczenie starego wektora
	for (std::vector< Tree* >::iterator it = oldGeneration.begin(); it != oldGeneration.end();) {
		delete (*it);
		it = oldGeneration.erase(it);
	}

}



void  GeneticProgramming::crossing() {
	while (newGeneration.size() >= 2) {
		int first = GetRandomNumber(ZERO, newGeneration.size() - 1);
		int second = GetRandomNumber(ZERO, newGeneration.size() - 1);
		int probOfCross = GetRandomNumber(ZERO, HUNDRED);

		while (first == second) {
			second = GetRandomNumber(ZERO, newGeneration.size() - 1);
		}
		/*
		//test
		cout << "------------" << endl;
		cout << "drzewo jeden przed podzialem [" << first << "]: " << newGeneration.at(first)->print() << endl;
		cout << "drzewo dwa przed podzialem [" << second << "]: " << newGeneration.at(second)->print() << endl;
		cout << endl;
		//~test
		*/

		if (probOfCross <= PROB_OF_CROSSING) {	// <= PROB_OF_CROSSING)
			int numOfNodesFirst = newGeneration.at(first)->getNumOfNodes();
			int numOfNodesSecond = newGeneration.at(second)->getNumOfNodes();


			//int randomNodeFirst = GetRandomNumber(ONE, numOfNodesFirst - 1);
			//int randomNodeSecond = GetRandomNumber(ONE, numOfNodesSecond - 1);

			//cout << "numOfNodesFirst - 1: " << numOfNodesFirst - 1 << endl;
			//cout << "numOfNodesSecond - 1: " << numOfNodesSecond - 1 << endl;
			//cout << endl;
			//cout << "randomNodeFirst: " << randomNodeFirst << endl;
			//cout << "randomNodeSecond: " << randomNodeSecond << endl;
			//cout << endl;

			Tree *treeFirstFirst = new Tree(newGeneration.at(first)->divideAndReturn(numOfNodesFirst));

			//cout << "drzewo jedenJeden : " << treeFirstFirst->print() << endl;

			Tree *treeFirstSecond = new Tree(*newGeneration.at(first));

			//cout << "drzewo jedenDwa: " << treeFirstSecond->print() << endl;

			//cout << "drzewo jedenJeden czy root jest pusty: " << treeFirstFirst->checkRootIsNull() << endl;

			//test
			//cout << "drzewo jeden po podziale: " << newGeneration.at(first)->print() << endl;
			//cout << endl;



			treeFirstFirst->repairTree();

			//cout << "drzewo jedenJeden po naprawie: " << treeFirstFirst->print() << endl;

			treeFirstSecond->repairTree();

			//cout << "drzewo jedenDwa po naprawie: " << treeFirstSecond->print() << endl;
			//cout << endl;

			//------------------- drzewo dwa -----------------

			Tree *treeSecondFirst = new Tree(newGeneration.at(second)->divideAndReturn(numOfNodesSecond));

			//cout << "drzewo dwaJeden : " << treeSecondFirst->print() << endl;

			Tree *treeSecondSecond = new Tree(*newGeneration.at(second));

			//cout << "drzewo dwaDwa: " << treeSecondSecond->print() << endl;


			//test
			//cout << "drzewo dwa po podziale: " << newGeneration.at(second)->print() << endl;
			//cout << endl;


			treeSecondFirst->repairTree();

			//cout << "drzewo dwaJeden po naprawie: " << treeSecondFirst->print() << endl;

			treeSecondSecond->repairTree();

			//cout << "drzewo dwaDwa po naprawie: " << treeSecondSecond->print() << endl;
			//cout << endl;




			treeFirstFirst->mergeTree(treeSecondSecond);

			//cout << "drzewo jedenJeden;dwaDwa po merge: " << treeFirstFirst->print() << endl;

			treeFirstSecond->mergeTree(treeSecondFirst);

			//cout << "drzewo jedenDwa;dwaJeden po merge: " << treeFirstSecond->print() << endl;
			//cout << endl;


			//wstawienie drzew do next gen
			oldGeneration.push_back(new Tree(*treeFirstFirst));
			oldGeneration.push_back(new Tree(*treeSecondSecond));


			delete treeFirstFirst;
			delete treeFirstSecond;
			delete treeSecondFirst;
			delete treeSecondSecond;


		} //if (probOfCross < PROB_OF_CROSSING) 

		else {
			//brak krzy¿owania, dodajemy oboje rodziców
			oldGeneration.push_back(new Tree(*newGeneration.at(first)));
			oldGeneration.push_back(new Tree(*newGeneration.at(second)));
		} //else

		/*
		//TEST
		cout << "stara generacja po dodaniu 2 drzew: " << endl;

		for (int i = 0; i < oldGeneration.size(); i++) {
			cout << oldGeneration.at(i)->print();
			cout << endl;
		}
		//~test
		*/

		//usuwamy je z obecnej generacji
		for (int i = newGeneration.size() - 1; i >= 0; i--)
			if (i == first || i == second) {
				delete newGeneration[i];
				newGeneration.erase(newGeneration.begin() + i);
			}

		/*
		//test
		cout << endl;
		cout << "nowa generacja po usunieciu 2 drzew: " << endl;
		for (int i = 0; i < newGeneration.size(); i++) {
			cout << newGeneration.at(i)->print();
			cout << endl;
		}
		//~test
		*/

	} //while (newGeneration.size >= 2)


	//jeœli zosta³o jedno drzewo dodajemy je do kolejnej generacji
	if (newGeneration.size() == 1)
		oldGeneration.push_back(new Tree(*newGeneration.at(0)));


	//wyczyszczenie newGeneration
	for (std::vector< Tree* >::iterator it = newGeneration.begin(); it != newGeneration.end();) {
		delete (*it);
		it = newGeneration.erase(it);
	}

}



void GeneticProgramming::mutation() {
	for (int i = 0; i < oldGeneration.size(); i++) {
		//oldGeneration.at(i)->mutating(MakeRandomTree());
		oldGeneration.at(i)->mutatingInside();
		oldGeneration.at(i)->repairTree();
		//test
		//cout << oldGeneration.at(i)->print() << endl;
	}
	//cout << endl;
}



int GeneticProgramming::findBest() {
	int min = INT_MAX;
	for (int i = 0; i < oldGeneration.size(); i++) {
		if (oldGeneration.at(i)->valueFunction < min)
			min = i;
	}

	return min;
}






void GeneticProgramming::writeResults() {

	int theBest = findBest();

	//test
	//cout << oldGeneration.at(theBest)->print() << endl;
	//cout << oldGeneration.at(theBest)->valueFunction << endl;


	string result = oldGeneration.at(theBest)->print();

	/*FILE * pFile;
	pFile = fopen("write.txt", "w");

	if (pFile != NULL){
		*pFile << result;
		fclose(pFile);
	}
	*/
	ofstream("write.txt", std::ofstream::out | std::ofstream::trunc);

	ofstream plik("write.txt");
	plik << result;
	plik.close();

}





//--------FUNKCJE POMOCNICZE---------


Tree*  GeneticProgramming::MakeRandomTree() {
	Tree *tree = new Tree();

	int numOfNodes = GetRandomNumber(MIN_NUM_NODES_IN_TREE, MAX_NUM_NODES_IN_TREE);
	string* rData = randomData(numOfNodes);


	tree->makeTree(rData, numOfNodes + 1);

	delete[] rData;

	return tree;
}



double GeneticProgramming::function(double x, double y) {
	//double function = (x - 2) * (x + 4);
	double function = sin(x * y);
	return function;
}



string*  GeneticProgramming::randomData(int numOfNodes) {
	vector<string> words;
	int operCount = 0;
	int varCount = 0;

	words.push_back(randomOperator(false));



	for (int i = 0; i < numOfNodes; i++)
		words.push_back(randomTypeOfNode(operCount, varCount));


	int dataLength = words.size();
	string *data = new string[dataLength];

	for (int i = 0; i < words.size(); i++)
		data[i] = words.at(i);

	return data;
}



string  GeneticProgramming::randomOperator(bool withSinCos) {
	int number;

	if (withSinCos)
		number = GetRandomNumber(MIN_RAND_OPER, MAX_RAND_OPER);
	else
		number = GetRandomNumber(MIN_RAND_OPER, MAX_RAND_OPER_WITHOUT_TRIG);


	if (number == 1)
		return PLUS;
	else if (number == 2)
		return MINUS;
	else if (number == 3)
		return MULTI;
	else if (number == 4)
		return SUB;
	else if (number == 5)
		return SIN;
	else
		return COS;
}



string  GeneticProgramming::randomTypeOfNode(int &operCount, int &varCount) {
	int typeOfNode = GetRandomNumber(MIN_TYPE_NODE, MAX_TYPE_NODE);


	if (typeOfNode == 1 && operCount < MAX_NUM_OPER_IN_TREE) {
		operCount++;

		return randomOperator(true);
	}
	else if (typeOfNode == 2 && varCount < MAX_NUM_VAR_IN_TREE) {
		string randomVar = randomVariable(varCount);
		varCount++;

		return randomVar;
	}
	else {
		string randomVal = to_string(GetRandomNumber(MIN_RAND_VALUE, MAX_RAND_VALUE));

		return randomVal;
	}
}



string  GeneticProgramming::randomVariable(int counter) {
	if (counter == 0) return X;
	else if (counter == 1) return Y;
	else return Z;

}



int GeneticProgramming::GetRandomNumber(int min, int max) {
	random_device g_rd;
	mt19937 g_rng(g_rd());
	uniform_int_distribution<int> uni(min, max);

	auto randomInteger = uni(g_rng);

	return randomInteger;
}













void GeneticProgramming::split(const string &s, char delim, vector<string> &elems) {
	stringstream ss(s);
	string item;
	while (getline(ss, item, delim)) {
		elems.push_back(item);
	}
}



vector<string> GeneticProgramming::split(const string &s, char delim) {
	vector<string> elems;
	split(s, delim, elems);
	return elems;
}


