#pragma once
#include <iostream>
#include <vector>

using namespace std;


//operatory = i +
//obs³uga pojedynczego wêz³a w drzewie

#define NUM_OF_CH 2	//domyœlna iloœæ dzieci

#define PLUS "+"
#define MINUS "-"
#define MULTI "*"
#define SUB "/"
#define SIN "sin"
#define COS "cos"
#define SUPERSUM "++"


enum nodeType { OPERATOR, VARIABLE, VALUE };



class Node {

private:
	nodeType type;				//typ definiuje co przechowuje dany wêze³

	string varOper;				//string do trzymania zmiennych i operatorów
	double value;					//int do trzymania sta³ych liczb

	vector < Node* > children;	// dzieci danego wêz³a


public:
	friend class Tree;
	

	//-------KOSTRUKTORY I DESTRUKTORY---------

	Node(string s, nodeType t);		//konstruktor dla operatora i zmiennej
	Node(double val, nodeType t);		//konstruktor dla sta³ej
	Node(const Node &other);		//konstruktor kopiuj¹cy

	~Node();						//destruktor


									//-----------OPERATORY---------

									//-----------FUNKCJE G£ÓWNE---------

									//--------FUNKCJE POMOCNICZE---------
};