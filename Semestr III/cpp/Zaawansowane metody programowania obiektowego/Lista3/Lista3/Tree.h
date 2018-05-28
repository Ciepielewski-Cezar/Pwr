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

using namespace std;


#define DEFAULT_REPAIR "1"		//warto�� jakimi ma by� uzupe�nione drzewo w przypadku z�ego wprowadzenia
#define PI 3.14


//ta klasa komunikuje si� z interfejsem
class Tree{

private:
	Node *root;

public:
	//-------KOSTRUKTORY I DESTRUKTORY-------------

	Tree();
	~Tree();


	//-----------OPERATORY---------
	Tree& operator=(const Tree &other);			//operator =
	Tree* operator+( Tree &other);			//operator +


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

	bool isInt(const std::string& s);							//sprawdza czy warto�� jest liczb�
	bool isOper(string val);									//sprawdza czy warto�� jest operatorem
	bool isVar(string val);										//sprawdza czy warto�� jest zmienn�

	int stringToInt(string val);								//zmienia stringa w int

	int numOfVariables(Node *node, int &counter);				//zlicza ilo�� zmiennych

	void assignValues(Node *node, string *data, int &counter);	//przypisuje dane do zmiennych

	Node* makeNode(string val);									//tworzy nowy w�ze� z podanej warto�ci
	bool addNode(Node **node, string *data, int &counter, unsigned int &numberOfElements, bool isRepaired, bool &isBroken);		//dodaje nowy w�ze� do drzewa

	bool checkRootIsNull();										//sprawdza czy root jest pusty

	double result(Node *node);									//oblicza drzewo

	void getValueOrVariable(Node *node, Tree *newTree, bool &isAdd);
};