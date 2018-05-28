#pragma once
#include <iostream>
#include <vector>

using namespace std;


//operatory = i +
//obs�uga pojedynczego w�z�a w drzewie

#define NUM_OF_CH 2	//domy�lna ilo�� dzieci

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
	nodeType type;				//typ definiuje co przechowuje dany w�ze�

	string varOper;				//string do trzymania zmiennych i operator�w
	double value;					//int do trzymania sta�ych liczb

	vector < Node* > children;	// dzieci danego w�z�a


public:
	friend class Tree;
	

	//-------KOSTRUKTORY I DESTRUKTORY---------

	Node(string s, nodeType t);		//konstruktor dla operatora i zmiennej
	Node(double val, nodeType t);		//konstruktor dla sta�ej
	Node(const Node &other);		//konstruktor kopiuj�cy

	~Node();						//destruktor


									//-----------OPERATORY---------

									//-----------FUNKCJE G��WNE---------

									//--------FUNKCJE POMOCNICZE---------
};