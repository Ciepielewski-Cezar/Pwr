#pragma once
#include "Node.h"

#include <iostream>
#include <vector>
#include <string>
#include <cctype>
#include <sstream>
#include <istream>
#include <stdio.h>     
#include <math.h>    
#include <random>
#include <map>

using namespace std;




#define DEFAULT_REPAIR "1"		//warto�� jakimi ma by� uzupe�nione drzewo w przypadku z�ego wprowadzenia
#define PI 3.14

#define MIN_VALUE 1			//najmniejsza wylosowana warto�� dla w�z�a
#define MAX_VALUE 30		//najwi�ksza wylosowana warto�� dla w�z�a

#define ZERO 0
#define ONE 1

//zmienne
//#define X "x"
//#define Y "y"
//#define Z "Z"





//ta klasa komunikuje si� z interfejsem
class Tree {

private:
	Node *root;

	double valueFunction;

public:
	friend class GeneticProgramming;

	//-------KOSTRUKTORY I DESTRUKTORY-------------

	Tree();

	Tree(const Tree &other);

	~Tree();


	//-----------OPERATORY---------
	Tree& operator=(const Tree &other);			//operator =
	Tree* operator+(Tree &other);			//operator +


	bool operator < (const Tree& str) const;
	



		//-----------FUNKCJE G��WNE---------

	bool makeTree(string *data, unsigned int numberOfElements);		//tworzy drzewo z podanego wyra�enia

	double calculate(string *data, int numOfVariables, int &error);				//wylicz wyra�enie dla podanych warto�ci, je�li z�a liczba argument�w zwr�� false
	double calculateRec(string *data, int numberOfValues, Node *node, int &error);//pomocnicza do calculate()

	void getVar();													//zwraca wszystkie zmienne z drzewa
	void getVarRec(Node *node);										//pomocnicza do getVar()

	string print();													//drukuje drzewo w postaci prefiksowej
	string printRec(Node *node, string &result);										//funkcja pomocnicza do funkcji print()

	Tree* mergeTree(string *newData, unsigned int numberOfElements);	//tworzy nowe drzewo i dokleja do istniej�cego, tutaj trzeba u�y� operator�w + i =



									//--------FUNKCJE POMOCNICZE---------

	bool isDouble(const std::string& s);						//sprawdza czy warto�� jest typu double
	bool isOper(string val);									//sprawdza czy warto�� jest operatorem
	bool isVar(string val);										//sprawdza czy warto�� jest zmienn�

	double stringToInt(string val);								//zmienia stringa w int

	int numOfVariables(Node *node, int &counter);				//zlicza ilo�� zmiennych

	void assignValues(Node *node, string *data, int &counter);	//przypisuje dane do zmiennych

	Node* makeNode(string val);									//tworzy nowy w�ze� z podanej warto�ci
	bool addNode(Node **node, string *data, int &counter, unsigned int &numberOfElements, bool isRepaired, bool &isBroken);		//dodaje nowy w�ze� do drzewa

	bool checkRootIsNull();										//sprawdza czy root jest pusty

	double result(Node *node);									//oblicza drzewo

	void getValueOrVariable(Node *node, Tree *newTree, bool &isAdd);	//sprawdza czy w�ze� jest warto�ci� lub zmienn� i wstawia nowe drzewo

	
	void Tree::getVariables(Node *node, map<string, double> &variablesWithValue);		//zwraca mape z warto�ciami bez powt�rze�

	void Tree::assignValuesToMap(Node *node, map<string, double> &variablesWithValue, string *data,int &dataCounter);	//przypisuje mapie zmienne i sta�e dla nich

	void assignValuesToTreeWithMap(Node *node, map<string, double> &variablesWithValue);	//przypisuje dane do zmiennych za pomoc� mapy


	//-----------FUNKCJE G��WNE GP---------


	void mutating(Tree *randomTree);			//mutuje drzewo

	
	//void addTreeRandomly(Node *node, Tree *newTree, int &counter, int randomNodeIndex, bool &isAdd);	//dodaje w randomowe miejsce nowe drzewo i usuwa stare
	void Tree::addTreeRandomly(Tree *newTree, int randomNodeIndex, int NumOfNodes);


	Tree& divideAndReturn(int nodeIndex);		// dzieli i zwraca jedn� po��wk� drzew

	//Tree& getNodeForDivisionRec(Node *nodeint, int numOfNodes);	//pomocnicza do getNodeForDivision

	void mergeTree(Tree *secondTree);		//��czy obydwa drzewa

	void mergeTreeHelper(Node *node, Tree *secondTree, int &counter, int randomLeaf, bool &isMerged, bool &checkFirst);	//pomocnicza do mergetree


	//--------FUNKCJE POMOCNICZE GP---------

	void printNumOfNodes();							//drukuje ilo�� node w drzewie

	int getNumOfNodes();							//liczy ilo�� node w drzewie

	int numOfNodesRec(Node *node, int &counter);	//liczy ilo�� node w drzewie (pomocnicza)

	int GetRandomNumber(int min, int max);			//zwraca losow� liczb� w przedziale (min, max)

	bool repairTree();								//uzupe�nia uci�te drzewo

	bool repairTreeRec(Node *node);					//uzupe�nia uci�te drzewo(pomocnicza)

	int numOfVariablesAndValues(Node *node, int &counter);	//zlicza ilo�� zmiennych i sta�ych

	
	//gettery i settery
	void setValueFunction(double n);

	double getValueFunction();

	vector < Node* > getVectorOfNodes(Node *node, vector < Node* > &listsOfNode);		//zwraca wektor z wszystkimi nodami drzewa

	void mutatingInside();

	Tree* mutatingInsideRec(Node *node, int randomNode, int &counter);

	void Tree::addingTreeAtIndex(Node *node, Tree *tree, int &counter, int nodeIndex);
	
};