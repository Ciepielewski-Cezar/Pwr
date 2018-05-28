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


#define DEFAULT_REPAIR "1"		//wartoœæ jakimi ma byæ uzupe³nione drzewo w przypadku z³ego wprowadzenia
#define PI 3.14


//ta klasa komunikuje siê z interfejsem
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


	//-----------FUNKCJE G£ÓWNE---------

	bool makeTree(string *data, unsigned int numberOfElements);		//tworzy drzewo z podanego wyra¿enia

	double calculate(string *data, int numOfVariables, int &error);				//wylicz wyra¿enie dla podanych wartoœci, jeœli z³a liczba argumentów zwróæ false
	double calculateRec(string *data, int numberOfValues, Node *node, int &error);//pomocnicza do calculate()

	void getVar();													//zwraca wszystkie zmienne z drzewa
	void getVarRec(Node *node);										//pomocnicza do getVar()

	string print();													//drukuje drzewo w postaci prefiksowej
	string printRec(Node *node, string &result);										//funkcja pomocnicza do funkcji print()

	Tree* mergeTree(string *newData, unsigned int numberOfElements);	//tworzy nowe drzewo i dokleja do istniej¹cego, tutaj trzeba u¿yæ operatorów + i =
	

	
	//--------FUNKCJE POMOCNICZE---------

	bool isInt(const std::string& s);							//sprawdza czy wartoœæ jest liczb¹
	bool isOper(string val);									//sprawdza czy wartoœæ jest operatorem
	bool isVar(string val);										//sprawdza czy wartoœæ jest zmienn¹

	int stringToInt(string val);								//zmienia stringa w int

	int numOfVariables(Node *node, int &counter);				//zlicza iloœæ zmiennych

	void assignValues(Node *node, string *data, int &counter);	//przypisuje dane do zmiennych

	Node* makeNode(string val);									//tworzy nowy wêze³ z podanej wartoœci
	bool addNode(Node **node, string *data, int &counter, unsigned int &numberOfElements, bool isRepaired, bool &isBroken);		//dodaje nowy wêze³ do drzewa

	bool checkRootIsNull();										//sprawdza czy root jest pusty

	double result(Node *node);									//oblicza drzewo

	void getValueOrVariable(Node *node, Tree *newTree, bool &isAdd);
};